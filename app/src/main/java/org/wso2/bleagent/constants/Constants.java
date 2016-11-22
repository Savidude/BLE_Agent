package org.wso2.bleagent.constants;

public class Constants {
    public static final String DEVICE_TYPE = "bleAgent";


    public final static String REGISTER_CONTEXT = "/bleAgent";
    public final static String DCR_CONTEXT = "/dynamic-client-web";
    public final static String TOKEN_ISSUER_CONTEXT = "/oauth2";
    public final static String API_APPLICATION_REGISTRATION_CONTEXT = "/api-application-registration";

    public static final String MANAGER_PORT = "8080";
    public static final String RESOURCE_REQUEST_ENDPOINT = "/request_endpoint";
    public static final String RESOURCE_DOWNLOAD = "/download";

    public static final String PROTOCOL_IBEACON = "iBeacon";
    public static final String PROTOCOL_EDDYSTONE = "eddystone";

    public static final String ACTION_IMAGE = "image";
    public static final String ACTION_URL = "url";
    public static final String ACTION_ENDPOINT = "endpoint";

    public final class Request {
        public final static String REQUEST_SUCCESSFUL = "200";
        public final static int MAX_ATTEMPTS = 2;
    }
}
