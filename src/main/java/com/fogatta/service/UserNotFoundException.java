package com.fogatta.service;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message){
        super(message);
    }

}
