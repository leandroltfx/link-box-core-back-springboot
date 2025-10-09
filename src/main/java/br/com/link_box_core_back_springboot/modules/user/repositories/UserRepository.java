package br.com.link_box_core_back_springboot.modules.user.repositories;

import br.com.link_box_core_back_springboot.modules.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUserNameOrEmail(String userName, String email);

}
