package com.example.thuva.api.security;

public class Contants {
    public static final String ENCODER_ID = "bcrypt";
    public static final String API_URL_PREFIX = "/api/vi/**";
    public static final String H2_URL_PREFIX = "/h2-console/**";
    public static final String SIGNUP_URL = "/api/v1/users";
    public static final String TOKEN_URL = "/api/v1/auth/token";
    public static final String REFRESH_URL = "/api/v1/auth/token/refresh";
    public static final String PRODUCTS_URL = "/api/v1/products/**";
    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String SECRET_KEY = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 900_000;
    public static final String ROLE_CLAIM = "roles";
    public static final String AUTHORITY_PREFIX = "ROLE_";
}
