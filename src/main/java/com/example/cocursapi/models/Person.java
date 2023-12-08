package com.example.cocursapi.models;

//import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.sql.Date;
import jakarta.persistence.*;
@Entity(name="persons")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "sex")
    private String sex;


    @Column (name = "birth_day")
    private Date birthDay;

    @NaturalId
    @Column(name = "contact")
    private String contact;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.ALL})
    @JoinColumn(name = "id_dance",foreignKey = @ForeignKey(name = "fk_persons_dance"))
    private Dance dance;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.ALL})
    @JoinColumn(name = "id_institutie",foreignKey = @ForeignKey(name = "fk_persons_institutie"))
    private Institutie institutie;


    public void setDanceIds(Long idByName, String dance) {
        this.dance.setId(idByName);
        this.dance.setName(dance);
    }

    public void setInstitutieIds( String denumire, String locatie) {

        this.institutie.setDenumire(denumire);
        this.institutie.setLocatie(locatie);
    }
}