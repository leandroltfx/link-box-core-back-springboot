package br.com.link_box_core_back_springboot.modules.user.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationResponseDTO {

    @Schema(example = "Usuário cadastrado com sucesso!", description = "Mensagem de sucesso")
    private String message;

    @Schema(example = "eyJhbGciOI6IkpXVCJ9.eyJzdWIxNjIzOTAyMn0.KMUFsIDTnFmyG9FNFU0", description = "Token de autorização")
    private String access_token;

    @Schema(example = "1760526387", description = "Tempo de expiração do token")
    private Long expires_in;

}
