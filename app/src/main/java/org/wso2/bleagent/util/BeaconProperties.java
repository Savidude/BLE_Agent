package org.wso2.bleagent.util;

/**
 * Created by wso2123 on 11/8/16.
 */
public abstract class BeaconProperties {
    protected String protocol;

    public BeaconProperties(String protocol){
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
