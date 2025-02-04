package com.hogwarts.hogwartsartifactonline.artifact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hogwarts.hogwartsartifactonline.wizard.dto.WizardDTO;

public record ArtifactDTO(Integer id,String name, String description, String imageUrl,
                          @JsonInclude(JsonInclude.Include.NON_NULL)
                          WizardDTO wizard) {
}
