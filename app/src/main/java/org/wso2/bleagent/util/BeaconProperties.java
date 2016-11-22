package org.wso2.bleagent.util;

public abstract class BeaconProperties {
    protected String protocol;
    private long connectedTime;

    public BeaconProperties(String protocol){
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public long getConnectedTime() {
        return connectedTime;
    }

    public void setConnectedTime(long connectedTime) {
        this.connectedTime = connectedTime;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
