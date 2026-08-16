package com.example.backend_java.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

}
