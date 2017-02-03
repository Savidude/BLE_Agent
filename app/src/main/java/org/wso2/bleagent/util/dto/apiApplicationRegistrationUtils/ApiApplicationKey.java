package org.wso2.bleagent.util.dto.apiApplicationRegistrationUtils;

/**
 * Created by wso2123 on 1/26/17.
 */
public class ApiApplicationKey {
    private String client_id;
    private String client_secret;

    public String getConsumerKey() {
        return this.client_id;
    }

    public void setClient_id(String consumerKey) {
        this.client_id = consumerKey;
    }

    public String getConsumerSecret() {
        return this.client_secret;
    }

    public void setClient_secret(String consumerSecret) {
        this.client_secret = consumerSecret;
    }
}
