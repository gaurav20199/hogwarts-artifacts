package com.hogwarts.hogwartsartifactonline.wizard;

import com.hogwarts.hogwartsartifactonline.wizard.entity.Wizard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WizardRepository extends JpaRepository<Wizard,Integer> {
}
