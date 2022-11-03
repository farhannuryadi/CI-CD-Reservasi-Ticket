package com.farhan.bioskopapi.helper.utility;

public class StatusCode {
    private StatusCode() {
        throw new IllegalStateException("Utility class");
    }

    public static final Integer OK = 200;
    public static final Integer BAD_REQUEST = 400;
    public static final Integer INTERNAL_ERROR = 500;
}
