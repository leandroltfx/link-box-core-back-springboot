package br.com.link_box_core_back_springboot.modules.user.useCases;

import br.com.link_box_core_back_springboot.exceptions.UserFoundException;
import br.com.link_box_core_back_springboot.modules.user.dtos.UserRegistrationRequestDTO;
import br.com.link_box_core_back_springboot.modules.user.dtos.UserRegistrationResponseDTO;
import br.com.link_box_core_back_springboot.modules.user.entities.UserEntity;
import br.com.link_box_core_back_springboot.modules.user.mappers.UserMapper;
import br.com.link_box_core_back_springboot.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class UserRegistrationUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserRegistrationResponseDTO execute(UserRegistrationRequestDTO userRegistrationRequestDTO) {

        UserEntity userEntity = userMapper.toEntity(userRegistrationRequestDTO);

        this.userRepository.findByUserNameOrEmail(
                userEntity.getUserName(),
                userEntity.getEmail()
        ).ifPresent(user -> {
            throw new UserFoundException("Este e-mail e/ou nome de usuário já está em uso.");
        });

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        this.userRepository.save(userEntity);

        var expiresIn = Instant.now().plus(Duration.ofHours(1));

        return UserRegistrationResponseDTO
                .builder()
                .access_token("GLMfXGvIt8iwaJiOPoeCCm2ps7G14tvw4up2s7k3cK8aX3WdkjLWRKi6HHspVGEp")
                .expires_in(expiresIn.toEpochMilli())
                .message("Usuário cadastrado com sucesso!")
                .build();
    }

}
