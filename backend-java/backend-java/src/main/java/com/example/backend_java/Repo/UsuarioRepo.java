package com.example.backend_java.Repo;

import com.example.backend_java.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer>{
}


