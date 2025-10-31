/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.endpoint;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.gob.imss.dpes.bitacoraback.entity.MclcTareaEstadoOrigen;
import mx.gob.imss.dpes.bitacoraback.entity.McltCronTarea;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraInterfazException;
import mx.gob.imss.dpes.bitacoraback.service.CronTareaService;
import mx.gob.imss.dpes.bitacoraback.service.TareaAccionService;
import mx.gob.imss.dpes.bitacoraback.service.TareaEstadoOrigenService;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;

/**
 *
 * @author juan.garfias
 */
@ApplicationScoped
@Path("/cronTarea")
public class CronTareaEndPoint extends BaseGUIEndPoint<McltCronTarea, McltCronTarea, McltCronTarea> {

    @Inject
    private CronTareaService service;

    @Inject
    private TareaAccionService serviceTareaAccion;

    @Inject
    private TareaEstadoOrigenService tareaEstadoOrigenService;

    @GET
    @Path("/vencidas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTareasVencidas() throws BusinessException {
        //log.log(Level.INFO, "JGV OBTIENE TAREAS VENCIDAS ->");

        List<McltCronTarea> lstCronTareas = service.getTareasVencidas();

        for (McltCronTarea mct : lstCronTareas) {
            //log.log(Level.INFO, "JGV MCLT ->" + mct);

            mct.setLstEstadosOrigen(
                    tareaEstadoOrigenService.getTareaEstadoOrigenByCveTareaAccion(mct.getTareaAccion().getId())
            );

        }
        //log.log(Level.INFO, "JGV OBTIENE TAREAS VENCIDAS ->" + lstCronTareas);

        // List<MclcTareaEstadoOrigen> lstEstadosOrigen = tareaEstadoOrigenService.getTareaEstadoOrigenByCveTareaAccion(1L);
        return Response.ok(
                lstCronTareas
        ).build();

    }

    @POST
    @Path("/agregar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTarea(McltCronTarea tarea) throws BusinessException {
        log.log(Level.INFO, "JGV agrega TAREAS ->");

        tarea.setAltaRegistro(new Date());
        tarea.setActivo(1L);
        tarea.setEjecutado(0L);
        return Response.ok(
                service.add(tarea)
        ).build();

    }

    @PUT
    @Path("/actualizarPorJob")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTareaPorJob(McltCronTarea tarea) throws BusinessException {
        log.log(Level.INFO, "JGV Actualizar TAREA 1-> {o}" + tarea);

        McltCronTarea tareaUpdate = service.getTarea(tarea.getId());
        log.log(Level.INFO, "JGV Actualizar TAREA 2-> {o}" + tareaUpdate);

        tareaUpdate.setActualizacionRegistro(new Date());
        tareaUpdate.setActivo(tarea.getActivo());
        tareaUpdate.setEjecutado(tarea.getEjecutado());

        return Response.ok(
                service.add(tareaUpdate)
        ).build();

    }

    @GET
    @Path("/tarea/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTareaById(@PathParam("id") Long id) throws BusinessException {
        log.log(Level.INFO, "JGV OBTIENE TAREAS VENCIDAS ->");

        return Response.ok(
                serviceTareaAccion.getTareaById(id)
        ).build();

    }

    @GET
    @Path("/tareas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTareas() throws BusinessException {
        log.log(Level.INFO, "JGV OBTIENE CATALOGO DE TAREAS->");

        return Response.ok(
                serviceTareaAccion.getTareas()
        ).build();

    }
    
    @GET
    @Path("/tarea/solicitud/{cveSol}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTareabySolicitud(@PathParam("cveSol") Long cveSol) {

        try {
            //log.log(Level.INFO, "Se obtiene la tarea 11 de la solicitud {0}", cveSol);
            McltCronTarea ct = service.tareaBySolicitudAccion(cveSol);
            if (ct != null) {
                return Response.ok(ct).build();
            } else {
                return Response.noContent().build();
            }
        } catch (BusinessException be) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, be, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "CronTareaEndPoint.getTareabySolicitud - cveSol = ["
                    + cveSol + "]", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                new BitacoraInterfazException(BitacoraInterfazException.ERROR_DESCONOCIDO_EN_EL_SERVICIO), null));
        }
        
    }
}
