package com.davimc.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1L;

    private List<FIeldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FIeldMessage> getErrors() {
        return errors;
    }

    public void setErrors(String field, String message) {
        errors.add(new FIeldMessage(field,message));
    }
}
