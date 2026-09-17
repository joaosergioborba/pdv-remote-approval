package com.example.backend_java.Mapper;


import com.example.backend_java.DTO.CreateUserDTO;
import com.example.backend_java.Entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(CreateUserDTO dto);
}
