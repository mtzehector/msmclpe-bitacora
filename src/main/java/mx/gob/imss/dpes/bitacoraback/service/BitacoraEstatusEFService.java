package mx.gob.imss.dpes.bitacoraback.service;

import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraEstatusEF;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraEstatusEFException;
import mx.gob.imss.dpes.bitacoraback.repository.BitacoraEstatusEFRepository;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class BitacoraEstatusEFService extends BaseCRUDService<McltBitacoraEstatusEF, McltBitacoraEstatusEF, Long, Long> {

    @Autowired
    private BitacoraEstatusEFRepository bitacoraEstatusEFRepository;

    @Override
    public JpaSpecificationExecutor<McltBitacoraEstatusEF> getRepository() {
        return bitacoraEstatusEFRepository;
    }

    @Override
    public JpaRepository<McltBitacoraEstatusEF, Long> getJpaRepository() {
        return bitacoraEstatusEFRepository;
    }

    public Message<McltBitacoraEstatusEF> execute(Message<McltBitacoraEstatusEF> request) throws BusinessException {
        try {
            McltBitacoraEstatusEF saved = save(request.getPayload());
            return new Message<>(saved);
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR BitacoraEstatusEFService.execute ", e);
            throw new BitacoraEstatusEFException(BitacoraEstatusEFException.ERROR_DE_ESCRITURA_EN_BD);
        }
    }

}
