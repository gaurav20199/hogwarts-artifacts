package com.hogwarts.hogwartsartifactonline.wizard;

import com.hogwarts.hogwartsartifactonline.artifact.entity.Artifact;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
@Entity
public class Wizard {
    @Id
    private String id;
    private String name;
    // bidirectional one-to-many relationship
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "owner")
    private List<Artifact> artifacts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
