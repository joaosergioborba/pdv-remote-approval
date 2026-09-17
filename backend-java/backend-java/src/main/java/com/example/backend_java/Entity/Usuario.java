package com.example.backend_java.Entity;


import com.example.backend_java.DTO.CreateUserDTO;
import com.example.backend_java.enun.NIVEL_USUARIO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", columnDefinition = "text")
    private String nome;

    @Column(name = "matricula", columnDefinition = "text", nullable = false)
    private String matricula;

    @Column(name = "senha", columnDefinition = "text", nullable = false)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(nivel.name()));
    }

    @Override
    public String getPassword() { return senha; }

    @Override
    public String getUsername() { return matricula; }

}
