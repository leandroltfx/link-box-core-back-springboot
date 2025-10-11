package br.com.link_box_core_back_springboot.modules.collection.repositories;

import br.com.link_box_core_back_springboot.modules.collection.entities.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, UUID> {

    List<CollectionEntity> findAllByUserId(UUID userId);

}
