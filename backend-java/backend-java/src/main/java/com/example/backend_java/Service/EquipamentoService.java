package com.example.backend_java.Service;

import com.example.backend_java.Entity.Equipamento;
import com.example.backend_java.Repo.EquipamentoRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class EquipamentoService {

    @Autowired
    private EquipamentoRepo equipamentoRepo;

    public void CreateEquipamentoService(Equipamento equipamento){

        if(equipamentoRepo.findByNumeroPdv(equipamento.getNumeroPdv()).isPresent()){
            throw new IllegalArgumentException("Equipamento já cadastrado");
        }



    }
}
