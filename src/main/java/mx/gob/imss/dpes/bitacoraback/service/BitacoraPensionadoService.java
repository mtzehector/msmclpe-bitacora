package mx.gob.imss.dpes.bitacoraback.service;

import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraPensionado;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraPensionadoException;
import mx.gob.imss.dpes.bitacoraback.repository.BitacoraPensionadoRepository;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.PageRequestModel;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraPensionadoRequest;
import mx.gob.imss.dpes.interfaces.bitacora.model.TipoModificacionRequest;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class BitacoraPensionadoService  extends BaseCRUDService<McltBitacoraPensionado, McltBitacoraPensionado, Long, Long> {

    @Autowired
    private BitacoraPensionadoRepository bitacoraPensionadoRepository;

    @Override
    public JpaSpecificationExecutor<McltBitacoraPensionado> getRepository() {
        return bitacoraPensionadoRepository;
    }

    @Override
    public JpaRepository<McltBitacoraPensionado, Long> getJpaRepository() {
        return bitacoraPensionadoRepository;
    }

    public Message<McltBitacoraPensionado> execute(Message<McltBitacoraPensionado> request) throws BusinessException {
        try {
            McltBitacoraPensionado saved = save(request.getPayload());
            return new Message<>(saved);
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR::BitacoraPensionadoService.execute {0}", e);
            throw new BitacoraPensionadoException(BitacoraPensionadoException.ERROR_DE_ESCRITURA_EN_BD);
        }
    }

    public List<BitacoraPensionadoRequest> findByCvePerson(PageRequestModel<BitacoraPensionadoRequest> request) throws BusinessException {
        try {
            List<McltBitacoraPensionado> mcltBitacoraPensionados = bitacoraPensionadoRepository.findByCvePersonaOrderByIdDesc(request.getModel().getCvePersona());
            List<BitacoraPensionadoRequest> bitacoraPensionadoRequests = new ArrayList<>();
            for (McltBitacoraPensionado mcltBitacoraPensionado : mcltBitacoraPensionados) {
                bitacoraPensionadoRequests.add(create(mcltBitacoraPensionado));
            }
            return bitacoraPensionadoRequests;
       } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR::BitacoraPensionadoService.findByCvePersona {0}", e);
            throw new BitacoraPensionadoException(BitacoraPensionadoException.ERROR_AL_EJECUTAR_CONSULTA);
       }
    }

    public BitacoraPensionadoRequest create(McltBitacoraPensionado mcltBitacoraPensionado) {
        BitacoraPensionadoRequest bitacoraPensionadoRequest = new BitacoraPensionadoRequest();
        bitacoraPensionadoRequest.setId(mcltBitacoraPensionado.getId());
        bitacoraPensionadoRequest.setCveCurp(mcltBitacoraPensionado.getCveCurp());
        bitacoraPensionadoRequest.setCvePersona(mcltBitacoraPensionado.getCvePersona());
        bitacoraPensionadoRequest.setValorAnterior(mcltBitacoraPensionado.getValorAnterior());
        bitacoraPensionadoRequest.setValorActual(mcltBitacoraPensionado.getValorActual());
        TipoModificacionRequest mclcTipoModificacion1 = new TipoModificacionRequest();
        mclcTipoModificacion1.setCveTipoModificacion(mcltBitacoraPensionado.getMclcTipoModificacion().getCveTipoModificacion());
        mclcTipoModificacion1.setNombreTipoModificacion(mcltBitacoraPensionado.getMclcTipoModificacion().getNombreTipoModificacion());
        bitacoraPensionadoRequest.setTipoModificacion(mclcTipoModificacion1);
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        //String strDate = dateFormat.format(mcltBitacoraPensionado.getAltaRegistro());
        //bitacoraPensionadoRequest.setFecRegistro(strDate);
        bitacoraPensionadoRequest.setFecRegistro(mcltBitacoraPensionado.getAltaRegistro());
        return bitacoraPensionadoRequest;
    }

    public List<Object> saveAll(List<BitacoraPensionadoRequest> mcltBitacoraPensionados) throws BusinessException {
        List<Object> objects = new ArrayList<>();
        try {
            for (BitacoraPensionadoRequest bitacoraPensionadoRequest : mcltBitacoraPensionados) {
                McltBitacoraPensionado mcltBitacoraPensionado = new McltBitacoraPensionado(bitacoraPensionadoRequest);
                objects.add(this.bitacoraPensionadoRepository.save(mcltBitacoraPensionado));
            }
            return objects;
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR::BitacoraPensionadoService.saveAll {0}", e);
            throw new BitacoraPensionadoException(BitacoraPensionadoException.ERROR_DE_ESCRITURA_EN_BD);
        }
    }

}
