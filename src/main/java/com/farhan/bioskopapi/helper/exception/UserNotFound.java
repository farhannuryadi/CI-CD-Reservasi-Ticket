package com.farhan.bioskopapi.helper.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message){
        super(message);
    }
}
