package org.wso2.bleagent.transport.executor;

import android.os.AsyncTask;

import org.wso2.bleagent.constants.Constants;
import org.wso2.bleagent.util.LocalRegistry;
import org.wso2.bleagent.util.dto.AccessTokenInfo;
import org.wso2.bleagent.util.dto.apiApplicationRegistrationUtils.ApiApplicationKey;
import org.wso2.bleagent.util.dto.apiApplicationRegistrationUtils.ApiApplicationRegistrationService;
import org.wso2.bleagent.util.dto.apiApplicationRegistrationUtils.ApiRegistrationProfile;
import org.wso2.bleagent.util.dto.apiApplicationRegistrationUtils.OAuthRequestInterceptor;
import org.wso2.bleagent.util.dto.deviceRegistrationUtils.AgentManagerService;
import org.wso2.bleagent.util.dto.dynamicClientRegistrationUtils.DynamicClientRegistrationService;
import org.wso2.bleagent.util.dto.dynamicClientRegistrationUtils.OAuthApplicationInfo;
import org.wso2.bleagent.util.dto.dynamicClientRegistrationUtils.RegistrationProfile;
import org.wso2.bleagent.util.dto.passwordGrantTypeUtils.TokenIssuerService;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import feign.Client;
import feign.Feign;
import feign.FeignException;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;

public class ClientRegisterAsyncExecutor extends AsyncTask<String, Void, Map<String, String>> {
    private static final String STATUS = "status";
    private final String PASSWORD_GRANT_TYPE = "password";
    private final String SCOPE = "perm:bleagent:enroll";

    TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
    };

    Client disableHostnameVerification = new Client.Default(getTrustedSSLSocketFactory(), new HostnameVerifier() {
        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }
    });

    private SSLSocketFactory getTrustedSSLSocketFactory(){
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            return sc.getSocketFactory();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            return null;
        }

    }

    @Override
    protected Map<String, String> doInBackground(String... strings) {
        String username = strings[0];
        String password = strings[1];
        String endpoint = strings[2];
        String deviceId = LocalRegistry.getInstance().getDeviceId();

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put(STATUS, "200");

        AccessTokenInfo accessTokenInfo = null;

        try{
            //            API application registration
            ApiApplicationRegistrationService apiApplicationRegistrationService = Feign.builder().client(disableHostnameVerification)
                    .requestInterceptor(new BasicAuthRequestInterceptor(username, password))
                    .contract(new JAXRSContract()).encoder(new JacksonEncoder()).decoder(new JacksonDecoder())
                    .target(ApiApplicationRegistrationService.class, endpoint + Constants.API_APPLICATION_REGISTRATION_CONTEXT);
            ApiRegistrationProfile apiRegistrationProfile = new ApiRegistrationProfile();
            apiRegistrationProfile.setApplicationName("bleagent_" + deviceId);
            apiRegistrationProfile.setIsAllowedToAllDomains(false);
            apiRegistrationProfile.setIsMappingAnExistingOAuthApp(false);
            apiRegistrationProfile.setTags(new String[]{Constants.DEVICE_TYPE});
            ApiApplicationKey apiApplicationKey = apiApplicationRegistrationService.register(apiRegistrationProfile);

            //PasswordGrantType
            TokenIssuerService tokenIssuerService = Feign.builder().client(disableHostnameVerification).requestInterceptor(
                    new BasicAuthRequestInterceptor(apiApplicationKey.getConsumerKey(), apiApplicationKey.getConsumerSecret()))
                    .contract(new JAXRSContract()).encoder(new JacksonEncoder()).decoder(new JacksonDecoder())
                    .target(TokenIssuerService.class, endpoint);
            accessTokenInfo = tokenIssuerService.getToken(PASSWORD_GRANT_TYPE, username, password, "device_" + deviceId, SCOPE);

            //Device registration
            AgentManagerService agentManagerService = Feign.builder().client(disableHostnameVerification)
                    .requestInterceptor(new OAuthRequestInterceptor(accessTokenInfo.getAccess_token()))
                    .contract(new JAXRSContract()).encoder(new JacksonEncoder()).decoder(new JacksonDecoder())
                    .target(AgentManagerService.class, endpoint + Constants.REGISTER_CONTEXT);
            if(agentManagerService != null){
                LocalRegistry.getInstance().setManagerService(agentManagerService);
            }else{
                responseMap.put(STATUS, "400");
            }
        }catch (FeignException e){
            responseMap.put(STATUS, String.valueOf(e.status()));
            return responseMap;
        }
        return responseMap;
    }
}
