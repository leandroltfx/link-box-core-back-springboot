package br.com.link_box_core_back_springboot.modules.collection.useCases;

import br.com.link_box_core_back_springboot.modules.collection.dtos.UpdateCollectionRequestDTO;
import br.com.link_box_core_back_springboot.modules.collection.dtos.UpdateCollectionResponseDTO;
import br.com.link_box_core_back_springboot.modules.collection.entities.CollectionEntity;
import br.com.link_box_core_back_springboot.modules.collection.repositories.CollectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCollectionUseCase {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private ListCollectionsUseCase listCollectionsUseCase;

    public UpdateCollectionResponseDTO execute(
            UUID collectionId,
            UpdateCollectionRequestDTO updateCollectionRequestDTO,
            UUID userId
    ) {

        CollectionEntity collectionEntity = collectionRepository
                .findById(collectionId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Coleção não encontrada.")
                );

        collectionEntity.setName(updateCollectionRequestDTO.getName());
        collectionEntity.setDescription(updateCollectionRequestDTO.getDescription());

        this.collectionRepository.save(collectionEntity);
        var collections = this.listCollectionsUseCase.getCollectionsPageable(userId, 0, 10);

        return UpdateCollectionResponseDTO
                .builder()
                .message("Coleção alterada com sucesso!")
                .data(collections)
                .build();
    }

}
