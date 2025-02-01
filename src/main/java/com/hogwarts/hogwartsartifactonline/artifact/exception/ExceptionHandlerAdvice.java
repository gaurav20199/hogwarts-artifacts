package com.hogwarts.hogwartsartifactonline.artifact.exception;

import com.hogwarts.hogwartsartifactonline.utils.Response;
import com.hogwarts.hogwartsartifactonline.utils.constants.ArtifactConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ArtifactNotFound.class)
    Response handleArtifactNotFoundException(ArtifactNotFound artifactNotFound) {
        return new Response(false, HttpStatus.NOT_FOUND.value(), ArtifactConstants.ARTIFACT_NOT_FOUND);
    }
}
