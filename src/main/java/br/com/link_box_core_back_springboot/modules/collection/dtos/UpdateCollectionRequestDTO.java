package br.com.link_box_core_back_springboot.modules.collection.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCollectionRequestDTO {

    @Size(min = 3, max = 50, message = "O nome da coleção deve ter entre 3 e 50 caracteres")
    @NotNull(message = "Informe o nome da coleção.")
    @Schema(example = "Canais do Geopolítica", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Nome da coleção de links")
    private String name;

    @Size(max = 255, message = "A descrição não deve ultrapassar 255 caracteres")
    @Schema(example = "10 melhores canais de Geopolítica", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Descrição da coleção de links")
    private String description;

}
