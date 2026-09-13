package com.example.backend_java.Service;


import com.example.backend_java.DTO.CreateUserDTO;
import com.example.backend_java.Entity.Usuario;
import com.example.backend_java.Mapper.UsuarioMapper;
import com.example.backend_java.Repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper usuarioMapper;

    @Transactional
    public Usuario salvar(CreateUserDTO dto) {

        Usuario usuario = usuarioMapper.toEntity(dto);
        return  usuarioRepo.save(usuario);
    }
}
