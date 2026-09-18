package com.example.backend_java.DTO;

import jakarta.validation.constraints.NotBlank;

public record CreateEquipamentoDTO (
        @NotBlank(message = "O numero do PDV é obrigatorio")
        Integer numeroPdv,

        @NotBlank(message  = "A porta de comunicação do agente é obrigatória")
        Integer portaComunicacaoAgente

){

}
