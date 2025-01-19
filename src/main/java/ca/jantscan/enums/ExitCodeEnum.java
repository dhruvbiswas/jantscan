package ca.jantscan.enums;

import ca.jantscan.constants.Constants;

public enum ExitCodeEnum {
    SUCCESS(Constants.EXIT_CODE_SUCCESS_MESSAGE, Constants.EXIT_CODE_SUCCESS),
    FAIL(Constants.EXIT_CODE_FAIL_MESSAGE, Constants.EXIT_CODE_FAIL);

    private String message;
    private int code;

    ExitCodeEnum(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
