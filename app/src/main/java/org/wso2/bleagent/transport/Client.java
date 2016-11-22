package org.wso2.bleagent.transport;

import org.wso2.bleagent.MainActivity;
import org.wso2.bleagent.constants.Constants;
import org.wso2.bleagent.transport.executor.BeaconConnectAsyncExecutor;
import org.wso2.bleagent.transport.executor.ClientRegisterAsyncExecutor;
import org.wso2.bleagent.util.BeaconProperties;
import org.wso2.bleagent.util.EddystoneProperties;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Client {
    private static final String STATUS = "status";

    public static boolean register(String url, String username, String password){
        boolean status = false;

        try {
            ClientRegisterAsyncExecutor executor = new ClientRegisterAsyncExecutor();
            executor.execute(username, password, url);
            Map<String, String> response = executor.get();
            String responseStatus = response.get(STATUS);

            status = responseStatus.contains(Constants.Request.REQUEST_SUCCESSFUL);
        } catch (InterruptedException e) {
            //Do nothing
        } catch (ExecutionException e) {
            //Do nothing
        } catch (NullPointerException e){

        }

        return status;
    }

    public static void beaconConnect(MainActivity activity, BeaconProperties properties){
        BeaconConnectAsyncExecutor executor = new BeaconConnectAsyncExecutor(activity);
        String protocol = properties.getProtocol();

        switch (protocol){
            case Constants.PROTOCOL_EDDYSTONE: {
                EddystoneProperties eddystoneProperties = (EddystoneProperties)properties;
                String namespace = eddystoneProperties.getNamespace();
                String instance = eddystoneProperties.getInstance();
                executor.execute(protocol, namespace, instance, null);
            }
        }
    }
}
