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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.InstanceOfAssertFactories.COLLECTION;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArtifactServiceTest {

    @Mock // instructs mockito to create mock of repository
    ArtifactRepository repository;

    @InjectMocks // when test is started mockito will inject mock(repository) to the service
    ArtifactService artifactService;

    List<Artifact> artifactList;

    @BeforeEach
    void setUp() {
        this.artifactList = new ArrayList<>();
        Artifact a1 = new Artifact();
        a1.setId(100);
        a1.setName("Deluminator");
        a1.setDescription("A Deluminator is a device invented by Albus Dumbledore that resembles a cigarette lighter. It is used to remove or absorb (as well as return) the light from any light source to provide cover to the user.");
        a1.setImageUrl("ImageUrl");
        this.artifactList.add(a1);

        Artifact a2 = new Artifact();
        a2.setId(200);
        a2.setName("Invisibility Cloak");
        a2.setDescription("An invisibility cloak is used to make the wearer invisible.");
        a2.setImageUrl("ImageUrl");
        this.artifactList.add(a2);
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
        //given
        BDDMockito.given(repository.findAll()).willReturn(this.artifactList);
        //when
        Optional<List<Artifact>> optionalArtifacts = artifactService.getAllArtifacts();
        //then
        assertThat(optionalArtifacts.isPresent());
        assertThat(optionalArtifacts.get().size()).isEqualTo(this.artifactList.size());

        //verifying that findAll is called exactly one time
        verify(repository,times(1)).findAll();
    }

    @Test
    void testAllArtifactsFail() {
        //given
        BDDMockito.given(repository.findAll()).willReturn(Collections.emptyList());
        //when
        Optional<List<Artifact>> optionalArtifacts = artifactService.getAllArtifacts();
        //then
        assertThat(optionalArtifacts.get().size()).isNotEqualTo(this.artifactList.size());

        //verifying that findAll is called exactly one time
        verify(repository,times(1)).findAll();

    }
}