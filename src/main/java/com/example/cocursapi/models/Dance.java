package com.example.cocursapi.models;


import lombok.Data;
import org.hibernate.annotations.NaturalId;

import jakarta.persistence.*;


@Entity(name="dance")
@Data
public class Dance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @NaturalId
    @Column(name = "name")
    private String name;


    public Dance(String dance) {
        this.name = dance;
    }

    public Dance() {

    }
}
