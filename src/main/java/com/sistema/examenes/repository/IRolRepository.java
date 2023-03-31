package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<Rol, Long>{
    
}
