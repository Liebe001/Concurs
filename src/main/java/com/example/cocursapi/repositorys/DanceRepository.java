package com.example.cocursapi.repositorys;


import com.example.cocursapi.models.Dance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DanceRepository extends JpaRepository<Dance, Long> {

    @Query("select d.id from dance d where d.name = :name")
    public Long getIdByName(String name);

    Dance getDanceByName(String name);

}
