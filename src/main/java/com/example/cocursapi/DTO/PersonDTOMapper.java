package com.example.cocursapi.DTO;

import com.example.cocursapi.models.Person;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class PersonDTOMapper implements Function<Person, PersonDTO> {

    @Override
    public PersonDTO apply(Person persons) {
        return new PersonDTO(
                persons.getFirstName(),
                persons.getLastName(),
                persons.getSex(),
                persons.getBirthDay(),
                persons.getContact(),
                persons.getDance().getName(),
                new InstitutieDTO(
                        persons.getInstitutie().getDenumire(),
                        persons.getInstitutie().getLocatie()
                )

        );
    }
}
