package com.test.filehandling.service;


import com.test.filehandling.model.Club;
import com.test.filehandling.model.Player;
import com.test.filehandling.model.RegistrationDTO;
import com.test.filehandling.repository.ClubRepository;
import com.test.filehandling.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {


    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Value("${player.fileDirectory}")

    private String uploadDir;

    public Player validateAndRegisterPlayer(RegistrationDTO player,Long clubId) throws IOException {

        Optional<Club> byClubId = clubRepository.findById(clubId);

        if(!byClubId.isPresent()){
            throw new RuntimeException("Player cannot be registered ,club is not present");
        }

        Optional<Player> byEmail = playerRepository.findByEmail(player.getEmail());

        if(byEmail.isPresent()){
            throw new RuntimeException("Player already present in club :"+player.getId());
        }

        if(player.getEmail() ==null || player.getEmail().trim().isEmpty()){
            throw new RuntimeException("email cannot be null or empty");
        }


        String fileName = UUID.randomUUID() + "_" + StringUtils.cleanPath(player.getPlayerDataFile().getOriginalFilename());

        Path path = Path.of(uploadDir);

        if(Files.notExists(path)){
            Files.createDirectories(path);
        }


        Path targetPath = Path.of(uploadDir, fileName);

        try{
            Files.copy(player.getPlayerDataFile().getInputStream(),targetPath,StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("File failed to upload");
        }


        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/playerImages/").path(fileName).toUriString();

        Player player1=new Player();

        Club club = byClubId.get();
        player1.setName(player.getName());
        player1.setEmail(player.getEmail());
        player1.setPassword(player.getPassword());
        player1.setPlayerDataFile(fileUri);
        player1.setClub(club);

        playerRepository.save(player1);


        return player1;
    }
}
