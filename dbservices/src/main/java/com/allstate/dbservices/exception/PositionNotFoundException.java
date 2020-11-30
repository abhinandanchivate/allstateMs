package com.allstate.dbservices.exception;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(Integer id) {
        super("Could not find position " + id);
    }
}