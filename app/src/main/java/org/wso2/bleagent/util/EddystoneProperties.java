package org.wso2.bleagent.util;

import org.wso2.bleagent.constants.Constants;

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

    @Override
    public boolean equals(Object obj) {
        EddystoneProperties properties = (EddystoneProperties)obj;
        boolean isNamespaceEqual = this.getNamespace().equals(properties.getNamespace());
        boolean isInstanceEqual = this.getInstance().equals(properties.getInstance());
        return isNamespaceEqual && isInstanceEqual;
    }
}
