package com.test.filehandling.controller;


import com.test.filehandling.model.Club;
import com.test.filehandling.model.Player;
import com.test.filehandling.model.RegistrationDTO;
import com.test.filehandling.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/v1/players")
public class PlayerController {


    @Autowired
    private PlayerService playerService;

    @PostMapping("/registerPlayer")

    public ResponseEntity<?> regsiterPlayer(
            @ModelAttribute RegistrationDTO registrationDTO, @RequestParam Long clubId
    ) {
        HashMap<String, Object> response = new HashMap<>();

        try {
            Player player = playerService.validateAndRegisterPlayer(registrationDTO, clubId);

            response.put("success", true);
            response.put("message", "club saved successfully");
            response.put("Data", player);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }


}

