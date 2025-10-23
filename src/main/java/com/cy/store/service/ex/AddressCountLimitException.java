package com.cy.store.service.ex;

/**收货地址总数超出上限的异常 规定最大20条*/
public class AddressCountLimitException extends ServiceException{
    public AddressCountLimitException() {
    }

    public AddressCountLimitException(String message) {
        super(message);
    }

    public AddressCountLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountLimitException(Throwable cause) {
        super(cause);
    }

    public AddressCountLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
