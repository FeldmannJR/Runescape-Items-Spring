package dev.feldmann.runescapeitems.validation;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ErrorValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FieldErrorMessage> handle(MethodArgumentNotValidException ex) {

        List<FieldErrorMessage> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(new FieldErrorMessage(fieldError.getField(),fieldError.getDefaultMessage()));
        }
        return errors;
    }
}
