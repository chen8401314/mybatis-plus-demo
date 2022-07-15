package com.example.demo.handler;

import com.example.demo.common.ExceptionEnum;
import com.example.demo.common.Response;
import com.example.demo.context.CommonException;
import com.example.demo.context.OperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = OperationException.class)
    @ResponseBody
    public Response exceptionGet(OperationException e) {
        return Response.failure(e.getMessage());
    }

}
