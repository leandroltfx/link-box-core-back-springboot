package br.com.link_box_core_back_springboot.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorFieldDTO {

    @Schema(example = "email", description = "Nome do campo com erro")
    private String field;

    @Schema(example = "E-mail inválido", description = "Descrição do erro")
    private String message;

}
