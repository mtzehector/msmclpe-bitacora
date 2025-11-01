package mx.gob.imss.dpes.bitacoraback.endpoint;

import mx.gob.imss.dpes.bitacoraback.assembler.AssambleBitacoraEstatusEFE;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraEstatusEF;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraEstatusEFException;
import mx.gob.imss.dpes.bitacoraback.service.BitacoraEstatusEFService;
import mx.gob.imss.dpes.bitacoraback.validation.ValidacionDeSolicitudBitacoraEstatusEF;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraEstatusEF;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;

@ApplicationScoped
@Path("/bitacoraEstatusEF")
public class BitacoraEstatusEFEndpoint extends BaseGUIEndPoint<McltBitacoraEstatusEF, McltBitacoraEstatusEF, McltBitacoraEstatusEF> {

    @Inject
    private BitacoraEstatusEFService service;

    @Inject
    private ValidacionDeSolicitudBitacoraEstatusEF validacionDeSolicitudBitacoraEstatusEF;

    @Inject
    private AssambleBitacoraEstatusEFE assambleBitacoraEstatusEFE;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearBitacoraEstatusEF(BitacoraEstatusEF request) {
        try {
            if (!validacionDeSolicitudBitacoraEstatusEF.esBitacoraEstatusEFValidoParaInsercionEnBD(request)) {
                return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                        new BitacoraEstatusEFException(BitacoraEstatusEFException.MENSAJE_DE_SOLICITUD_INCORRECTO),
                        null));
            }
            Message<McltBitacoraEstatusEF> saved = service.execute(new Message<>(
                    assambleBitacoraEstatusEFE.assemble(request)));
            request.setId(saved.getPayload().getId());
            return toResponse(new Message<>(request));
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR BitacoraEstatusEFEndpoint.crearBitacoraEstatusEF ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new BitacoraEstatusEFException(BitacoraEstatusEFException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }
}
