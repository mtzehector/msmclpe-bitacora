/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.repository;

import java.util.List;
import mx.gob.imss.dpes.bitacoraback.entity.MclcTareaEstadoOrigen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author juan.garfias
 */
public interface TareaEstadoOrigenRepository extends JpaRepository<MclcTareaEstadoOrigen, Long>,
        JpaSpecificationExecutor<MclcTareaEstadoOrigen> {

    List<MclcTareaEstadoOrigen> findByCveTareaAccion(Long cveTareaAccion);
    
}
