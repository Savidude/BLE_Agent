package org.wso2.bleagent.util;

import org.wso2.bleagent.util.dto.deviceRegistrationUtils.AgentManagerService;

/**
 * Created by wso2123 on 11/8/16.
 */
public class LocalRegistry {
    private static volatile LocalRegistry mInstance;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
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

    public static LocalRegistry getmInstance() {
        return mInstance;
    }

    public static void setmInstance(LocalRegistry mInstance) {
        LocalRegistry.mInstance = mInstance;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
