package com.hogwarts.hogwartsartifactonline.artifact.service;

import com.hogwarts.hogwartsartifactonline.artifact.entity.Artifact;
import com.hogwarts.hogwartsartifactonline.artifact.exception.ArtifactNotFound;
import com.hogwarts.hogwartsartifactonline.artifact.repository.ArtifactRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    public ArtifactService(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    public Optional<List<Artifact>> getAllArtifacts() {
        return Optional.ofNullable(artifactRepository.findAll());
    }

    public Artifact getArtifactsById(Integer artifactId) throws ArtifactNotFound{
        return artifactRepository.findById(artifactId).
                orElseThrow(() -> new ArtifactNotFound(artifactId));
    }
}
