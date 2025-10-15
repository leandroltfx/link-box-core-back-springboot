package br.com.link_box_core_back_springboot.modules.login.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDTO {

    @NotBlank(
            message = "Informe o e-mail"
    )
    @Schema(example = "user@email.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "E-mail do usuário")
    private String email;

    @NotBlank(
            message = "Informe a senha"
    )
    @Schema(example = "user1234", requiredMode = Schema.RequiredMode.REQUIRED, description = "Senha do usuário")
    private String password;

}
