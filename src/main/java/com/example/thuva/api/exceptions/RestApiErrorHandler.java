package com.example.thuva.api.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class RestApiErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(RestApiErrorHandler.class);
    private final MessageSource messageSource;

    @Autowired
    public RestApiErrorHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(HttpServletRequest request, Exception ex, Locale locale){
        ex.printStackTrace(); // TODO: Should be kept only for development
        Error error = ErrorUtils.createError(
                ErrorCode.GENERIC_ERROR.getErrMsgKey(),
                        ErrorCode.GENERIC_ERROR.getErrCode(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Error> handleHttpMediaTypeNotSupportedException(HttpServletRequest request,
                                                                          HttpMediaTypeNotSupportedException ex,
                                                                          Locale locale) {
        ex.printStackTrace(); // TODO: Should be kept only for development
        Error error = ErrorUtils.createError(ErrorCode.HTTP_MEDIA_TYPE_NOT_SUPPORTED.getErrMsgKey(),
                ErrorCode.HTTP_MEDIA_TYPE_NOT_SUPPORTED.getErrCode(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        log.info("HttpMediaTypeNotSupportedException :: request.getMethod(): {}", request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<Error> handleHttpMessageNotWritableException(HttpServletRequest request,
                                                                       HttpMessageNotWritableException ex,
                                                                       Locale locale) {
        Error error = ErrorUtils.createError((ErrorCode.HTTP_MESSAGE_NOT_WRITABLE.getErrMsgKey()),
                ErrorCode.HTTP_MESSAGE_NOT_READABLE.getErrCode(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        log.info("HttpMessageNotWritableException :: request.getMethod(): {}", request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Error> handleJsonParseException(HttpServletRequest request,
                                                          JsonParseException ex,
                                                          Locale locale) {
        Error error = ErrorUtils.createError((ErrorCode.JSON_PARSE_ERROR.getErrMsgKey()),
                ErrorCode.JSON_PARSE_ERROR.getErrCode(),
                HttpStatus.NOT_ACCEPTABLE.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        log.info("JsonParseException :: request.getMethod(): {}", request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
