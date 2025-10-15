package br.com.link_box_core_back_springboot.modules.collection.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListCollectionResponseDTO {

    @Schema(implementation = Page.class, description = "Lista de coleções do usuário")
    private Page<CollectionDTO> data;

}
