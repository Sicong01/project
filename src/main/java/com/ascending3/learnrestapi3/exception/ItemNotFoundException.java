package com.ascending3.learnrestapi3.exception;

import java.io.Serial;

public class ItemNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 4475037126204458706L;

    public ItemNotFoundException(){
        super();
    }

    public ItemNotFoundException(String arg0){
        super(arg0);
    }

    public ItemNotFoundException(Throwable cause){
        super(cause);
    }

    public ItemNotFoundException(String errorMsg, Throwable cause){
        super(errorMsg, cause);
    }
}
