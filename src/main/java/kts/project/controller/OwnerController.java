package kts.project.controller;

import kts.project.controller.dto.RegisterDTO;
import kts.project.model.Owner;
import kts.project.repository.OwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by USER on 6/11/2017.
 */

@RestController
@RequestMapping("/api/users/owner")
public class OwnerController {

    OwnerRepository ownerRepository;



}
