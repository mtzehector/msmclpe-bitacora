/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.service;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacora;
import mx.gob.imss.dpes.bitacoraback.repository.BitacoraRepository;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author salvador.pocteco
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class BitacoraPersistenceService extends BaseCRUDService<McltBitacora, McltBitacora, Long, Long> {

    @Autowired
    private BitacoraRepository bitacoraRepository;

    @Override
    public JpaSpecificationExecutor<McltBitacora> getRepository() {
        return bitacoraRepository;
    }

    @Override
    public JpaRepository<McltBitacora, Long> getJpaRepository() {
        return bitacoraRepository;
    }

    
    public Message<McltBitacora> execute(Message<McltBitacora> request) throws BusinessException {
        McltBitacora saved = save(request.getPayload());
        return new Message<>(saved);
    }
}
