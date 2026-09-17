package com.example.backend_java.Config;


import com.example.backend_java.Erros.CadastroNaoEncontradoException;
import com.example.backend_java.Repo.UsuarioRepo;
import com.example.backend_java.Service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityFilter  extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token =  this.recoveryToken(request);
        if (token != null) {

           String matricula =  tokenService.validarToken(token);

           if(!Objects.equals(matricula, "")){

               UserDetails user = usuarioRepo.findByMatricula(matricula).orElseThrow(() -> new CadastroNaoEncontradoException("Matricula não encontrada"));

               var authentication = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());

               SecurityContextHolder.getContext().setAuthentication(authentication);
           }

        }
        filterChain.doFilter(request, response);

    }

    private String recoveryToken(HttpServletRequest request){

        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;

        return authHeader.replace("Bearer", "").trim();

    }
}
