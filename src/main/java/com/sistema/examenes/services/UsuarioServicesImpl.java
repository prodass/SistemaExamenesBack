package com.sistema.examenes.services;

import com.sistema.examenes.entity.Usuario;
import com.sistema.examenes.entity.UsuarioRol;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistema.examenes.repository.IRolRepository;
import com.sistema.examenes.repository.IUsuarioRepository;

@Service
public class UsuarioServicesImpl implements IUsuarioServices {

    @Autowired
    private IUsuarioRepository usuarioDao;

    @Autowired
    private IRolRepository rolDao;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = this.usuarioDao.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            throw new Exception("El usuario ya existe!");
        }
        else{
            for(UsuarioRol usuarioRol:usuarioRoles){
                this.rolDao.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = this.usuarioDao.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return this.usuarioDao.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(Long idUsuario) {
        this.usuarioDao.deleteById(idUsuario);
    }
}
