package edu.tienda.core.exceptions;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
