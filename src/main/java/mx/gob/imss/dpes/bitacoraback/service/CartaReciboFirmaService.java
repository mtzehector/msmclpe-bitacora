package mx.gob.imss.dpes.bitacoraback.service;

import mx.gob.imss.dpes.bitacoraback.exception.DocumentoConciliacionException;
import mx.gob.imss.dpes.bitacoraback.model.CartaReciboFirma;
import mx.gob.imss.dpes.bitacoraback.repository.CartaReciboFirmaRepository;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class CartaReciboFirmaService {

    private Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    private CartaReciboFirmaRepository repository;

    public List<CartaReciboFirma> obtenerListCartaReciboFirma(String periodo, Long tipoDocumento, Long cveEntidadFinanciera) throws BusinessException {
        try {
            return repository.obtenerListCartaReciboConFirma(periodo, tipoDocumento, cveEntidadFinanciera);
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR CartaReciboFirmaService.obtenerListCartaReciboFirma()", e);
            throw new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DE_LECTURA_DE_BD);
        }
    }

}
