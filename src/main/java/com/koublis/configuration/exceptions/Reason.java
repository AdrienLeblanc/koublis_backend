package com.koublis.configuration.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Reason {
    USER_NOT_FOUND("User not found!"),
    SECRET_KEY_NOT_FOUND("Secret key not found!");

    private final String message;

}
