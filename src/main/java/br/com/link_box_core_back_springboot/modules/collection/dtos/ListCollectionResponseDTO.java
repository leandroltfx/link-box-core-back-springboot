package br.com.link_box_core_back_springboot.modules.collection.dtos;

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

    private String message;
    private Page<CollectionDTO> data;

}
