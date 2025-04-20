package com.test.filehandling.model;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {


    private Long id;

    private String name;

    private String email;

    private String password;

    private MultipartFile playerDataFile;


}
