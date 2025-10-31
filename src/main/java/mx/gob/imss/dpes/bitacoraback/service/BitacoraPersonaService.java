/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.service;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraPersona;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraReporte;
import mx.gob.imss.dpes.bitacoraback.repository.BitacoraPersonaRepository;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author juanf.barragan
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class BitacoraPersonaService extends BaseCRUDService<McltBitacoraPersona, McltBitacoraPersona, Long, Long>  {

    @Autowired 
    private BitacoraPersonaRepository bitacoraPersonaRepository;
    @Override
    public JpaSpecificationExecutor<McltBitacoraPersona> getRepository() {
        return bitacoraPersonaRepository;
    }

    @Override
    public JpaRepository<McltBitacoraPersona, Long> getJpaRepository() {
        return bitacoraPersonaRepository;
    }
    
    
    public Message<McltBitacoraPersona> execute (Message<McltBitacoraPersona> request) throws BusinessException{
        McltBitacoraPersona saved = save(request.getPayload());
        return new Message<>(saved);
    }
    
    
}
