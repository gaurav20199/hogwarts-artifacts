package com.hogwarts.hogwartsartifactonline.artifact.exception;

public class ArtifactNotFound extends RuntimeException{

    public ArtifactNotFound(String msg, int artifactId) {
        super(msg+":"+artifactId);
    }

    public ArtifactNotFound(String artifactId) {
        super("Could not find artifact with id::"+artifactId);
    }
    
}
