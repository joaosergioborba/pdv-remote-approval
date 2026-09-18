package com.example.backend_java.Controller;


import com.example.backend_java.DTO.CreateEquipamentoDTO;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("\equipamentos")
public class EquipamentoController {

    @PostMapping
    public ResponseEntity CreateEquipamento(@RequestBody @Valid CreateEquipamentoDTO createEquipamentoDTO){



    }
}
