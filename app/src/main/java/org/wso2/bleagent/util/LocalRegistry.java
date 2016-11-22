package org.wso2.bleagent.util;

import org.wso2.bleagent.util.dto.deviceRegistrationUtils.AgentManagerService;

public class LocalRegistry {
    private static volatile LocalRegistry mInstance;

    private String url;
    private String username;
    private String password;
    private String profile;

    private String deviceId;

    private AgentManagerService managerService;

    private LocalRegistry(){
    }

    public static LocalRegistry getInstance(){
        if(mInstance == null){
            Class c = LocalRegistry.class;
            synchronized (c){
                if(mInstance == null){
                    mInstance = new LocalRegistry();
                }
            }
        }
        return mInstance;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public AgentManagerService getManagerService() {
        return managerService;
    }

    public void setManagerService(AgentManagerService managerService) {
        this.managerService = managerService;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
