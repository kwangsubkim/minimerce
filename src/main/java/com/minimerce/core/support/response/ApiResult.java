package com.minimerce.core.support.response;

/**
 * Created by gemini on 23/05/2017.
 */
public interface ApiResult {
    int getStatus();
    int getCode();
    String getMessage();
    boolean getIsError();
}
