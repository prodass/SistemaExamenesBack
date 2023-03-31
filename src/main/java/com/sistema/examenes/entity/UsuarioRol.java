package com.sistema.examenes.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UsuarioRol {
    
    @Id
    @Column(name = "id_usuarioRol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuarioRol;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    
    @ManyToOne
    private Rol rol;
}
