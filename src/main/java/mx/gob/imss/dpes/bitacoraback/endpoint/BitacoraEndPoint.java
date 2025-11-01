/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.endpoint;

import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacora;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraInterfaz;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraNotificacion;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraPersona;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraReporte;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraInterfazException;
import mx.gob.imss.dpes.bitacoraback.service.BitacoraFindBySolicitudService;
import mx.gob.imss.dpes.bitacoraback.service.BitacoraInterfazPersistenceService;
import mx.gob.imss.dpes.bitacoraback.service.BitacoraNotificacionService;
import mx.gob.imss.dpes.bitacoraback.service.BitacoraPersistenceService;
import mx.gob.imss.dpes.bitacoraback.service.BitacoraPersonaService;
import mx.gob.imss.dpes.bitacoraback.service.BitacoraReporteService;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.interfaces.bitacora.model.Bitacora;

/**
 *
 * @author salvador.pocteco
 */
@ApplicationScoped
@Path("/bitacora")
public class BitacoraEndPoint extends BaseGUIEndPoint<McltBitacora, McltBitacora, McltBitacora> {

    @Context
    private UriInfo uriInfo;

    @Inject
    private BitacoraPersistenceService bitacoraPersistenceService;

    @Inject
    private BitacoraInterfazPersistenceService bitacoraInterfazPersistenceService;

    @Inject
    private BitacoraFindBySolicitudService serviceFind;

    @Inject
    private BitacoraReporteService bitacoraReporteService;

    @Inject
    private BitacoraNotificacionService bitacoraNotificacionService;
    
    @Inject
    private BitacoraPersonaService bitacoraPersonaService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response create(McltBitacora mcltBitacora) throws BusinessException {
        Message<McltBitacora> execute = bitacoraPersistenceService.execute(new Message<McltBitacora>(mcltBitacora));
        return toResponse(execute);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/interfaz")
    public Response createBitacoraInterfaz(McltBitacoraInterfaz mcltBitacoraInterfaz) {
        try {
            Message<McltBitacoraInterfaz> execute = bitacoraInterfazPersistenceService.execute(
                new Message<McltBitacoraInterfaz>(mcltBitacoraInterfaz));
            return toResponse(execute);
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR BitacoraEndPoint.createBitacoraInterfaz mcltBitacoraInterfaz = ["
                + mcltBitacoraInterfaz + "]", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new BitacoraInterfazException(BitacoraInterfazException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }

    @GET
    @Path("/{id}")
    public Response getBitacora(@PathParam("id") Long id) {
        try {
            return Response.ok(uriInfo.getAbsolutePath()).entity(serviceFind.execute(id)).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, null, e);
            return Response.status(Status.NOT_FOUND).build();
        }

    }

    @POST
    @Path("/obtener")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBitacoraBySolicitud(Bitacora bitacora) {
        try {

            return Response.ok(uriInfo.getAbsolutePath()).entity(serviceFind.execute(bitacora.getIdSolicitud())).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, null, e);
            return Response.status(Status.NOT_FOUND).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/notificacion")
    public Response createBitacoraNotificacion(McltBitacoraNotificacion request) throws BusinessException {
        log.log(Level.INFO, ">>>BITACORABACK|BitacoraEndPoint|createBitacoraNotificacion {0}", request);
        Message<McltBitacoraNotificacion> execute = bitacoraNotificacionService.execute(new Message<McltBitacoraNotificacion>(request));
        return toResponse(execute);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/reporte")
    public Response createBitacoraReporte(McltBitacoraReporte request) throws BusinessException {
        log.log(Level.INFO, ">>>BITACORABACK|BitacoraEndPoint|createBitacoraReporte {0}", request);
        Message<McltBitacoraReporte> execute = bitacoraReporteService.execute(new Message<McltBitacoraReporte>(request));
        return toResponse(execute);
    }
    
    @POST
    @Path("/registros")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBitacorasBySolicitud(Bitacora bitacora) {
        try {

            return Response.ok(uriInfo.getAbsolutePath()).entity(serviceFind.findbyIdSolicitud(bitacora.getIdSolicitud())).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, null, e);
            return Response.status(Status.NOT_FOUND).build();
        }

    }
    
    @POST
    @Path("/bitacoraPersona")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBitacoraPersona (McltBitacoraPersona request)throws BusinessException{
        log.log(Level.INFO, ">>>BITACORABACK|BitacoraEndPoint|createBitacoraPersona {0}", request);
        Message<McltBitacoraPersona> execute = bitacoraPersonaService.execute(new Message<McltBitacoraPersona>(request));
        return toResponse(execute);
    }
    

}
