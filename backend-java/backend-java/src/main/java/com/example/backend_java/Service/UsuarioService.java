package com.example.backend_java.Service;


import com.example.backend_java.Entity.Usuario;
import com.example.backend_java.Repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepo usuarioRepo;

    @Transactional
    public Usuario salvar(Usuario usuario) {
       return  usuarioRepo.save(usuario);
    }
}
