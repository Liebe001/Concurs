package com.example.cocursapi.services;


import com.example.cocursapi.models.Institutie;
import com.example.cocursapi.repositorys.InstitutieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutieService {

    private final InstitutieRepository institutieRepository;

    @Autowired
    public InstitutieService(InstitutieRepository institutieRepository) {
        this.institutieRepository = institutieRepository;
    }

    public Long getIdByName(String name,String locate){
        return institutieRepository.getInstitutieByDenumireAndLocatie(name,locate).getId();
    }

    public List<Institutie> getAll(){
        return institutieRepository.findAll();
    }

    public Institutie getByName(String denumire,String locatie){
        return institutieRepository.getInstitutieByDenumireAndLocatie(denumire,locatie);
    }

    public void createInstitutie(String denumire,String locatie){
        try{
            if(institutieRepository.getInstitutieByDenumireAndLocatie(denumire, locatie)==null){
                institutieRepository.saveAndFlush(new Institutie(denumire,locatie));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void delete(String denumire,String locatie){
        Institutie deleteInsit = institutieRepository.getInstitutieByDenumireAndLocatie( denumire,locatie);
        if(deleteInsit!=null){
            institutieRepository.delete(deleteInsit);
        }
    }

}
