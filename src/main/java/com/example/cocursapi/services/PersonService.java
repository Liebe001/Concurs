package com.example.cocursapi.services;

import com.example.cocursapi.DTO.DanceAvgDTO;
import com.example.cocursapi.DTO.PersonDTO;
import com.example.cocursapi.DTO.PersonDTOMapper;
import com.example.cocursapi.repositorys.PersonRepository;


import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.cocursapi.models.*;

import java.sql.Date;
import java.util.*;

@Service
public class PersonService {


    private PersonRepository personsRepository;

    private PersonDTOMapper personDTOMapper;


    private String ceva;

    private  InstitutieService institutieService;

    private final DanceServices danceServices;

    @Autowired
    public PersonService(PersonRepository personsRepository, PersonDTOMapper personDTOMapper, InstitutieService institutieService, DanceServices danceServices) {
        this.personsRepository = personsRepository;
        this.personDTOMapper = personDTOMapper;
        this.institutieService = institutieService;
        this.danceServices = danceServices;
    }

    public List<PersonDTO> getAll(){

        return personsRepository
                .findAll()
                .stream()
                .map(personDTOMapper)
                .toList();


    }
    public PersonDTO getPersonByContact(String contact){

        return personsRepository.getPersonsByContact(contact)
                .map(personDTOMapper)
                .orElseThrow(
                        ()->new NoSuchElementException("Persons with contact [%s] not found ".formatted(contact))
                );

    }

    public PersonDTO getPersonByDance(String dance){

        return personsRepository.getPersonsByDance(dance)
                .map(personDTOMapper)
                .orElseThrow(
                        ()->new NoSuchElementException("Persons that dance %s not found".formatted(dance))
                );

    }

    public void createPerson(PersonDTO newPerson){
        if(personsRepository.getPersonsByContact(newPerson.contact()).isEmpty()){
            Person person = new Person();
            person.setFirstName(newPerson.firstName());
            person.setLastName(newPerson.lastName());
            person.setSex(newPerson.sex());
            person.setBirthDay(newPerson.birthDay());
            person.setContact(newPerson.contact());
            Dance newDance=danceServices.getByName(newPerson.dance());
            if(newDance!=null){
                person.setDance(newDance);
            }
            else{
                danceServices.createDance(newPerson.dance());
                person.setDance(danceServices.getByName(newPerson.dance()));
            }
            Institutie newInstitutie = institutieService.getByName(newPerson.institutie().denumire(),newPerson.institutie().locatie());
            if(newInstitutie!=null){
                person.setInstitutie(newInstitutie);

            }else{
                institutieService.createInstitutie(newPerson.institutie().denumire(),newPerson.institutie().locatie());
                person.setInstitutie(institutieService.getByName(newPerson.institutie().denumire(),newPerson.institutie().locatie()));
            }
            personsRepository.saveAndFlush(person);


        }




    }



    @Transactional
    public void updatePerson(String contact,PersonDTO updatedPerson ){
        Person person = personsRepository.getPersonsByContact(contact).orElseThrow(
                ()->new NoSuchElementException("Persons with contact [%s] not found ".formatted(contact))
        );
        System.out.println("UpdatedPerson: "+updatedPerson);
        person.setFirstName(updatedPerson.firstName());
        person.setLastName(updatedPerson.lastName());
        person.setSex(updatedPerson.sex());
        person.setBirthDay(updatedPerson.birthDay());
        person.setContact(updatedPerson.contact());
        if(!person.getDance().getName().equals(updatedPerson.dance())){
            Dance newDance=danceServices.getByName(updatedPerson.dance());
            if(newDance!=null){
                person.setDance(newDance);
            }
            else{
                danceServices.createDance(updatedPerson.dance());
                person.setDance(danceServices.getByName(updatedPerson.dance()));
            }
        }
        if(person.getInstitutie().getDenumire().equals(updatedPerson.institutie().denumire())&&
                person.getInstitutie().getLocatie().equals(updatedPerson.institutie().locatie())){}
        else{
            Institutie newInstitutie = institutieService.getByName(updatedPerson.institutie().denumire(),updatedPerson.institutie().locatie());
            if(newInstitutie!=null){
                person.setInstitutie(newInstitutie);

            }else{
                institutieService.createInstitutie(updatedPerson.institutie().denumire(),updatedPerson.institutie().locatie());
                person.setInstitutie(institutieService.getByName(updatedPerson.institutie().denumire(),updatedPerson.institutie().locatie()));
            }
        }


        System.out.println("Person: "+person.getFirstName()+"  "+person.getInstitutie()+" "+person.getDance());

        personsRepository.saveAndFlush(person);

    }

    public void deletePerson(String contact){
        Optional<Person> personsOptional = personsRepository.getPersonsByContact(contact);
        if(personsOptional.isEmpty()){
            ResponseEntity.internalServerError().build();
            return;
        }
        personsRepository.deletePersonsByContact(contact);
        ResponseEntity.ok().build();

    }

    public PersonDTO getYougest(){
        return  personsRepository.getYoungest()
                .map(personDTOMapper)
                .orElseThrow(
                        ()->new NoSuchElementException("Person not found")
                );
    }
    public PersonDTO getOldest(){
        return  personsRepository.getOldest()
                .map(personDTOMapper)
                .orElseThrow(
                        ()->new NoSuchElementException("Person not found")
                );
    }
    public List<PersonDTO> getPeopleAsc(){
        return  personsRepository.getPersonAsc()
                .stream()
                .map(personDTOMapper)
                .toList();

    }

    public PersonDTO getFemaleYoungest(){
        return  personsRepository.getFemaleYoungest()
                .map(personDTOMapper)
                .orElseThrow(
                        ()->new NoSuchElementException("Person not found")
                );
    }

    public List<Object[]> getMaleAvgAge() {
        return personsRepository.getMaleAvgAge();
    }



}






