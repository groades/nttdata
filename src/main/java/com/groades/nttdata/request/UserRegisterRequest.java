package com.groades.nttdata.request;

import com.groades.nttdata.entities.PhoneEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    private String name;
    @NotNull(message = "El email es obligatorio")
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato correcto (aaaaaa@dominio.cl)")
    private String email;
    @Pattern(message = "La contraseña debe contener al menos una letra Mayúscula, letras minúsculas y dos números",
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d{2,}).+$", flags = Pattern.Flag.UNICODE_CASE)
    private String password;
    private List<PhoneEntity> phones;
}
