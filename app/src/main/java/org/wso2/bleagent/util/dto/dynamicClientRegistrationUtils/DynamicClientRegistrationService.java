package org.wso2.bleagent.util.dto.dynamicClientRegistrationUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/register")
public interface DynamicClientRegistrationService {
    /**
     * This method is used to register an Oauth application.
     *
     * @param profile contains the necessary attributes that are
     *                needed in order to register an app.
     * @return Status 200 if success including consumerKey and consumerSecret.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    OAuthApplicationInfo register(RegistrationProfile profile);
}
