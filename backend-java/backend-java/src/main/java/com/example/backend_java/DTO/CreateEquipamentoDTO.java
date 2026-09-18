package com.example.backend_java.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateEquipamentoDTO (
        @NotNull(message = "O numero do PDV é obrigatorio")
        Integer numeroPdv,

        @NotNull(message  = "A porta de comunicação do agente é obrigatória")
        Integer portaComunicacaoAgente,

        @NotBlank(message = " o IP do pdv é obrigatorio")
        String ipPdv

){

}
