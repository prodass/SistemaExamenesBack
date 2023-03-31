package com.sistema.examenes.controller;

import com.sistema.examenes.entity.JwtRequest;
import com.sistema.examenes.entity.JwtResponse;
import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.security.JwtUtils;
import com.sistema.examenes.services.UserDetailsServicesImpl;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServicesImpl userDetailsServicesImpl;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails = this.userDetailsServicesImpl.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException disabledException) {
            throw new Exception("Usuario deshabilitado" + disabledException.getMessage());
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Credenciales invalidas" + badCredentialsException.getMessage());
        }
    }
    
    @GetMapping("/actual-usuario")
    public Usuario getActualUsuario(Principal principal){
        return (Usuario) this.userDetailsServicesImpl.loadUserByUsername(principal.getName());
    }
}
