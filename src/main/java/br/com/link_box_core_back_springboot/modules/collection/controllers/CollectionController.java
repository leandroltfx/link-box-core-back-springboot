package br.com.link_box_core_back_springboot.modules.collection.controllers;

import br.com.link_box_core_back_springboot.modules.collection.dtos.CreateCollectionRequestDTO;
import br.com.link_box_core_back_springboot.modules.collection.dtos.CreateCollectionResponseDTO;
import br.com.link_box_core_back_springboot.modules.collection.mappers.CollectionMapper;
import br.com.link_box_core_back_springboot.modules.collection.useCases.CreateCollectionUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    @Autowired
    private CreateCollectionUseCase createCollectionUseCase;

    @Autowired
    private CollectionMapper collectionMapper;

    @PostMapping
    public ResponseEntity<CreateCollectionResponseDTO> createCollection(
            @Valid @RequestBody CreateCollectionRequestDTO createCollectionRequestDTO,
            HttpServletRequest request
    ) {
        var userId = request.getAttribute("user_id");
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.createCollectionUseCase.execute(
                        createCollectionRequestDTO,
                        UUID.fromString(userId.toString())
                )
        );
    }

}
