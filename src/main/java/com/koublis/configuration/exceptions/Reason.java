package com.koublis.configuration.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Reason {
    USER_NOT_FOUND("User not found!"),
    SECRET_KEY_NOT_FOUND("Secret key not found!"),
    FAILED_TO_LOAD_CATALOG_WINES("Failed to load catalog wines"),
    FAILED_TO_PARSE_CATALOG_WINES("Failed to parse catalog wines"),
    INVALID_JSON_FILE_START("The JSON file must start with an array."),
    ;

    private final String message;

}
