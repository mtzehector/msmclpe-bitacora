package mx.gob.imss.dpes.bitacoraback.service;

import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.bitacoraback.entity.McltDocumentoEFPS;
import mx.gob.imss.dpes.bitacoraback.exception.DocumentoEFPSException;
import mx.gob.imss.dpes.bitacoraback.repository.DocumentoEFPSRepository;
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
public class DocumentoEFPSService extends BaseCRUDService<McltDocumentoEFPS,McltDocumentoEFPS,Long,Long>{

    @Autowired
    private DocumentoEFPSRepository documentoEFPSRepository;

    @Override
    public JpaSpecificationExecutor<McltDocumentoEFPS> getRepository() {
        return documentoEFPSRepository;
    }

    @Override
    public JpaRepository<McltDocumentoEFPS, Long> getJpaRepository() {
        return documentoEFPSRepository;
    }
    
     public McltDocumentoEFPS execute (McltDocumentoEFPS request) throws BusinessException {
        try {
            McltDocumentoEFPS mcltDocumentoEFPS = save(request);
            mcltDocumentoEFPS = documentoEFPSRepository.findById(mcltDocumentoEFPS.getId());
            return mcltDocumentoEFPS;
        }
        catch(Exception e) {
            log.log(Level.SEVERE, "ERROR DocumentoEFPSService.execute ", e);
            throw new DocumentoEFPSException(DocumentoEFPSException.ERROR_DE_ESCRITURA_EN_BD);
        }
     }

     public List<McltDocumentoEFPS> obtenerDocumentosPorIdEntidadFinancieraYIdTipoPrestadorDeServicios(
             Long idEntidadFinanciera, Integer idTipoPrestadorDeServicios
     ) throws BusinessException {
         try {
             return documentoEFPSRepository.buscarDocumentosPorIdEntidadFinancieraYIdTipoPrestadorDeServicios(
                     idEntidadFinanciera, idTipoPrestadorDeServicios);
         }
         catch(Exception e) {
             log.log(Level.SEVERE,
                "ERROR DocumentoEFPSService.obtenerDocumentosPorIdEntidadFinancieraYIdTipoPrestadorDeServicios ",
                     e);
             throw new DocumentoEFPSException(DocumentoEFPSException.ERROR_DE_LECTURA_DE_BD);
         }
     }
    
}
