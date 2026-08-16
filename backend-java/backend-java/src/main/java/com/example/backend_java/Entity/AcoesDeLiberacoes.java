package com.example.backend_java.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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
}



