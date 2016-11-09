package org.wso2.bleagent.util.dto.passwordGrantTypeUtils;

import org.wso2.bleagent.util.dto.AccessTokenInfo;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by wso2123 on 10/7/16.
 */
@Path("/token")
public interface TokenIssuerService {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    AccessTokenInfo getToken(@QueryParam("grant_type") String grant, @QueryParam("username") String username,
                             @QueryParam("password") String password);

}
