package mx.gob.imss.dpes.bitacoraback.service;

import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.bitacoraback.entity.McltDocumentoConciliacion;
import mx.gob.imss.dpes.bitacoraback.exception.DocumentoConciliacionException;
import mx.gob.imss.dpes.bitacoraback.repository.DocumentoConciliacionRepository;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class DocumentoConciliacionActualizarService extends BaseCRUDService<McltDocumentoConciliacion, McltDocumentoConciliacion, Long, Long> {

    @Autowired
    private DocumentoConciliacionRepository repository;

    public McltDocumentoConciliacion actualizarCampoErogacion(Long cveEntidadFinanciera, Long cveTipoDocumento, String periodo) throws BusinessException {
        try {
            McltDocumentoConciliacion cartaRecibo = repository.obtenerDocumentoConciliacion(
                    cveEntidadFinanciera,
                    cveTipoDocumento,
                    periodo
            );
            if (cartaRecibo != null) {
                cartaRecibo.setErogacion(true);
                cartaRecibo = repository.save(cartaRecibo);
                return cartaRecibo;
            }
            return null;
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionActualizarService.actualizarCampoErogacion()",e);
            throw new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DE_ESCRITURA_EN_BD);
        }
    }


    @Override
    public JpaSpecificationExecutor<McltDocumentoConciliacion> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<McltDocumentoConciliacion, Long> getJpaRepository() {
        return repository;
    }
}
