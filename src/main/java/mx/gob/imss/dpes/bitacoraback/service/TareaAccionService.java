/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.service;

import java.util.List;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.bitacoraback.entity.MclcTareaAccion;
import mx.gob.imss.dpes.bitacoraback.entity.McltCronTarea;
import mx.gob.imss.dpes.bitacoraback.repository.CronTareaRepository;
import mx.gob.imss.dpes.bitacoraback.repository.TareaAccionRepository;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author juan.garfias
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class TareaAccionService {

    @Autowired
    private TareaAccionRepository repository;

    public MclcTareaAccion getTareaById(Long id) {

        return repository.findOne(id);

    }

    public List<MclcTareaAccion> getTareas() {

        return repository.findAll();

    }
}
