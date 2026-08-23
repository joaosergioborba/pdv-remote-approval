package com.example.backend_java.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Entity
@Table(name = "acoes_de_liberacao")
@Getter
@Setter
public class AcoesDeLiberacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "acao", columnDefinition = "text")
    private String acao;

    @Column(name = "tecla_associada",length = 1)
    private String teclaAssociada;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        AcoesDeLiberacoes acao = (AcoesDeLiberacoes) o;
        return Objects.equals(id, acao.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString(){

        return "Acao{" + "id=" + id + "}";

    }
}



