/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.endpoint;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mx.gob.imss.dpes.bitacoraback.assembler.AssambleBitacoraCatImssFromMcltBitacoraCatImss;
import mx.gob.imss.dpes.bitacoraback.assembler.AssambleMcltBitacoraCatImssFromBitacoraCatImss;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraCatImssException;
import mx.gob.imss.dpes.bitacoraback.validation.ValidacionDeSolicitudBitacoraCatImss;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraCatImss;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraCatImss;
import mx.gob.imss.dpes.bitacoraback.service.BitacoraCatImssService;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;

import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author juanf.barragan
 */

@ApplicationScoped
@Path("/bitacoraCatImss")
public class BitacoraCatImssEndpoint extends BaseGUIEndPoint<McltBitacoraCatImss,McltBitacoraCatImss,McltBitacoraCatImss>{

    @Inject
    private AssambleMcltBitacoraCatImssFromBitacoraCatImss assambleMcltBitacoraCatImssFromBitacoraCatImss;

    @Inject
    private AssambleBitacoraCatImssFromMcltBitacoraCatImss assambleBitacoraCatImssFromMcltBitacoraCatImss;

    @Inject
    private ValidacionDeSolicitudBitacoraCatImss validationRequestBitacoraCatImss;

    @Inject
    private BitacoraCatImssService service;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(BitacoraCatImss request) {

        try {
            if(!validationRequestBitacoraCatImss.esBitacoraCatImssValidoParaInsercionEnBD(request))
                return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                        new BitacoraCatImssException(BitacoraCatImssException.MENSAJE_DE_SOLICITUD_INCORRECTO),
                        null));

            Message<McltBitacoraCatImss> saved = service.execute(new Message<>(
                    assambleMcltBitacoraCatImssFromBitacoraCatImss.assemble(request)));
            request.setId(saved.getPayload().getId());
            return toResponse(new Message<>(request));
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR BitacoraCatImssEndpoint.create ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new BitacoraCatImssException(BitacoraCatImssException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }

    @GET
    @Path("/bitacoras")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBitacoraList() {
        try {
            List<McltBitacoraCatImss> mcltBitacoraCatImssList = service.getBitacoraCatImssList();

            return Response.ok(
                    assambleBitacoraCatImssFromMcltBitacoraCatImss.assembleList(mcltBitacoraCatImssList)
            ).build();
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR BitacoraCatImssEndpoint.getBitacoraList ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new BitacoraCatImssException(BitacoraCatImssException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }
}
