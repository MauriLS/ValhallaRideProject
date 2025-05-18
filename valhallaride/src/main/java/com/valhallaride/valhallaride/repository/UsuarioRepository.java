package com.valhallaride.valhallaride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.valhallaride.valhallaride.model.Usuario;
import java.util.List;
import java.util.Optional;


public interface  UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    List<Usuario> findByNombreUsuario(String nombreUsuario); // Chicos! Este devuelve una lista porque puede haber varios usuarios con el mismo nombre
    Optional<Usuario> findByCorreoUsuario(String correoUsuario); // En este caso, usamos "Optional", ya que el correo es unico, y tambien esta la posibilidad de que no exista
    List<Usuario> findByRol_idRol(Integer idRol); // Aqui buscamos todos los usuarios que tengan un rol específico (por el id de rol)
}
