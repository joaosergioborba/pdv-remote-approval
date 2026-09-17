package com.example.backend_java.Service;

import com.example.backend_java.Entity.Usuario;
import com.example.backend_java.Repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    @Lazy //para usar so no momento de uso
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        return usuarioRepo.findByMatricula(matricula).orElseThrow(() -> new UsernameNotFoundException("Credenciais invalidas"));
    }

    public String Login(java.lang.String matricula, String senha ){



        var usernamePassword = new UsernamePasswordAuthenticationToken(matricula, senha);

        var auth = this.authenticationManager.authenticate(usernamePassword);

        if(auth.isAuthenticated()){
            System.out.println("aqui meu frind" + ((Usuario)auth.getPrincipal()).getMatricula());
          return tokenService.gerarToken(((Usuario) auth.getPrincipal()).getMatricula());

        }

        return null;
    }
}
