package com.avanade.dio.jwt.security;

public class SecurityConstants {

    public static final String SECRET = "SecretKeyTokenJWTs";
    public static final long EXPIRATION_TIME = 864_300_00; //10 days
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/login";
    public static final String STATUS_URL = "/status";

}
