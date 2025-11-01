/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.repository;

import mx.gob.imss.dpes.bitacoraback.entity.McltBitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author salvador.pocteco
 */
public interface BitacoraRepository extends JpaRepository<McltBitacora, Long>,
        JpaSpecificationExecutor<McltBitacora> {

}
