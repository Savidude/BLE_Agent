package org.wso2.bleagent.transport;


import org.wso2.bleagent.constants.Constants;
import org.wso2.bleagent.transport.executor.ManagerRequestAsyncExecutor;
import org.wso2.bleagent.util.LocalRegistry;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ManagerClient {

    public static boolean sendRequestToManager(String[] endpointAttributes){
        boolean status = false;

        String requestType = endpointAttributes[0];
        String requestUrl = endpointAttributes[1];
        String requestParams = endpointAttributes[2];

        //Setting the params set by owner for user and timestamp
        String newParams = requestParams.replace("$user", LocalRegistry.getInstance().getDeviceId());
        newParams = newParams.replace("$timestamp", Long.toString(System.currentTimeMillis()));
        String newAttributes = requestType+";"+requestUrl+";"+newParams;

        String managerUrl = getManagerUrl(LocalRegistry.getInstance().getUrl(), Constants.RESOURCE_REQUEST_ENDPOINT);

        ManagerRequestAsyncExecutor executor = new ManagerRequestAsyncExecutor();
        executor.execute(managerUrl, newAttributes);

        return status;
    }

    public static String requestImage(String path){
        String url = null;
        try {
            String managerUrl = getManagerUrl(LocalRegistry.getInstance().getUrl(), Constants.RESOURCE_DOWNLOAD);
            String encodedPath = URLEncoder.encode("path", "UTF-8") + "=" + URLEncoder.encode(path, "UTF-8");
            url = managerUrl + "?" + encodedPath;
        } catch (UnsupportedEncodingException e) {
            //Do nothing
        }

        return url;
    }

    private static String getManagerUrl(String agentUrl, String resource){
        String[] agentUrlAttr = agentUrl.split(":");
        String hostUrl = agentUrlAttr[1];
        String managerUrl = "http:" + hostUrl + ":" + Constants.MANAGER_PORT + resource;
        return managerUrl;
    }
}
