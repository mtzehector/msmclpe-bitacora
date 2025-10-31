/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.service;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraCatImss;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraCatImssException;
import mx.gob.imss.dpes.bitacoraback.repository.BitacoraCatImssRepository;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author juanf.barragan
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class BitacoraCatImssService extends BaseCRUDService<McltBitacoraCatImss,McltBitacoraCatImss,Long,Long>{

    @Autowired
    private BitacoraCatImssRepository bitacoraCatImssRepository;

    @Override
    public JpaSpecificationExecutor<McltBitacoraCatImss> getRepository() {
        return bitacoraCatImssRepository;
    }

    @Override
    public JpaRepository<McltBitacoraCatImss, Long> getJpaRepository() {
        return bitacoraCatImssRepository;
    }
    
     public Message<McltBitacoraCatImss> execute (Message<McltBitacoraCatImss> request) throws BusinessException {
        try {
            McltBitacoraCatImss saved = save(request.getPayload());
            return new Message<>(saved);
        }
        catch(Exception e) {
            log.log(Level.SEVERE, "ERROR BitacoraCatImssService.execute ", e);
            throw new BitacoraCatImssException(BitacoraCatImssException.ERROR_DE_ESCRITURA_EN_BD);
        }
     }

     public List<McltBitacoraCatImss> getBitacoraCatImssList() throws BusinessException {
         try {
             return bitacoraCatImssRepository.findByBajaRegistroIsNullOrderByIdDesc();
         }
         catch(Exception e) {
             log.log(Level.SEVERE, "ERROR BitacoraCatImssService.getBitacoraCatImssList ", e);
             throw new BitacoraCatImssException(BitacoraCatImssException.ERROR_DE_LECTURA_DE_BD);
         }
     }
    
}
