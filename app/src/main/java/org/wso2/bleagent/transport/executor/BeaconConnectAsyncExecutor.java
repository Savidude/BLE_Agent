package org.wso2.bleagent.transport.executor;

import android.app.Activity;
import android.os.AsyncTask;

import org.wso2.bleagent.OnDataSendToActivity;
import org.wso2.bleagent.constants.Constants;
import org.wso2.bleagent.util.LocalRegistry;
import org.wso2.bleagent.util.dto.deviceRegistrationUtils.Action;
import org.wso2.bleagent.util.dto.deviceRegistrationUtils.AgentManagerService;

import java.util.HashMap;
import java.util.Map;

import feign.FeignException;

/**
 * Created by wso2123 on 11/8/16.
 */
public class BeaconConnectAsyncExecutor extends AsyncTask<String, Void, Map<String, String>> {
    private static final String STATUS = "status";

    private Action action;

    private OnDataSendToActivity dataSendToActivity;
    public BeaconConnectAsyncExecutor(Activity activity){
        dataSendToActivity = (OnDataSendToActivity)activity;
    }


    @Override
    protected Map<String, String> doInBackground(String... strings) {
        String protocol = strings[0];
        String id1 = strings[1];
        String id2 = strings[2];
        String id3 = strings[3];

        Map<String, String> responseMap = new HashMap<>();

        LocalRegistry localRegistry = LocalRegistry.getInstance();
        AgentManagerService managerService = localRegistry.getManagerService();

        try{
            switch (protocol){
                case Constants.PROTOCOL_EDDYSTONE: {
                    action = managerService.eddystoneConnect(id1, id2, localRegistry.getProfile());
                    break;
                }
            }
        }catch (FeignException e){
            e.printStackTrace();
            responseMap.put(STATUS, String.valueOf(e.status()));
        }

        if(action!=null){
            responseMap.put(STATUS, "200");
        }else {
            responseMap.put(STATUS, "400");
        }

        return responseMap;
    }

    @Override
    protected void onPostExecute(Map<String, String> stringStringMap) {
        if(action!=null){
            dataSendToActivity.onBeaconConnection(action);
        }
    }
}
