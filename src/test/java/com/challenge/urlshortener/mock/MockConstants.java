package com.challenge.urlshortener.mock;

public final class MockConstants {

    public static final String WWW_GOOGLE_COM = "www.google.com";
    public static final String WWW_UOL_COM = "www.uol.com";
    public static final String SHORTENED_URL_BY_GOOGLE_EXPECTED = "d61ac13e";
    public static final String SHORTENED_URL_NOT_EXIST = "sdf25sdf";
    public static final String URL_REQUEST_JSON_WWW_GOOGLE_COM = "{\"originalUrl\": \"www.google.com\"}";
    public static final String URL_REQUEST_JSON_INVALID = "{\"originalUrl\": \"abcdef\"}";
    public static final String POST_API_URL_SHORTEN = "/api/url/shorten";
    public static final String GET_API_URL = "/api/url/";
    public static final String GET_API_ACCESS_STATISTICS = "/api/access/statistics/";
    public static final String APPLICATION_JSON = "application/json";
    public static final Integer STATISTICS_10 = 10;
    public static final Integer STATISTICS_5 = 5;
    public static final Double STATISTICS_2 = 2.0;
    public static final String MESSAGE_RESPONSE_SHORTENED_URL_HAS_ALREADY_BEEN_CREATED = "The shortened URL for [ www.google.com ] has already been created. Shortened Url is this: d61ac13e";
    public static final String MESSAGE_URL_RESPONSE_JSON_ORIGINAL_URL_MUST_BE_A_VALID_URL = "{\"originalUrl\":\"Original URL must be a valid URL\"}";
    public static final String MESSAGE_URL_RESPONSE_JSON_TOTAL_ACCESS_AVERAGE_ACCESS_PER_DAY = "{\"totalAccesses\":4,\"averageAccessPerDay\":1.3333333333333333}";
    public static final String MESSAGE_RESPONSE_ENTITY_NOT_FOUND = "NOT FOUND - Entity not found";
    public static final String MESSAGE_RESPONSE_SHORTENED_URL_NOT_FOUND = "NOT FOUND - Shortened Url not found";
    private MockConstants() {}

}
