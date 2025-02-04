package com.hogwarts.hogwartsartifactonline.wizard.converter;

import com.hogwarts.hogwartsartifactonline.wizard.dto.WizardDTO;
import com.hogwarts.hogwartsartifactonline.wizard.entity.Wizard;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WizardToWizardDTOConverter implements Converter<Wizard, WizardDTO> {
    @Override
    public WizardDTO convert(Wizard source) {
        return new WizardDTO(source.getName(),source.getNumberOfArtifacts());
    }
}
