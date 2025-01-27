package com.hogwarts.hogwartsartifactonline.artifact.repository;

import com.hogwarts.hogwartsartifactonline.artifact.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtifactRepository extends JpaRepository<Artifact,Integer> {
}
