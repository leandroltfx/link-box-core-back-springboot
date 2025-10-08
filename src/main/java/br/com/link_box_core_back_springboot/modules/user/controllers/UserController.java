package br.com.link_box_core_back_springboot.modules.user.controllers;

import br.com.link_box_core_back_springboot.modules.user.dtos.UserRegistrationRequestDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public void register(
            @Valid @RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO
    ) {
        System.out.println(userRegistrationRequestDTO.toString());
    }

}
