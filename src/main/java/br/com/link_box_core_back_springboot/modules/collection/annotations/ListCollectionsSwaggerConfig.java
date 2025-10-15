package br.com.link_box_core_back_springboot.modules.collection.annotations;

import br.com.link_box_core_back_springboot.dtos.ResponseErrorDTO;
import br.com.link_box_core_back_springboot.modules.collection.dtos.ListCollectionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
        @ApiResponse(responseCode = "200", content = {
                @Content(schema = @Schema(implementation = ListCollectionResponseDTO.class))
        }),
        @ApiResponse(responseCode = "403", description = "Falha na autenticação.", content = {
                @Content(schema = @Schema(implementation = ResponseErrorDTO.class))
        }),
        @ApiResponse(responseCode = "500", description = "Erro de conexão com o banco de dados.", content = {
                @Content(schema = @Schema(implementation = ResponseErrorDTO.class))
        })
})
@Operation(summary = "Listagem de coleções", description = "Retorna uma lista de coleções com paginação")
public @interface ListCollectionsSwaggerConfig {
}
