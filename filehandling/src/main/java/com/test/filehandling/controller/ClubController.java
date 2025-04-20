package com.test.filehandling.controller;


import com.test.filehandling.model.Club;
import com.test.filehandling.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/clubs")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @PostMapping("/registerClub")

    public ResponseEntity registerClub(@RequestBody Club club){
        HashMap<String, Object> response = new HashMap<>();

        try{
            Club club1 = clubService.registerClub(club);

            response.put("success",true);
            response.put("message","club saved successfully");
            response.put("Data",club1);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        response.put("success",false);
            response.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }
}
