package com.example.backend_java.Entity;


import com.example.backend_java.enun.NIVEL_USUARIO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.Objects;

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

    @Column(name = "senha", columnDefinition = "text")
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false)
    private NIVEL_USUARIO nivel ;

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
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString(){

        return "Usuario{" + "id=" + id + "}";

    }

}
