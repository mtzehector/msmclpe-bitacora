/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.repository;

import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraCatImss;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 *
 * @author juanf.barragan
 */
public interface BitacoraCatImssRepository
        extends JpaRepository<McltBitacoraCatImss,Long>,JpaSpecificationExecutor<McltBitacoraCatImss> {

    @EntityGraph(attributePaths = {"mclcTipoEvento", "mcltDocumento"})
    List<McltBitacoraCatImss> findByBajaRegistroIsNullOrderByIdDesc();
}
