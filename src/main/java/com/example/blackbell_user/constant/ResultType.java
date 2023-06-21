package com.example.blackbell_user.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResultType {

    SUCCESS(HttpStatus.OK, "0000", "success"),
    CREATE_ACCOUNT_SUCCESS(HttpStatus.CREATED, "0001", "Account is successfully created"),

    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "9000", "system error");

    private final HttpStatus status;
    private final String code;
    private final String desc;
}
