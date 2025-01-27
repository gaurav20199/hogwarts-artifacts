package com.hogwarts.hogwartsartifactonline.artifact.controller;

import com.hogwarts.hogwartsartifactonline.artifact.entity.Artifact;
import com.hogwarts.hogwartsartifactonline.artifact.service.ArtifactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ArtifactController {

    private final ArtifactService artifactService;

    public ArtifactController(ArtifactService service) {
        this.artifactService = service;
    }

    @GetMapping("/artifacts")
    public ResponseEntity<?> getAllArtifacts() {
        Optional<List<Artifact>> artifacts = artifactService.getAllArtifacts();
        if(artifacts.isPresent()) {
            return ResponseEntity.ok(artifacts.get());
        }
        return ResponseEntity.internalServerError().body("Not able to fetch Artifacts");
    }

    @GetMapping("/artifacts/{id}")
    public ResponseEntity<?> getArtifactById(@PathVariable("id") Integer artifactId) {
        Optional<Artifact> artifact = artifactService.getArtifactsById(artifactId);
        if(artifact.isPresent()) {
            return ResponseEntity.ok(artifact.get());
        }
        return ResponseEntity.internalServerError().body("Not able to fetch Artifact");
    }
}
