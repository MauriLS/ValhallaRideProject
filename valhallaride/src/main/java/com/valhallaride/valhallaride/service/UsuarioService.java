package com.valhallaride.valhallaride.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valhallaride.valhallaride.model.Usuario;
import com.valhallaride.valhallaride.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id){
        return usuarioRepository.getById(id);
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario updateUsuario(Long id, Usuario usuario){
        Usuario usuarioToUpdate = usuarioRepository.findById(id).orElse(null);
        if (usuarioToUpdate != null) {
            usuarioToUpdate.setNombreUsuario(usuario.getNombreUsuario());
            usuarioToUpdate.setCorreoUsuario(usuario.getCorreoUsuario());
            usuarioToUpdate.setContrasena(usuario.getContrasena());
            return usuarioRepository.save(usuarioToUpdate);
        } else {
            return null;
        }
    }

    public Usuario patchUsuario(Long id, Usuario parcialUsuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()){

            Usuario usuarioToUpdate = usuarioOptional.get();

            if (parcialUsuario.getNombreUsuario() != null){
                usuarioToUpdate.setNombreUsuario(parcialUsuario.getNombreUsuario());
            }
            
            if (parcialUsuario.getCorreoUsuario() != null){
                usuarioToUpdate.setCorreoUsuario(parcialUsuario.getCorreoUsuario());
            }

            if (parcialUsuario.getContrasena() != null){
                usuarioToUpdate.setContrasena(parcialUsuario.getContrasena());
            }

            return usuarioRepository.save(usuarioToUpdate);
        } else{
            return null;
        }
    }
}
