/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.service;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraInterfaz;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraNotificacion;
import mx.gob.imss.dpes.bitacoraback.repository.BitacoraNotificacionRepository;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author edgar.arenas
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class BitacoraNotificacionService extends BaseCRUDService<McltBitacoraNotificacion, McltBitacoraNotificacion, Long, Long> {

    @Autowired
    private BitacoraNotificacionRepository bitacoraNotificacionRepository;
    
    @Override
    public JpaSpecificationExecutor<McltBitacoraNotificacion> getRepository() {
        return bitacoraNotificacionRepository;
    }

    @Override
    public JpaRepository<McltBitacoraNotificacion, Long> getJpaRepository() {
        return bitacoraNotificacionRepository;
    }
    
    public Message<McltBitacoraNotificacion> execute(Message<McltBitacoraNotificacion> request) throws BusinessException {
        McltBitacoraNotificacion saved = save(request.getPayload());
        return new Message<>(saved);
    }
    
}
