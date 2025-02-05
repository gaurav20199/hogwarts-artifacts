package com.hogwarts.hogwartsartifactonline.artifact.controller;

import com.hogwarts.hogwartsartifactonline.artifact.converter.ArtifactToArtifactDTOConverter;
import com.hogwarts.hogwartsartifactonline.artifact.dto.ArtifactDTO;
import com.hogwarts.hogwartsartifactonline.artifact.entity.Artifact;
import com.hogwarts.hogwartsartifactonline.artifact.service.ArtifactService;
import com.hogwarts.hogwartsartifactonline.utils.Response;
import com.hogwarts.hogwartsartifactonline.utils.constants.ArtifactConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ArtifactController {
    private final ArtifactService artifactService;
    private final ArtifactToArtifactDTOConverter artifactToArtifactDTOConverter;

    public ArtifactController(ArtifactService service,ArtifactToArtifactDTOConverter artifactToArtifactDTOConverter) {
        this.artifactService = service;
        this.artifactToArtifactDTOConverter = artifactToArtifactDTOConverter;
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
    public Response getArtifactById(@PathVariable("id") String artifactId) {
        Artifact artifact = artifactService.getArtifactsByUUID(artifactId);
        ArtifactDTO artifactDTO = artifactToArtifactDTOConverter.convert(artifact);
        return new Response(true,HttpStatus.OK.value(),artifactDTO);
    }

    @PostMapping("/artifacts")
    public Response saveArtifact(@Valid @RequestBody ArtifactDTO artifactDTO) {
        Artifact savedArtifact = artifactService.saveArtifact(artifactDTO);
        ArtifactDTO response = artifactToArtifactDTOConverter.convert(savedArtifact);
        return new Response(true,HttpStatus.CREATED.value(), response);
    }
}
