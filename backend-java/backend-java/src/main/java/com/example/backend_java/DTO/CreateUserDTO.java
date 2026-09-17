package com.example.backend_java.DTO;

import com.example.backend_java.enun.NIVEL_USUARIO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


public record CreateUserDTO (

        @NotBlank(message = "O nome é obrigatorio")
        String nome,

        @NotBlank(message = "A matricula é obrigatoria")
        String matricula,

        @NotBlank(message = "A senha é obrigatoria")
        String senha,

        NIVEL_USUARIO nivel

){}
