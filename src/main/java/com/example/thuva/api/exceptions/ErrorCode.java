package com.example.thuva.api.exceptions;

import lombok.Getter;

public enum ErrorCode {

    // Internal Errors
    GENERIC_ERROR("CODE-0001", "The system is unable to complete the request."),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED("CODE-0002", "Requestd media type is not supported."),
    HTTP_MESSAGE_NOT_WRITABLE("CODE-0003", "Missing 'Accept' header"),
    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE("CODE-0004", "Requested 'Accept' header value is not supported"),
    JSON_PARSE_ERROR("CODE-0005", "Make sure request payload should be a valid JSON object"),
    HTTP_MESSAGE_NOT_READABLE("CODE-0006", "Make sure request payload should be a valid JSON or XML object.");

    @Getter
    private String errCode;
    @Getter
    private String errMsgKey;

    ErrorCode(final String errCode, final String errMsgKey) {
        this.errCode = errCode;
        this.errMsgKey = errMsgKey;
    }
}
