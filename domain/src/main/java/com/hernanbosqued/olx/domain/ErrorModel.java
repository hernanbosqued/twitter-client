package com.hernanbosqued.olx.domain;

import java.util.List;

public class ErrorModel {

    public List<Error> errors;

    public static class Error {
        public Error( int code, String message){
            this.code = code;
            this.message = message;
        }

        public int code;
        public String message;
        public String label;
    }
}
