package org.wso2.bleagent.util.dto.dynamicClientRegistrationUtils;

/**
 * Created by wso2123 on 10/7/16.
 */
public class RegistrationProfile {
    public String callbackUrl;
    public String clientName;
    public String tokenScope;
    public String owner;
    public String grantType;
    public String applicationType;

    private static final String TAG = RegistrationProfile.class.getSimpleName();

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callBackUrl) {
        this.callbackUrl = callBackUrl;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTokenScope() {
        return tokenScope;
    }

    public void setTokenScope(String tokenScope) {
        this.tokenScope = tokenScope;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }
}
