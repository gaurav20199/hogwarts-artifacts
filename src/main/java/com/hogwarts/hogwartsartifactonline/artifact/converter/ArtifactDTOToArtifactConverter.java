package com.hogwarts.hogwartsartifactonline.artifact.converter;

import com.hogwarts.hogwartsartifactonline.artifact.dto.ArtifactDTO;
import com.hogwarts.hogwartsartifactonline.artifact.entity.Artifact;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArtifactDTOToArtifactConverter implements Converter<ArtifactDTO, Artifact> {

    @Override
    public Artifact convert(ArtifactDTO source) {
        Artifact artifact = new Artifact();
        artifact.setImageUrl(source.imageUrl());
        artifact.setName(source.name());
        artifact.setDescription(source.description());
        return artifact;
    }
}
