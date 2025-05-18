package com.valhallaride.valhallaride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.valhallaride.valhallaride.model.Orden;
import java.util.List;
import java.sql.Date;

public interface OrdenRepository extends JpaRepository<Orden, Long> {

 /*
 List <Orden> findByIdOrden(Integer idOrden); -> Esta no se usa ya que idOrden es clave primaria
*/

List <Orden> findByFecha(Date fecha);

List <Orden> findByTotal(Integer total);

}
