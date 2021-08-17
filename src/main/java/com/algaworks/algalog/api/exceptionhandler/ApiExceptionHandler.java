package com.algaworks.algalog.api.exceptionhandler;

import com.algaworks.algalog.domain.exception.DomainException;
import com.algaworks.algalog.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    public ErrorObject errorObjectFactory(Integer status, OffsetDateTime dateTime, String description, List<ErrorObject.Field> fieldList){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatus(status);
        errorObject.setDateTime(dateTime);
        errorObject.setDescription(description);
        errorObject.setFieldList(fieldList);
        return errorObject;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorObject.Field> fieldList = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            String name = ((FieldError) error).getField();
            String defaultMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            fieldList.add(new ErrorObject.Field(name,defaultMessage));
        }
        String message = "Um ou mais campos não foram preenchidos corretamente";
        ErrorObject errorObject = errorObjectFactory(status.value(), OffsetDateTime.now(), message, fieldList);

        return handleExceptionInternal(ex, errorObject, headers, status, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomainException(DomainException exception, WebRequest request){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorObject errorObject = errorObjectFactory(httpStatus.value(), OffsetDateTime.now(), exception.getMessage(), null);
        return handleExceptionInternal(exception, errorObject, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception, WebRequest request){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErrorObject errorObject = errorObjectFactory(httpStatus.value(), OffsetDateTime.now(), exception.getMessage(), null);
        return handleExceptionInternal(exception, errorObject, new HttpHeaders(), httpStatus, request);
    }

}
