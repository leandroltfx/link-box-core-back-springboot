package br.com.link_box_core_back_springboot.modules.collection.controllers;

import br.com.link_box_core_back_springboot.modules.collection.dtos.CollectionDTO;
import br.com.link_box_core_back_springboot.modules.collection.dtos.CreateCollectionRequestDTO;
import br.com.link_box_core_back_springboot.modules.collection.dtos.CreateCollectionResponseDTO;
import br.com.link_box_core_back_springboot.modules.collection.mappers.CollectionMapper;
import br.com.link_box_core_back_springboot.modules.collection.useCases.CreateCollectionUseCase;
import br.com.link_box_core_back_springboot.modules.collection.useCases.ListCollectionsUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    @Autowired
    private CreateCollectionUseCase createCollectionUseCase;

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private ListCollectionsUseCase listCollectionsUseCase;

    @PostMapping
    public ResponseEntity<CreateCollectionResponseDTO> createCollection(
            @Valid @RequestBody CreateCollectionRequestDTO createCollectionRequestDTO,
            HttpServletRequest httpServletRequest
    ) {
        var userId = httpServletRequest.getAttribute("user_id");
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.createCollectionUseCase.execute(
                        createCollectionRequestDTO,
                        UUID.fromString(userId.toString())
                )
        );
    }

    @GetMapping
    public ResponseEntity<Page<CollectionDTO>> listCollections(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest httpServletRequest
    ) {
        var userId = httpServletRequest.getAttribute("user_id");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listCollectionsUseCase.listCollections(UUID.fromString(userId.toString()), page, size));
    }

}
