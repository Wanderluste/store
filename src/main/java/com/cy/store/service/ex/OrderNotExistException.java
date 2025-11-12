package com.cy.store.service.ex;

public class OrderNotExistException extends ServiceException{
    public OrderNotExistException() {
        super();
    }

    public OrderNotExistException(String message) {
        super(message);
    }

    public OrderNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotExistException(Throwable cause) {
        super(cause);
    }

    protected OrderNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
