package com.hogwarts.hogwartsartifactonline.artifact.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hogwarts.hogwartsartifactonline.wizard.entity.Wizard;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Optional;

@Entity
public class Artifact implements Serializable {
    @Id
    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
    @ManyToOne
    private Wizard wizard;

    public Optional<Wizard> getWizard() {
        return Optional.ofNullable(this.wizard);
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", wizard=" + wizard +
                '}';
    }
}
