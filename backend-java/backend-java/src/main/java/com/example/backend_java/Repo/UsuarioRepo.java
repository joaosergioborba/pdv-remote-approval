package com.example.backend_java.Repo;

import com.example.backend_java.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer>{

    Optional<Usuario> findByMatricula(String matricula);


}


