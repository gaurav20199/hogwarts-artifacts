package com.hogwarts.hogwartsartifactonline.artifact.service;

import com.hogwarts.hogwartsartifactonline.artifact.converter.ArtifactDTOToArtifactConverter;
import com.hogwarts.hogwartsartifactonline.artifact.dto.ArtifactDTO;
import com.hogwarts.hogwartsartifactonline.artifact.entity.Artifact;
import com.hogwarts.hogwartsartifactonline.artifact.exception.ArtifactNotFound;
import com.hogwarts.hogwartsartifactonline.artifact.repository.ArtifactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    private final ArtifactDTOToArtifactConverter artifactDTOToArtifactConverter;

    public ArtifactService(ArtifactRepository artifactRepository,
                           ArtifactDTOToArtifactConverter artifactDTOToArtifactConverter) {
        this.artifactRepository = artifactRepository;
        this.artifactDTOToArtifactConverter = artifactDTOToArtifactConverter;
    }

    public Optional<List<Artifact>> getAllArtifacts() {
        return Optional.ofNullable(artifactRepository.findAll());
    }

    public Artifact getArtifactsByUUID(String artifactId) throws ArtifactNotFound{
        return artifactRepository.findByUuid(artifactId).
                orElseThrow(() -> new ArtifactNotFound(artifactId));
    }

    public Artifact saveArtifact(ArtifactDTO artifactDTO) {
        Artifact artifact = artifactDTOToArtifactConverter.convert(artifactDTO);
        return artifactRepository.save(artifact);
    }

    public Artifact updateArtifactByUUID(ArtifactDTO artifactDTO,String artifactUUID) {
        Artifact artifactFromDB = artifactRepository.findByUuid(artifactUUID)
                .orElseThrow(() -> new ArtifactNotFound(artifactUUID));
        BeanUtils.copyProperties(artifactDTO,artifactFromDB,"uuid");
        artifactFromDB.setUuid(artifactFromDB.getUuid());
        artifactFromDB.setId(artifactFromDB.getId());
        return artifactRepository.save(artifactFromDB);
    }

    public void deleteArtifactByUUID(String artifactUUID) throws ArtifactNotFound {
        Artifact artifact = artifactRepository.findByUuid(artifactUUID).orElseThrow(() -> new ArtifactNotFound(artifactUUID));
        artifactRepository.deleteByUuid(artifact.getUuid());
    }

}
