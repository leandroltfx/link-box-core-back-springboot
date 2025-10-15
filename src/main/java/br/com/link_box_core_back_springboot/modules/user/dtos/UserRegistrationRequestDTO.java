package br.com.link_box_core_back_springboot.modules.user.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationRequestDTO {

    @Pattern(
            regexp = "^[A-Za-z][A-Za-z0-9]{2,29}$",
            message = "O nome de usuário deve ter entre 3 e 30 caracteres, começar com uma letra e conter apenas letras e números (sem espaços ou símbolos)."
    )
    @NotNull(message = "Informe o nome de usuário.")
    @Schema(example = "user", requiredMode = Schema.RequiredMode.REQUIRED, description = "Nickname do usuário")
    private String userName;

    @Pattern(
            regexp = "^(?=.{1,254}$)[^\\s@]+@[^\\s@]+\\.[^\\s@]+$",
            message = "Digite um e-mail válido no formato \"exemplo@dominio.com\" sem ultrapassar 254 caracteres."
    )
    @NotNull(message = "Informe o e-mail.")
    @Schema(example = "user@email.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "E-mail do usuário")
    private String email;

    @Pattern(
            regexp = "^.{8,80}$",
            message = "A senha deve ter entre 8 e 80 caracteres."
    )
    @NotNull(message = "Informe a senha.")
    @Schema(example = "user1234", requiredMode = Schema.RequiredMode.REQUIRED, description = "Senha do usuário")
    private String password;

}
