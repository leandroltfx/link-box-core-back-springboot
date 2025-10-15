package br.com.link_box_core_back_springboot.modules.collection.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCollectionResponseDTO {

    @Schema(example = "Coleção cadastrada com sucesso!", description = "Mensagem de sucesso")
    private String message;

    @Schema(implementation = CollectionDTO.class, description = "Coleção cadastrada")
    private CollectionDTO data;

}
