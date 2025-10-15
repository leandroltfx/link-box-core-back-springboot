package br.com.link_box_core_back_springboot.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseErrorDTO {

    @Schema(example = "Ocorreu um erro", description = "Mensagem de erro")
    private String message;

    private List<ErrorFieldDTO> details = new ArrayList<>();

    public ResponseErrorDTO(
            String message
    ) {
        this.message = message;
    }

}
