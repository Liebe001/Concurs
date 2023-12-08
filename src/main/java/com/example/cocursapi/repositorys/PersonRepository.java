package com.example.cocursapi.repositorys;


import com.example.cocursapi.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

    @Query("SELECT p FROM persons p JOIN p.dance d WHERE d.name = :dance")
    Optional<Person> getPersonsByDance(@Param("dance") String dance);
    Optional<Person> getPersonsByContact(String contact);
    void deletePersonsByContact(String contact);

    @Query("FROM persons p INNER JOIN p.dance d WHERE p.birthDay = (SELECT MIN(p2.birthDay) FROM persons p2)")
    Optional<Person> getYoungest();

    @Query("FROM persons p INNER JOIN p.dance d WHERE p.birthDay = (SELECT MAX(p2.birthDay) FROM persons p2)")
    Optional<Person> getOldest();

    @Query("FROM persons ORDER BY birthDay ASC")
    List<Person> getPersonAsc();


    @Query("FROM persons p  WHERE p.birthDay = (SELECT MIN(p2.birthDay) FROM persons p2 WHERE p2.sex IN ('feminin', 'f', 'F', 'fem', 'female', 'Female', 'Feminin')) AND p.sex IN ('feminin', 'f', 'F', 'fem', 'female', 'Female', 'Feminin')")
    Optional<Person> getFemaleYoungest();


    @Query("SELECT d.name AS categoryOfDance, AVG(YEAR(CURRENT_DATE) - YEAR(p.birthDay)) AS averageAge FROM persons p JOIN p.dance d WHERE p.sex = 'Male' GROUP BY d.name ORDER BY d.name")
    List<Object[]> getMaleAvgAge();







}
