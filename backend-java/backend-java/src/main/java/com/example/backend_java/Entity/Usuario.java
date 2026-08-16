package com.example.backend_java.Entity;


import com.example.backend_java.enun.NIVEL_USUARIO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.OffsetDateTime;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", columnDefinition = "text")
    private String nome;

    @Column(name = "matricula", columnDefinition = "text")
    private String matricula;

    @Column(name = "nivel")
    private NIVEL_USUARIO nivel ;

    @Column(name = "ativo")
    private boolean ativo;


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;


}
