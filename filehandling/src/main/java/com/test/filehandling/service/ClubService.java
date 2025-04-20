package com.test.filehandling.service;

import com.test.filehandling.model.Club;
import com.test.filehandling.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClubService {


    @Autowired
    private ClubRepository clubRepository;


    public Club registerClub(Club club){
        Optional<Club> byEmail = clubRepository.findByEmail(club.getEmail());

        if(byEmail.isPresent()){

            throw new RuntimeException("club already present");
        }

        else{
            clubRepository.save(club);
            return club;
        }
    }
}
