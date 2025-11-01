/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.persistence.BaseSpecification;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacora;
import mx.gob.imss.dpes.bitacoraback.repository.BitacoraFindBySolicitud;
import mx.gob.imss.dpes.bitacoraback.repository.BitacoraFindBySolicitudAndEstado;
import mx.gob.imss.dpes.bitacoraback.repository.BitacoraRepository;
import mx.gob.imss.dpes.common.enums.TipoEstadoSolicitudEnum;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author eduardo.loyo
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class BitacoraFindBySolicitudService extends BaseCRUDService<McltBitacora, McltBitacora, Long, Long> {

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

    public McltBitacora execute(Long id) throws BusinessException {
        Collection<BaseSpecification> constraints = new ArrayList<>();
        constraints.add( new BitacoraFindBySolicitudAndEstado( id, TipoEstadoSolicitudEnum.INICIADO.getTipo() ) );
        McltBitacora entity = findOne( constraints );
        return entity;
    }
    
    public List<McltBitacora> findbyIdSolicitud(Long id) throws BusinessException {
        Collection<BaseSpecification> constraints = new ArrayList<>();
        constraints.add(new BitacoraFindBySolicitud(id));
        List<McltBitacora> res = load(constraints);
        
        return res;
        
    }

}
