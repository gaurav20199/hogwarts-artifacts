package com.hogwarts.hogwartsartifactonline.artifact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hogwarts.hogwartsartifactonline.utils.constants.ArtifactConstants;
import com.hogwarts.hogwartsartifactonline.wizard.dto.WizardDTO;
import jakarta.validation.constraints.NotBlank;

public record ArtifactDTO(
                          @NotBlank(message = ArtifactConstants.ARTIFACT_NAME_REQUIRED)
                          String name,
                          @NotBlank(message = ArtifactConstants.ARTIFACT_DESC_REQUIRED)
                          String description,
                          @NotBlank(message = ArtifactConstants.ARTIFACT_IMAGE_URL_REQUIRED)
                          String imageUrl,
                          @JsonInclude(JsonInclude.Include.NON_NULL)
                          WizardDTO wizard) {
}
