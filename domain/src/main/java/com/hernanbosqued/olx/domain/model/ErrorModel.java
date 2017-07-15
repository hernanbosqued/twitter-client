package com.hernanbosqued.olx.domain.model;

public class ErrorModel {
    public ErrorModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code;
    public String message;
}
