package com.algaworks.algalog.domain.exception;

public class NotFoundException extends  DomainException{

    private static final long SerialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }

}
