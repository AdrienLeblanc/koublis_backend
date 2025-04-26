package com.koublis.configuration.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Reason {
    USERNAME_TAKEN("Username is already taken!"),
    EMAIL_TAKEN("Email is already in use!"),
    USER_NOT_FOUND("User not found!"),
    ROLE_NOT_FOUND("Role not found!"),
    INVALID_CREDENTIALS("Invalid credentials!"),
    USERNAME_OR_PASSWORD_EMPTY("Username or password cannot be empty!"),
    USERNAME_OR_EMAIL_EMPTY("Username or email cannot be empty!");

    private final String message;

}
