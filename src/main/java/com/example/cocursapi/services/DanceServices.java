package com.example.cocursapi.services;

import com.example.cocursapi.models.Dance;
import com.example.cocursapi.repositorys.DanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanceServices {

    private final DanceRepository danceRepository;

    @Autowired
    public DanceServices(DanceRepository danceRepository) {
        this.danceRepository = danceRepository;
    }


    public Long getIdByName(String name){
        return danceRepository.getIdByName(name);
    }


    public List<Dance> getAll(){
        return danceRepository.findAll();
    }

    public Dance getByName(String name){
        return danceRepository.getDanceByName(name);
    }
    public void createDance(String dance){

       try {
           if(danceRepository.getDanceByName(dance)==null){
               danceRepository.saveAndFlush(new Dance(dance));
           }
       }catch (Exception e){
           e.printStackTrace();
       }

    }


    public void deleteDance(String dance){

        Dance thisDance = getByName(dance);
        if(thisDance!=null)
            danceRepository.delete(thisDance);

    }
}
