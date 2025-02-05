package com.hogwarts.hogwartsartifactonline.artifact.exception;

import com.hogwarts.hogwartsartifactonline.utils.Response;
import com.hogwarts.hogwartsartifactonline.utils.constants.ArtifactConstants;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ArtifactNotFound.class)
    Response handleArtifactNotFoundException(ArtifactNotFound artifactNotFound) {
        return new Response(false, HttpStatus.NOT_FOUND.value(), ArtifactConstants.ARTIFACT_NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Response handleValidationException(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        Map<String, String> map = new HashMap<>(errors.size());
        errors.forEach((error) -> {
            String key = ((FieldError) error).getField();
            String val = error.getDefaultMessage();
            map.put(key, val);
        });
        return new Response(false, HttpStatus.BAD_REQUEST.value(), "Provided arguments are invalid, see data for details.", map);
    }
}
