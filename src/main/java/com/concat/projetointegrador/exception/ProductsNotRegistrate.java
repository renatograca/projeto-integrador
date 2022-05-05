package com.concat.projetointegrador.exception;

public class ProductsNotRegistrate extends RuntimeException {

    private static final long serialVersionUID = 1234L;

    public ProductsNotRegistrate() {
    }

    public ProductsNotRegistrate(String message) {
        super(message);
    }

    public ProductsNotRegistrate(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductsNotRegistrate(Throwable cause) {
        super(cause);
    }

    public ProductsNotRegistrate(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
