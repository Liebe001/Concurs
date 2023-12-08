package com.example.cocursapi.controllers;

import com.example.cocursapi.DTO.PersonDTO;
import com.example.cocursapi.models.Person;
import com.example.cocursapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;



    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDTO> getAll(){
        return personService.getAll();
    }



    @GetMapping("/{contact}")
    public PersonDTO getPersonByContact(@PathVariable String contact){
        return personService.getPersonByContact(contact);

    }

    @GetMapping("/MinAge")
    public PersonDTO getPersonWithMinAge(){
        return personService.getOldest();

    }
    @GetMapping("/MaxAge")
    public PersonDTO getPersonWithMaxAge(){
        return personService.getYougest();

    }

    @GetMapping("/asc")
    public List<PersonDTO> getPeopleAsc(){
        return personService.getPeopleAsc();
    }
    @GetMapping("/FemaleMinAge")
    public PersonDTO getFemaleMinAge(){
        return personService.getFemaleYoungest();
    }



    @GetMapping("/dance/{dance}")
    public PersonDTO getPersonByDance(@PathVariable String dance){
        return personService.getPersonByDance(dance);
    }

    @PostMapping("/create")
    public void createPerson(@RequestBody PersonDTO person){
        personService.createPerson(person);

    }

    @PutMapping("/{contact}")
    public void updatePerson(@PathVariable String contact,@RequestBody PersonDTO person){

        personService.updatePerson(contact,person);
    }

    @DeleteMapping("/{contact}")
    public void deletePerson(@PathVariable String contact){
        personService.deletePerson(contact);
    }


    @GetMapping("/maleAvgAge")
    public ResponseEntity<List<Object[]>> getMaleAvgAge() {
        List<Object[]> result = personService.getMaleAvgAge();

        return ResponseEntity.ok(result);


    }


    @GetMapping("/index")
    public String index(Model model) {
        List<PersonDTO> persons = personService.getAll();
        model.addAttribute("persons", persons);
        model.addAttribute("newPerson", new Person());
        model.addAttribute("addPersonPanelStyle", "display: none;");
        return "index";
    }






}
