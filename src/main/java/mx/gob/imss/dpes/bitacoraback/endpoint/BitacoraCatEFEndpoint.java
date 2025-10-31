/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.endpoint;

import mx.gob.imss.dpes.bitacoraback.assembler.AssambleBitacoraCatEFFromMcltBitacoraCatEF;
import mx.gob.imss.dpes.bitacoraback.assembler.AssambleMcltBitacoraCatEFFromBitacoraCatEF;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraCatEF;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraCatEFException;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraCatImssException;
import mx.gob.imss.dpes.bitacoraback.service.BitacoraCatEFService;
import mx.gob.imss.dpes.bitacoraback.validation.ValidacionDeSolicitudBitacoraCatEF;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraCatEF;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

@ApplicationScoped
@Path("/bitacoraCatEF")
public class BitacoraCatEFEndpoint extends BaseGUIEndPoint<McltBitacoraCatEF,McltBitacoraCatEF,McltBitacoraCatEF>{

    @Inject
    private AssambleMcltBitacoraCatEFFromBitacoraCatEF assambleMcltBitacoraCatEFFromBitacoraCatEF;

    @Inject
    private AssambleBitacoraCatEFFromMcltBitacoraCatEF assambleBitacoraCatEFFromMcltBitacoraCatEF;

    @Inject
    private ValidacionDeSolicitudBitacoraCatEF validationRequestBitacoraCatEF;

    @Inject
    private BitacoraCatEFService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(BitacoraCatEF request) {

        try {
            if(!validationRequestBitacoraCatEF.esBitacoraCatEFValidoParaInsercionEnBD(request))
                return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                        new BitacoraCatEFException(BitacoraCatEFException.MENSAJE_DE_SOLICITUD_INCORRECTO),
                        null));

            Message<McltBitacoraCatEF> saved = service.execute(new Message<>(
                    assambleMcltBitacoraCatEFFromBitacoraCatEF.assemble(request)));
            request.setId(saved.getPayload().getId());
            request.setAltaRegistro(saved.getPayload().getAltaRegistro());
            return toResponse(new Message<>(request));
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR BitacoraCatEFEndpoint.create ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new BitacoraCatEFException(BitacoraCatEFException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }

    @POST
    @Path("/bitacoras")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBitacoras(List<BitacoraCatEF> request) {

        try {
            if(!validationRequestBitacoraCatEF.esListaBitacoraCatEFValidoParaInsercionEnBD(request))
                return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                        new BitacoraCatEFException(BitacoraCatEFException.MENSAJE_DE_SOLICITUD_INCORRECTO),
                        null));

            for(BitacoraCatEF bitacoraCatEF : request) {
                Message<McltBitacoraCatEF> saved = service.execute(new Message<>(
                        assambleMcltBitacoraCatEFFromBitacoraCatEF.assemble(bitacoraCatEF)));
                bitacoraCatEF.setId(saved.getPayload().getId());
                bitacoraCatEF.setAltaRegistro(saved.getPayload().getAltaRegistro());
            }

            return toResponse(new Message<>((Serializable) request));
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR BitacoraCatEFEndpoint.create ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new BitacoraCatEFException(BitacoraCatEFException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }

    @GET
    @Path("/bitacoras/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBitacoraList(@PathParam("id") Long mclcCondicionOfertaId) {
        try {
            List<McltBitacoraCatEF> mcltBitacoraCatEFList = service.getBitacoraCatEFList(mclcCondicionOfertaId);

            return Response.ok(
                    assambleBitacoraCatEFFromMcltBitacoraCatEF.assembleList(mcltBitacoraCatEFList)
            ).build();
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR BitacoraCatEFEndpoint.getBitacoraList ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new BitacoraCatImssException(BitacoraCatImssException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }
}
