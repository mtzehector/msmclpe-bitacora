/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.service;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;

import mx.gob.imss.dpes.bitacoraback.exception.BitacoraInterfazException;
import mx.gob.imss.dpes.bitacoraback.repository.BitacoraInterfazRepository;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraInterfaz;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.logging.Level;

/**
 *
 * @author salvador.pocteco
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class BitacoraInterfazPersistenceService extends BaseCRUDService<McltBitacoraInterfaz, McltBitacoraInterfaz, Long, Long> {

    @Autowired
    private BitacoraInterfazRepository bitacoraInterfazRepository;

    @Override
    public JpaSpecificationExecutor<McltBitacoraInterfaz> getRepository() {
        return bitacoraInterfazRepository;
    }

    @Override
    public JpaRepository<McltBitacoraInterfaz, Long> getJpaRepository() {
        return bitacoraInterfazRepository;
    }

    private String ajustaValorCadena(Long sesion, String valor, int numPosiciones) {
        if (valor == null)
            return valor;

        if (valor.isEmpty())
            return valor;

        if(valor.length() <= numPosiciones)
            return valor;

        log.log(Level.WARNING,
                "BitacoraInterfazPersistenceService.ajustaValorCadena - sesion = [" + sesion + "], valor = [" +
                        valor + "], numPosiciones = [" + numPosiciones + "]");
        return valor.substring(0, numPosiciones);
    }

    private McltBitacoraInterfaz actualizaMensaje(McltBitacoraInterfaz mcltBitacoraInterfaz) {
        Long sesion = mcltBitacoraInterfaz.getSesion();
        mcltBitacoraInterfaz.setDescRequest(ajustaValorCadena(sesion, mcltBitacoraInterfaz.getDescRequest(), 4000));
        mcltBitacoraInterfaz.setDescRespuesta(ajustaValorCadena(sesion, mcltBitacoraInterfaz.getDescRespuesta(), 4000));
        mcltBitacoraInterfaz.setEndpoint(ajustaValorCadena(sesion, mcltBitacoraInterfaz.getEndpoint(), 1000));
        return mcltBitacoraInterfaz;
    }
    
    public Message<McltBitacoraInterfaz> execute(Message<McltBitacoraInterfaz> request) throws BusinessException {
        try {
            McltBitacoraInterfaz saved = save(actualizaMensaje(request.getPayload()));
            return new Message<>(saved);
        } catch(Exception e) {
            log.log(Level.SEVERE,
                "ERROR BitacoraInterfazPersistenceService.execute - request = [" + request + "]", e);
            throw new BitacoraInterfazException(BitacoraInterfazException.ERROR_DE_ESCRITURA_EN_BD);
        }
    }
}
