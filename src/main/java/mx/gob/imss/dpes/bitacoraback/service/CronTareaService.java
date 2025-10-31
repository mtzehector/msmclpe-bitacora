/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.service;

import java.util.List;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.bitacoraback.entity.McltCronTarea;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraCatEFException;
import mx.gob.imss.dpes.bitacoraback.repository.CronTareaRepository;
import mx.gob.imss.dpes.bitacoraback.repository.TareaBySolicitudAccionSpecification;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author juan.garfias
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class CronTareaService {

    @Autowired
    private CronTareaRepository repository;

    public List<McltCronTarea> getTareasVencidas() {

        return repository.findByFechaVencidaAndActivoAndNoEjecutado();

    }

    public McltCronTarea add(McltCronTarea tarea) {

        return repository.save(tarea);

    }

    public McltCronTarea update(McltCronTarea tarea) {

        return repository.save(tarea);

    }

    public McltCronTarea getTarea(Long id) {

        return repository.findOne(id);

    }
    
    public McltCronTarea tareaBySolicitudAccion(Long cveSol) throws BusinessException {
        try {
            return repository.findOne(new TareaBySolicitudAccionSpecification(cveSol));
        } catch(Exception e) {
            throw new BitacoraCatEFException(BitacoraCatEFException.ERROR_DE_LECTURA_DE_BD);
        }
    }
}
