package com.hogwarts.hogwartsartifactonline.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    @JsonProperty("success")
    private boolean isSuccess;

    private Integer status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object responsePayload;


    public Response() {
    }

    public Response(boolean isSuccess, Integer status, String message) {
        this.isSuccess = isSuccess;
        this.status = status;
        this.message = message;
    }

    public Response(boolean isSuccess, Integer status, Object responsePayload) {
        this.isSuccess = isSuccess;
        this.status = status;
        this.responsePayload = responsePayload;
    }

    public Response(boolean isSuccess, Integer status, String message, Object responsePayload) {
        this.isSuccess = isSuccess;
        this.status = status;
        this.message = message;
        this.responsePayload = responsePayload;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Object getResponsePayload() {
        return responsePayload;
    }

    public void setResponsePayload(Object responsePayload) {
        this.responsePayload = responsePayload;
    }
}