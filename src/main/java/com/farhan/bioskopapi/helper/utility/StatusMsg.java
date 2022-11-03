package com.farhan.bioskopapi.helper.utility;

public class StatusMsg {
    private StatusMsg() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SUCCSESS = "Success";
    public static final String REQUEST_INVALID = "request invalid :{}";
    public static final String ERROR_SERVER = "error from server :{}";
}
