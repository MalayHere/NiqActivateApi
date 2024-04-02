package com.niqactivate.niqactivateapi.utils;

import com.niqactivate.niqactivateapi.exception.DatabaseException;
import com.niqactivate.niqactivateapi.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ErrorResponseBuilder {
    public ResponseEntity<Object> buildErrorResponse(BindingResult bindingResult, String message) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(new ErrorResponse("400", message + " " + String.join(", ", errors)));
    }

  public ResponseEntity<Object> buildErrorResponse(DatabaseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("400", ex.getMessage()));
    }
}
