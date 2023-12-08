package com.example.cocursapi.DTO;

import com.example.cocursapi.models.Institutie;

import java.sql.Date;

public record PersonDTO(
        String firstName,
        String lastName,

        String sex,
        Date birthDay,
        String contact,
        String dance,
        InstitutieDTO institutie

) {
}
