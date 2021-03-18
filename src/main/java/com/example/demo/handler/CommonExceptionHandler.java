package com.example.demo.handler;

import com.example.demo.common.Consts;
import com.example.demo.common.ExceptionEnum;
import com.example.demo.common.Response;
import com.example.demo.context.CommonException;
import com.example.demo.context.NoLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    /**
     * @param e
     * @return
     */
    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public Response exceptionGet(CommonException e) {
        return Response.failure(e.getMessage());
    }


    @ExceptionHandler(value = NoLoginException.class)
    @ResponseBody
    public Response exceptionGet(NoLoginException e) {
        return Response.failure(ExceptionEnum.NOT_LOGIN);
    }

}
