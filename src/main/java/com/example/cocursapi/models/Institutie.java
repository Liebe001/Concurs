package com.example.cocursapi.models;

//import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import jakarta.persistence.*;
@Entity(name="institutie")
@Data
public class Institutie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name ="denumire")
    private String denumire;

    @Column(name= "locatie")
    private String locatie;


    public Institutie(String denumire, String locatie) {
        this.denumire = denumire;
        this.locatie = locatie;

    }

    public Institutie() {

    }
}
