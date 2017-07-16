package com.hernanbosqued.olx.domain.model;

@SuppressWarnings("unused")
public class ErrorModel {
    public ErrorModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public final int code;
    public final String message;
}
