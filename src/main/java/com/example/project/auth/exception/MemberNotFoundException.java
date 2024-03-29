package com.example.project.auth.exception;

public class MemberNotFoundException extends IllegalArgumentException {
    public MemberNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
