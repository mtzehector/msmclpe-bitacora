/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.repository;

import java.util.List;
import mx.gob.imss.dpes.bitacoraback.entity.McltCronTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author juan.garfias
 */
public interface CronTareaRepository extends JpaRepository<McltCronTarea, Long>,
        JpaSpecificationExecutor<McltCronTarea> {

    @Query(value = "select * from MCLT_CRON_TAREA "
            + "where fecha_limite < current_timestamp and activo=1 and ejecutado=0",
            nativeQuery = true)
    List<McltCronTarea> findByFechaVencidaAndActivoAndNoEjecutado();

}
