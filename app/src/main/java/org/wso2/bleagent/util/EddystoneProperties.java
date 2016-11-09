package org.wso2.bleagent.util;

import org.wso2.bleagent.constants.Constants;

/**
 * Created by wso2123 on 11/8/16.
 */
public class EddystoneProperties extends BeaconProperties {
    private String namespace;
    private String instance;

    public EddystoneProperties() {
        super(Constants.PROTOCOL_EDDYSTONE);
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
}
