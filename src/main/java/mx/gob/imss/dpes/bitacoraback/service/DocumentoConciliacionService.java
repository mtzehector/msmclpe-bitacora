package mx.gob.imss.dpes.bitacoraback.service;

import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.bitacoraback.entity.McltDocumentoConciliacion;
import mx.gob.imss.dpes.bitacoraback.exception.DocumentoConciliacionException;
import mx.gob.imss.dpes.bitacoraback.repository.DocumentoConciliacionRepository;
import mx.gob.imss.dpes.common.enums.TipoDocumentoEnum;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.interfaces.bitacora.model.DocumentoConciliacionRequest;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.PageRequestModelConciliacion;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.interceptor.Interceptors;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class DocumentoConciliacionService extends BaseCRUDService<McltDocumentoConciliacion, McltDocumentoConciliacion, Long, Long> {

    @Autowired
    private DocumentoConciliacionRepository repository;

    public McltDocumentoConciliacion obtenerDocumentoConciliacion(Long cveEntidadFinanciera, Long cveTipoDocumento, String periodo ) throws BusinessException {
        try {
            return repository.obtenerDocumentoConciliacion(
                    cveEntidadFinanciera,
                    cveTipoDocumento,
                    periodo
            );
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionService.obtenerDocumentoConciliacion()",e);
            throw new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DE_LECTURA_DE_BD);
        }
    }

    public McltDocumentoConciliacion guardarDocumentoConciliacion(McltDocumentoConciliacion documentoConciliacion) throws BusinessException{
        try {
            return repository.save(documentoConciliacion);
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionService.guardarDocumentoConciliacion()",e);
            throw new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DE_ESCRITURA_EN_BD);
        }
    }

    public McltDocumentoConciliacion guardarFechaDescarga(Long cveCartaRecibo) throws BusinessException {
        try {
            McltDocumentoConciliacion documentoConciliacion = repository.findOne(cveCartaRecibo);
            documentoConciliacion.setFechaDescarga(new Date());
            return repository.save(documentoConciliacion);
        } catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionService.guardarFechaDescarga()", e);
            throw new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DE_ESCRITURA_EN_BD);
        }
    }

    public McltDocumentoConciliacion obtenerDocumento(Long cveTipoDocumento, String periodo ) throws BusinessException {
        try {
            return repository.obtenerDocumentoResumenConciliacion(cveTipoDocumento, periodo);
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionService.obtenerDocumento()",e);
            throw new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DE_LECTURA_DE_BD);
        }
    }

    public List<McltDocumentoConciliacion> obtenerCartaRecibo(Long cveEntidadFinanciera, Long cveTipoDocumento, String periodo ) throws BusinessException {
        try {
            return repository.obtenerCartaRecibo(
                    cveEntidadFinanciera,
                    cveTipoDocumento,
                    periodo
            );
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionService.obtenerCartaRecibo()",e);
            throw new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DE_LECTURA_DE_BD);
        }
    }

    public List<McltDocumentoConciliacion> obtenerListCartaReciboEFTodas(Long cvePerfil, String periodo ) throws BusinessException {
        try {
            List<Long> listTipoDocumento = new ArrayList<>();
            switch (cvePerfil.intValue()){
                case 6: //operadorIMSS
                    listTipoDocumento.add(TipoDocumentoEnum.CARTA_RECIBO_TITULAR_DIVISION.getTipo());
                    listTipoDocumento.add(TipoDocumentoEnum.CARTA_RECIBO_OPERADOR_EF.getTipo());
                    listTipoDocumento.add(TipoDocumentoEnum.CARTA_RECIBO_CON_FIRMA.getTipo());
                    break;
            }
            return filtroCartasEFTodas(repository.obtenerListCartaReciboEFTodas(
                    listTipoDocumento, periodo
            )); 
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionService.obtenerListCartaReciboEFTodas()",e);
            throw new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DE_LECTURA_DE_BD);
        }
    }
    
    public List<McltDocumentoConciliacion> filtrarCartaFirmadaOEF(List<McltDocumentoConciliacion> listCartaRecibo, List<Long> listaEFFirmaOperador) throws BusinessException {
        try {
        	 List<McltDocumentoConciliacion> listaNueva = new ArrayList<>();
             for (McltDocumentoConciliacion documento: listCartaRecibo) {
            	 if (documento.getDocumento().getTipoDocumento() == 36L) {
                     listaNueva.add(documento);
                 }
            	 boolean aux = true;
            	 for (long entidadOPFirma: listaEFFirmaOperador) {
            		 if(entidadOPFirma == documento.getEntidadFinanciera().getId()) {
            			 aux = false;
            			 if (documento.getDocumento().getTipoDocumento() == 35L) {
                             listaNueva.add(documento);
                         }
            		 }
            	 }
            	 if(aux && documento.getDocumento().getTipoDocumento() == 29L) {
            		 listaNueva.add(documento); 
            	 }
             }
          return listaNueva;
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionService.obtenerListCartaReciboEFTodas()",e);
            throw new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DE_LECTURA_DE_BD);
        }
    }

    public List<McltDocumentoConciliacion> filtroCartasEFTodas (List<McltDocumentoConciliacion> listaCartas) throws BusinessException {
        try {
        	
        	List<McltDocumentoConciliacion> listaFiltrada = new ArrayList<>();
        	boolean existeElementos = true;
        	while (existeElementos) {
        		if(listaCartas.size() > 0) {
        			McltDocumentoConciliacion carta = listaCartas.get(0);
        			if(listaFiltrada.size() > 0) {
        				boolean noExiste = true;
        				for(int x = 0; x < listaFiltrada.size(); x++) {
        					if(carta.getEntidadFinanciera().equals(listaFiltrada.get(x).getEntidadFinanciera())) {
        						noExiste = false;
        						if(listaFiltrada.get(x).getDocumento().getTipoDocumento() < carta.getDocumento().getTipoDocumento()) {
            	        			listaFiltrada.remove(x);
            	        			listaFiltrada.add(carta);
            	        			x = listaFiltrada.size() + 1 ;
            	        		}
        					}
        	        	}
        				if(noExiste) {
        					listaFiltrada.add(carta);
        				}
        				listaCartas.remove(0);
        			} else {
        				listaFiltrada.add(carta);
        				listaCartas.remove(0);
        			}
        		} else {
        			existeElementos = false;
        		}
        	}
            return listaFiltrada;
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionService.obtenerDocumento()",e);
            throw new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DE_LECTURA_DE_BD);
        }
    }
    
    public McltDocumentoConciliacion obtenerCartaReciboFirmadaAdministradorEF(Long cveEntidadFinanciera, Long cveTipoDocumento, String periodo ) throws BusinessException {
        try {
            return repository.obtenerCartaReciboFirmadaAdministradorEF(
                    cveEntidadFinanciera,
                    cveTipoDocumento,
                    periodo
            );
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionService.obtenerCartaReciboFirmadaAdministrarEF()",e);
            throw new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DE_LECTURA_DE_BD);
        }
    }
    
    public List<McltDocumentoConciliacion> obtenerUltimasCartas(Long entidadFinanciera, String periodo) throws BusinessException {
        try {
            return repository.obtenerUltimasCartas(entidadFinanciera,periodo);
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionService.obtenerDocumentoConciliacion()",e);
            throw new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DE_LECTURA_DE_BD);
        }
    }
    
    public McltDocumentoConciliacion filtroCartas (List<McltDocumentoConciliacion> listaCartas) throws BusinessException {
        try {
        	if(listaCartas.size() < 1) 
        		return null;
        	
        	McltDocumentoConciliacion ultimaCarta = listaCartas.get(0);
        	for(McltDocumentoConciliacion carta: listaCartas) {
        		if(ultimaCarta.getDocumento().getTipoDocumento() < carta.getDocumento().getTipoDocumento()) {
        			ultimaCarta = carta;
        		}
        	}
            return ultimaCarta;
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionService.obtenerDocumento()",e);
            throw new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DE_LECTURA_DE_BD);
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
