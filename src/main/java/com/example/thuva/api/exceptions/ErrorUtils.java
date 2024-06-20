package com.example.thuva.api.exceptions;

public class ErrorUtils {
    private ErrorUtils() {
    }

    public static Error createError(final String errMsgKey, final String errCode, final Integer httpStatusCode) {
        Error error = new Error();
        error.setErrorCode(errCode);
        error.setMessage(errMsgKey);
        error.setStatus(httpStatusCode);
        return error;
    }
}
