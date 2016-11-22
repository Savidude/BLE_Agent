package org.wso2.bleagent;

import org.wso2.bleagent.util.dto.deviceRegistrationUtils.Action;

public interface OnDataSendToActivity {
    void onBeaconConnection(Action action);
}
