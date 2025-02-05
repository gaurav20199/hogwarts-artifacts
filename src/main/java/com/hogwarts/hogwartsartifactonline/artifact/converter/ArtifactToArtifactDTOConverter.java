package com.hogwarts.hogwartsartifactonline.artifact.converter;

import com.hogwarts.hogwartsartifactonline.artifact.dto.ArtifactDTO;
import com.hogwarts.hogwartsartifactonline.artifact.entity.Artifact;
import com.hogwarts.hogwartsartifactonline.wizard.converter.WizardToWizardDTOConverter;
import com.hogwarts.hogwartsartifactonline.wizard.dto.WizardDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArtifactToArtifactDTOConverter implements Converter<Artifact, ArtifactDTO> {

    private final WizardToWizardDTOConverter wizardToWizardDTOConverter;

    public ArtifactToArtifactDTOConverter(WizardToWizardDTOConverter wizardToWizardDTOConverter) {
        this.wizardToWizardDTOConverter = wizardToWizardDTOConverter;
    }

    @Override
    public ArtifactDTO convert(Artifact source) {
        WizardDTO wizardDTO = source.getWizard().isPresent()?wizardToWizardDTOConverter.convert(source.getWizard().get()):null;
        return new ArtifactDTO(source.getUuid(),source.getName(),source.getDescription(),source.getDescription(),wizardDTO);
    }

}
