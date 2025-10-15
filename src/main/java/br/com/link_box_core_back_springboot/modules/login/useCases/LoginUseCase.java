package br.com.link_box_core_back_springboot.modules.login.useCases;

import br.com.link_box_core_back_springboot.exceptions.UserNotFoundException;
import br.com.link_box_core_back_springboot.modules.login.dtos.LoginRequestDTO;
import br.com.link_box_core_back_springboot.modules.login.dtos.LoginResponseDTO;
import br.com.link_box_core_back_springboot.modules.user.entities.UserEntity;
import br.com.link_box_core_back_springboot.modules.user.repositories.UserRepository;
import br.com.link_box_core_back_springboot.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class LoginUseCase {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    public LoginResponseDTO execute(
            LoginRequestDTO loginRequestDTO
    ) throws AuthenticationException {

        UserEntity userEntity = this.userRepository
                .findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new AuthenticationException("E-mail e/ou senha inválidos"));

        var passwordMatches = this.passwordEncoder.matches(loginRequestDTO.getPassword(), userEntity.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException("E-mail e/ou senha inválidos");
        }

        return LoginResponseDTO.builder()
                .message("Login realizado com sucesso!")
                .access_token(jwtProvider.generateToken(userEntity.getId()))
                .expires_in(jwtProvider.generateTokenExpirationTime().toEpochMilli())
                .build();
    }

}
