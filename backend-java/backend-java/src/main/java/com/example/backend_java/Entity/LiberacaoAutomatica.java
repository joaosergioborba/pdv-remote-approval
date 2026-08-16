package com.example.backend_java.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "liberacao_automatica")
@Getter
@Setter
public class LiberacaoAutomatica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_regra_horarios_aprovacao_automatica")
    private RegrasHorariosAprovacaoAutomatica idRegraHorariosAprovacaoAutomatica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acoes_de_liberacao")
    private AcoesDeLiberacoes idAcoesDeLiberacao;

    @Column(name = "ativo")
    private boolean ativo;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        LiberacaoAutomatica liberacaoAutomatica = (LiberacaoAutomatica) o;
        return Objects.equals(id, liberacaoAutomatica.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString(){

        return "liberacaoAutomatica{" + "id=" + id + "}";

    }

}
