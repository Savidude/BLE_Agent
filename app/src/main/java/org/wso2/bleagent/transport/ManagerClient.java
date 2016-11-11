package org.wso2.bleagent.transport;


import org.wso2.bleagent.constants.Constants;
import org.wso2.bleagent.transport.executor.ManagerRequestAsyncExecutor;
import org.wso2.bleagent.util.LocalRegistry;

public class ManagerClient {

    public static boolean sendRequestToManager(String[] endpointAttributes){
        boolean status = false;

        String requestType = endpointAttributes[0];
        String requestUrl = endpointAttributes[1];
        String requestParams = endpointAttributes[2];
        String newParams = requestParams.replace("$user", LocalRegistry.getInstance().getDeviceId());
        String newAttributes = requestType+";"+requestUrl+";"+newParams;

        String managerUrl = getManagerUrl(LocalRegistry.getInstance().getUrl());

        ManagerRequestAsyncExecutor executor = new ManagerRequestAsyncExecutor();
        executor.execute(managerUrl, newAttributes);

        return status;
    }

    private static String getManagerUrl(String agentUrl){
        String[] agentUrlAttr = agentUrl.split(":");
        String hostUrl = agentUrlAttr[1];
        String managerUrl = "http:" + hostUrl + ":" + Constants.MANAGER_PORT + Constants.MANAGER_RESOURCE;
        return managerUrl;
    }
}
