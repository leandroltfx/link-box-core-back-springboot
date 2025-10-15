package br.com.link_box_core_back_springboot.modules.collection.controllers;

import br.com.link_box_core_back_springboot.modules.collection.annotations.CreateCollectionSwaggerConfig;
import br.com.link_box_core_back_springboot.modules.collection.annotations.ListCollectionsSwaggerConfig;
import br.com.link_box_core_back_springboot.modules.collection.annotations.UpdateCollectionSwaggerConfig;
import br.com.link_box_core_back_springboot.modules.collection.dtos.*;
import br.com.link_box_core_back_springboot.modules.collection.useCases.CreateCollectionUseCase;
import br.com.link_box_core_back_springboot.modules.collection.useCases.ListCollectionsUseCase;
import br.com.link_box_core_back_springboot.modules.collection.useCases.UpdateCollectionUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/collections")
@Tag(name = "Coleção", description = "Coleções para agrupar links")
public class CollectionController {

    @Autowired
    private CreateCollectionUseCase createCollectionUseCase;

    @Autowired
    private ListCollectionsUseCase listCollectionsUseCase;

    @Autowired
    private UpdateCollectionUseCase updateCollectionUseCase;

    @CreateCollectionSwaggerConfig
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

    @ListCollectionsSwaggerConfig
    @GetMapping
    public ResponseEntity<ListCollectionResponseDTO> listCollections(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest httpServletRequest
    ) {
        var userId = httpServletRequest.getAttribute("user_id");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listCollectionsUseCase.execute(UUID.fromString(userId.toString()), page, size));
    }

    @UpdateCollectionSwaggerConfig
    @PatchMapping("/{collection_id}")
    public ResponseEntity<UpdateCollectionResponseDTO> updateCollection(
            @PathVariable("collection_id") UUID collectionId,
            @Valid @RequestBody UpdateCollectionRequestDTO updateCollectionRequestDTO,
            HttpServletRequest httpServletRequest
    ) {
        var userId = httpServletRequest.getAttribute("user_id");
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.updateCollectionUseCase.execute(
                        collectionId,
                        updateCollectionRequestDTO,
                        UUID.fromString(userId.toString())
                )
        );
    }

}
