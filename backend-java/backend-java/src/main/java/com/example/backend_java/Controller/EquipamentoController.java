package com.example.backend_java.Controller;


import com.example.backend_java.DTO.CreateEquipamentoDTO;
import com.example.backend_java.Entity.Equipamento;
import com.example.backend_java.Service.EquipamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipamentos")
@RequiredArgsConstructor
public class EquipamentoController {


    private final EquipamentoService equipamentoService;

    @PostMapping
    public ResponseEntity CreateEquipamento(@RequestBody @Valid CreateEquipamentoDTO createEquipamentoDTO){
        Equipamento equipamento = Equipamento.builder()
                .numeroPdv(createEquipamentoDTO.numeroPdv())
                .portaComunicacaoAgente(createEquipamentoDTO.portaComunicacaoAgente())
                .ipPdv(createEquipamentoDTO.ipPdv())
                .ativo(true)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(equipamentoService.CreateEquipamentoService(equipamento));
    }
}
