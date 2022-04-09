package com.mandiri.library;

import com.mandiri.message.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomException{

    public static void ResponseThrow(String className, String id){
        String message = String.format(ResponseMessage.RESOURCE_NOT_FOUND, className, id);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
    }

}
