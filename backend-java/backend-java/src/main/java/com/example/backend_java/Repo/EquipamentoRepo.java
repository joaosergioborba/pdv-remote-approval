package com.example.backend_java.Repo;

import com.example.backend_java.Entity.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipamentoRepo extends JpaRepository<Equipamento, Integer> {

    Optional<Equipamento> findByNumeroPdv(Integer numeroPdv);

    Optional<Equipamento> findByIpPdv(String ip);
}
