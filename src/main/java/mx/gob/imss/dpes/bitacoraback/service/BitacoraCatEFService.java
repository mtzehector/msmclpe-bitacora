package mx.gob.imss.dpes.bitacoraback.service;

import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraCatEF;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraCatEFException;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraCatImssException;
import mx.gob.imss.dpes.bitacoraback.repository.BitacoraCatEFRepository;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.logging.Level;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class BitacoraCatEFService extends BaseCRUDService<McltBitacoraCatEF,McltBitacoraCatEF,Long,Long>{

    @Autowired
    private BitacoraCatEFRepository bitacoraCatEFRepository;

    @Override
    public JpaSpecificationExecutor<McltBitacoraCatEF> getRepository() {
        return bitacoraCatEFRepository;
    }

    @Override
    public JpaRepository<McltBitacoraCatEF, Long> getJpaRepository() {
        return bitacoraCatEFRepository;
    }
    
     public Message<McltBitacoraCatEF> execute (Message<McltBitacoraCatEF> request) throws BusinessException {
        try {
            McltBitacoraCatEF saved = save(request.getPayload());
            return new Message<>(saved);
        }
        catch(Exception e) {
            log.log(Level.SEVERE, "ERROR BitacoraCatEFService.execute ", e);
            throw new BitacoraCatEFException(BitacoraCatEFException.ERROR_DE_ESCRITURA_EN_BD);
        }
     }

    public List<McltBitacoraCatEF> getBitacoraCatEFList(Long mclcCondicionOfertaId) throws BusinessException {
        try {
            return bitacoraCatEFRepository.findByMclcCondicionOfertaId(mclcCondicionOfertaId);
        }
        catch(Exception e) {
            log.log(Level.SEVERE, "ERROR BitacoraCatEFService.getBitacoraCatEFList ", e);
            throw new BitacoraCatImssException(BitacoraCatImssException.ERROR_DE_LECTURA_DE_BD);
        }
    }
    
}
