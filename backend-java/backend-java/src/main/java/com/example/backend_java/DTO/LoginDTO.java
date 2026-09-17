package com.example.backend_java.DTO;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(@NotBlank(message = "Matricula obrigatória") String matricula, @NotBlank(message = "Senha é obrigatoria") String senha){


}
