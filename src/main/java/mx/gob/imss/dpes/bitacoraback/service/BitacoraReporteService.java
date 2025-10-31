/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.service;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraReporte;
import mx.gob.imss.dpes.bitacoraback.repository.BitacoraReporteRepository;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author juan.garfias
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class BitacoraReporteService extends BaseCRUDService<McltBitacoraReporte, McltBitacoraReporte, Long, Long> {

    @Autowired
    private BitacoraReporteRepository bitacoraReporteRepository;

    @Override
    public JpaSpecificationExecutor<McltBitacoraReporte> getRepository() {
        return bitacoraReporteRepository;
    }

    @Override
    public JpaRepository<McltBitacoraReporte, Long> getJpaRepository() {
        return bitacoraReporteRepository;
    }

    public Message<McltBitacoraReporte> execute(Message<McltBitacoraReporte> request) throws BusinessException {
        McltBitacoraReporte saved = save(request.getPayload());
        return new Message<>(saved);
    }
}
