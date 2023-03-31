package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
    public Usuario findByUsername(String username);

}
