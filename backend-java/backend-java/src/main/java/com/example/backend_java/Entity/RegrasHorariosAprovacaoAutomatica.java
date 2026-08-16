package com.example.backend_java.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "regras_horarios_aprovacao_automatica")
@Setter
@Getter
public class RegrasHorariosAprovacaoAutomatica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dia_unico")
    private Date diaUnico;

    @Column(name = "hora_inicio")
    private Time horaInicio;

    @Column(name = "hora_fim")
    private Time horaFim;

    @Column(name = "segunda_active")
    private boolean seguntaActive;

    @Column(name = "terca_active")
    private boolean tercaActive;

    @Column(name = "quarta_active")
    private boolean quartaActive;

    @Column(name = "quinta_active")
    private boolean quintaActive;

    @Column(name = "sexta_active")
    private boolean sextaActive;

    @Column(name = "sabado_active")
    private boolean sabadoActive;

    @Column(name = "domingo_active")
    private boolean domingoActive;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        RegrasHorariosAprovacaoAutomatica regraAprovacao = (RegrasHorariosAprovacaoAutomatica) o;
        return Objects.equals(id, regraAprovacao.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString(){

        return "Regra aprovacao automatica{" + "id=" + id + "}";

    }

}
