package com.example.cocursapi.repositorys;



import com.example.cocursapi.models.Institutie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutieRepository extends JpaRepository<Institutie,Long> {

    @Query("from institutie where denumire = :denumire and locatie = :locatie")
    Institutie getInstitutieByDenumireAndLocatie(String denumire, String locatie);

}
