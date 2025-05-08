package com.wallet_service.exception;

public class WalletNotFoundException extends RuntimeException{
    private final String code;

    public WalletNotFoundException(String code, String message){
        super(message);
        this.code = code;
    }

    public WalletNotFoundException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
