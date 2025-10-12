package br.com.link_box_core_back_springboot.modules.collection.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCollectionResponseDTO {

    private String message;
    private List<CollectionDTO> data;

}
