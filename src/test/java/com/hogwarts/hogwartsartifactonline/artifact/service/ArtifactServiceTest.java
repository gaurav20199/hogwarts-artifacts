package com.hogwarts.hogwartsartifactonline.artifact.service;

import com.hogwarts.hogwartsartifactonline.artifact.entity.Artifact;
import com.hogwarts.hogwartsartifactonline.artifact.exception.ArtifactNotFound;
import com.hogwarts.hogwartsartifactonline.artifact.repository.ArtifactRepository;
import com.hogwarts.hogwartsartifactonline.wizard.entity.Wizard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArtifactServiceTest {

    @Mock // instructs mockito to create mock of repository
    ArtifactRepository repository;

    @InjectMocks // when test is started mockito will inject mock(repository) to the service
    ArtifactService artifactService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByIdSuccess() {
        // Given
        // Arrange Inputs,Targets. Defining behaviour of Mock object artifactRepository
        Artifact artifact = new Artifact();
        artifact.setId(123);
        artifact.setName("Test Artifact");
        artifact.setDescription("Test Artifact Description");
        artifact.setImageUrl("dummy image");

        Wizard wizard = new Wizard();
        wizard.setName("Test Wizard");

        artifact.setWizard(wizard);

        // defining behaviour of mock object
        BDDMockito.given(repository.findById(123)).willReturn(Optional.of(artifact));

        // When
        // Act on Target Behaviour(find by id method)
        Artifact returnedArtifact = artifactService.getArtifactsById(123);

        //Done
        // Assert expected outcomes
        assertThat(returnedArtifact.getId()).isEqualTo(artifact.getId());
        assertThat(returnedArtifact.getName()).isEqualTo(artifact.getName());
        assertThat(returnedArtifact.getDescription()).isEqualTo(artifact.getDescription());
        assertThat(returnedArtifact.getName()).isEqualTo(artifact.getName());
        assertThat(returnedArtifact.getImageUrl()).isEqualTo(artifact.getImageUrl());

        //verifying that findById is called exactly one time
        verify(repository,times(1)).findById(123);
    }

    @Test
    void testFindByIdFail() {

        BDDMockito.given(repository.findById(Mockito.any(Integer.class))).willReturn(Optional.empty());

        // When

        Throwable thrown = catchThrowable(() -> {
            artifactService.getArtifactsById(123);
        });

        // Then
        assertThat(thrown).
                isInstanceOf(ArtifactNotFound.class).
                hasMessage("Could not find Artifact with id::123 :(");

        //verifying that findById is called exactly one time
        verify(repository,times(1)).findById(123);

    }

    @Test
    void testAllArtifactsSuccess() {
    }
}