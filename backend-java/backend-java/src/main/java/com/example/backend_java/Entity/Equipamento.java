package com.example.backend_java.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "equipamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_pdv", nullable = false)
    private Integer  numeroPdv;

    @Column(name = "porta_comunicacao_agente", nullable = false)
    private Integer portaComunicacaoAgente;

    @Column(name = "ip_pdv", nullable = false)
    private String ipPdv;


    @Column(name = "ativo")
    private boolean ativo;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Equipamento equipamento = (Equipamento) o;
        return Objects.equals(id, equipamento.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString(){

        return "Equipamento{" + "id=" + id + "}";

    }



}
