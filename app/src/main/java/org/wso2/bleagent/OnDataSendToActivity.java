package org.wso2.bleagent;

import org.wso2.bleagent.util.dto.deviceRegistrationUtils.Action;

/**
 * Created by wso2123 on 11/8/16.
 */
public interface OnDataSendToActivity {
    void onBeaconConnection(Action action);
}
