package com.example.backend_java.Controller;


import com.example.backend_java.DTO.CreateUserDTO;
import com.example.backend_java.Entity.Usuario;
import com.example.backend_java.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class usuarioControlller {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    @Tag(name = "Cadastrar", description = "Cadastrar um novo usuario")
    @Operation(summary = "Cadastra um novo usuario", description = "Um novo cadastro será efeituado - requer nivel GERENTE")
    public ResponseEntity<Usuario> create(@RequestBody @Valid CreateUserDTO usuario){

        if(usuarioService.findByMatricula(usuario.matricula()) != null){

            throw new IllegalArgumentException("Matricula já cadastrada");
        }

            var password = new BCryptPasswordEncoder().encode(usuario.senha());
            Usuario novoUsuario = Usuario.builder()
                    .nome(usuario.nome())
                    .nivel(usuario.nivel())
                    .matricula(usuario.matricula())
                    .senha(password)
                    .ativo(true)
                    .build();

            System.out.println(novoUsuario.getNivel() + novoUsuario.getMatricula());
            Usuario user =  usuarioService.salvar(novoUsuario);
           return ResponseEntity.status(HttpStatus.CREATED).body(user);

    };


}
