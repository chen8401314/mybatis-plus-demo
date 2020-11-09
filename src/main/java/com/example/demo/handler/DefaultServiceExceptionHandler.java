package com.example.demo.handler;

import com.example.demo.common.Response;
import com.google.common.base.Throwables;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.util.Set;


/**
 * Created by bradford on 2017/5/31.
 */
@ControllerAdvice
@Slf4j
public class DefaultServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Response> defaultErrorHandler(HttpServletRequest req, Exception e) {
        HttpStatus status = getStatus(req);
        log.error(Throwables.getStackTraceAsString(e));
        String localizeMsg = e.getMessage();
        // If the exception is annotated with @ResponseStatus, extract it
        ResponseStatus ann = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);

        if (ann != null) {
            status = ann.code();
            if (!StringUtils.isEmpty(ann.reason())) {
                localizeMsg = ann.reason();
            }
        }

        return new ResponseEntity<>(Response.failure(status.value(), localizeMsg), status);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseBody
    public Response handleValidationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuilder message = new StringBuilder();
        constraintViolations.forEach(con -> {
            ConstraintViolationImpl<?> cv = (ConstraintViolationImpl<?>) con;
            message.append(cv.getMessage()).append(",");
        });
        return Response.failure(message.substring(0, message.length() - 1));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest req) {
        status = HttpStatus.INTERNAL_SERVER_ERROR;
        StringBuilder sb = new StringBuilder();
        if (e.getBindingResult().hasErrors()) {
            //输出所有校验信息
            for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
                String fieldString = objectError.getCodes()[1].replaceAll("^.*\\.", "");
                Field field;
                try {
                    field = e.getBindingResult().getTarget().getClass().getDeclaredField(fieldString);
                    ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                    if (annotation != null) {
                        sb.append(annotation.value());
                    } else {
                        sb.append(fieldString);
                    }
                } catch (NoSuchFieldException e1) {
                    log.error("no field");
                }

                sb.append(objectError.getDefaultMessage()).append(";");
            }
        }
        ResponseStatus ann = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);

        if (ann != null) {
            status = ann.code();
        }
        return new ResponseEntity<>(Response.failure(status.value(), sb.toString()), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
