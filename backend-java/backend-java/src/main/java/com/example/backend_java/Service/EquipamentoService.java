package com.example.backend_java.Service;

import com.example.backend_java.Entity.Equipamento;
import com.example.backend_java.Repo.EquipamentoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EquipamentoService {

    @Autowired
    private EquipamentoRepo equipamentoRepo;



    public Equipamento CreateEquipamentoService(Equipamento equipamento){

        if(equipamentoRepo.findByNumeroPdv(equipamento.getNumeroPdv()).isPresent() || (equipamentoRepo.findByIpPdv(equipamento.getIpPdv()).isPresent())){
            throw new IllegalArgumentException("Equipamento já cadastrado");
        }
        return equipamentoRepo.save(equipamento);
    }
}
