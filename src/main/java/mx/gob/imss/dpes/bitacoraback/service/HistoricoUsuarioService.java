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
import mx.gob.imss.dpes.bitacoraback.entity.McltHistoricoUsuario;
import mx.gob.imss.dpes.bitacoraback.repository.HistoricoUsuarioRepository;
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
public class HistoricoUsuarioService extends BaseCRUDService<McltHistoricoUsuario, McltHistoricoUsuario, Long, Long>{
    
    @Autowired 
    private HistoricoUsuarioRepository historicoUsuarioRepository;
    
    @Override
    public JpaSpecificationExecutor<McltHistoricoUsuario> getRepository() {
        return historicoUsuarioRepository;
    }

    @Override
    public JpaRepository<McltHistoricoUsuario, Long> getJpaRepository() {
        return historicoUsuarioRepository;
    }
    
    public Message<McltHistoricoUsuario> execute(Message<McltHistoricoUsuario> request) throws BusinessException{
        McltHistoricoUsuario saved = save(request.getPayload());
        return new Message<>(saved);
        
    }
}
