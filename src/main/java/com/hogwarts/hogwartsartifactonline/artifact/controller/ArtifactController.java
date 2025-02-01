package com.hogwarts.hogwartsartifactonline.artifact.controller;

import com.hogwarts.hogwartsartifactonline.artifact.entity.Artifact;
import com.hogwarts.hogwartsartifactonline.artifact.exception.ArtifactNotFound;
import com.hogwarts.hogwartsartifactonline.artifact.service.ArtifactService;
import com.hogwarts.hogwartsartifactonline.utils.Response;
import com.hogwarts.hogwartsartifactonline.utils.constants.ArtifactConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ArtifactController {

    private final ArtifactService artifactService;

    public ArtifactController(ArtifactService service) {
        this.artifactService = service;
    }

    @GetMapping("/artifacts")
    public Response getAllArtifacts() {
        Optional<List<Artifact>> artifacts = artifactService.getAllArtifacts();
        if(artifacts.isPresent()) {
            return new Response(true,HttpStatus.OK.value(),artifacts.get());
        }
        return new Response(false,HttpStatus.INTERNAL_SERVER_ERROR.value(), ArtifactConstants.ERROR_FETCHING_ARTIFACTS);
    }

    @GetMapping("/artifact/{id}")
    public Response getArtifactById(@PathVariable("id") Integer artifactId) {
        try {
            Artifact artifact = artifactService.getArtifactsById(artifactId);
            return new Response(true,HttpStatus.OK.value(),artifact);
        }catch (ArtifactNotFound e) {
            return new Response(false,HttpStatus.NOT_FOUND.value(), ArtifactConstants.ARTIFACT_NOT_FOUND);
        }
    }
}
