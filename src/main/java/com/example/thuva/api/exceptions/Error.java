package com.example.thuva.api.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

@Getter
public class Error {
    private static final long serialVersionUID = 1L;

    @Setter
    private String errorCode;
    @Setter
    private String message;
    @Setter
    private Integer status;
    private String url = "Not available";
    private String reqMethod = "Not available";

    public Error setUrl(String url) {
        if (Strings.isNotBlank(url)){
            this.url = url;
        }
        return this;
    }

    public Error setReqMethod(String method) {
        if (Strings.isNotBlank(method)) {
            this.reqMethod = method;
        }
        return this;
    }
}
