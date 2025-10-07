package com.cristian.springboot.springmvc.app.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "franchises")
public class Franchise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "franchise", cascade = CascadeType.ALL)
    private List<Branch> braches = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Branch> getBraches() {
        return braches;
    }

    public void setBraches(List<Branch> braches) {
        this.braches = braches;
    }
}
