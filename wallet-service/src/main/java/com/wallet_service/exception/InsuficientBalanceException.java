package com.wallet_service.exception;

public class InsuficientBalanceException extends RuntimeException{

    private final String code;

    public InsuficientBalanceException(String code, String message){
        super(message);
        this.code = code;
    }

    public InsuficientBalanceException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
