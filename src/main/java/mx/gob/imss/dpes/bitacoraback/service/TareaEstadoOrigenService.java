/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.service;

import java.util.List;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.bitacoraback.entity.MclcTareaEstadoOrigen;
import mx.gob.imss.dpes.bitacoraback.repository.TareaEstadoOrigenRepository;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author juan.garfias
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class TareaEstadoOrigenService {

    @Autowired
    private TareaEstadoOrigenRepository repository;

    
    public List<MclcTareaEstadoOrigen> getTareaEstadoOrigenByCveTareaAccion(Long id) {
        
        return repository.findByCveTareaAccion(id);
    
    }
    
}
