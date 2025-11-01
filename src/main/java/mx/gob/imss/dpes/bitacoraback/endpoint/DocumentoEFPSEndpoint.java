package mx.gob.imss.dpes.bitacoraback.endpoint;

import mx.gob.imss.dpes.bitacoraback.assembler.AssambleDocumentoEFPSFromMcltDocumentoEFPS;
import mx.gob.imss.dpes.bitacoraback.assembler.AssambleMcltDocumentoEFPSFromDocumentoEFPS;
import mx.gob.imss.dpes.bitacoraback.entity.McltDocumentoEFPS;
import mx.gob.imss.dpes.bitacoraback.exception.DocumentoEFPSException;
import mx.gob.imss.dpes.bitacoraback.service.DocumentoEFPSService;
import mx.gob.imss.dpes.bitacoraback.validation.ValidacionDeSolicitudDocumentoEFPS;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.interfaces.bitacora.model.DocumentoEFPS;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@ApplicationScoped
@Path("/documentoEFPS")
public class DocumentoEFPSEndpoint extends BaseGUIEndPoint<McltDocumentoEFPS,McltDocumentoEFPS,McltDocumentoEFPS>{

    @Inject
    private AssambleMcltDocumentoEFPSFromDocumentoEFPS assambleMcltDocumentoEFPSFromDocumentoEFPS;

    @Inject
    private AssambleDocumentoEFPSFromMcltDocumentoEFPS assambleDocumentoEFPSFromMcltDocumentoEFPS;

    @Inject
    private ValidacionDeSolicitudDocumentoEFPS validacionDeSolicitudDocumentoEFPS;

    @Inject
    private DocumentoEFPSService service;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearDocumento(DocumentoEFPS request) {

        try {
            if(!validacionDeSolicitudDocumentoEFPS.esDocumentoEFPSValidoParaInsercionEnBD(request))
                return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                        new DocumentoEFPSException(DocumentoEFPSException.MENSAJE_DE_SOLICITUD_INCORRECTO),
                        null));

            return toResponse(new Message<>(assambleDocumentoEFPSFromMcltDocumentoEFPS.assemble(
                    service.execute(assambleMcltDocumentoEFPSFromDocumentoEFPS.assemble(request)))));

        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR DocumentoEFPSEndpoint.crear ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new DocumentoEFPSException(DocumentoEFPSException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }

    private List<McltDocumentoEFPS> guardarDocumentos(List<DocumentoEFPS> listaDocumentos)
            throws BusinessException {
        List<McltDocumentoEFPS> lista = new ArrayList<McltDocumentoEFPS>();

        try {
            for(DocumentoEFPS documento : listaDocumentos) {
                lista.add(
                        service.execute(
                                assambleMcltDocumentoEFPSFromDocumentoEFPS.assemble(documento))
                );
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR DocumentoEFPSEndpoint.guardarDocumentos ", e);
            throw new DocumentoEFPSException(DocumentoEFPSException.ERROR_DESCONOCIDO_EN_EL_SERVICIO);
        }

        return (lista.isEmpty() ? null : lista);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/documentos")
    public Response crearDocumentos(List<DocumentoEFPS> listaDocumentos) {

        try {
            if(!validacionDeSolicitudDocumentoEFPS.sonDocumentoEFPSValidosParaInsercionEnBD(listaDocumentos))
                return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                        new DocumentoEFPSException(DocumentoEFPSException.MENSAJE_DE_SOLICITUD_INCORRECTO),
                        null));

            List<McltDocumentoEFPS> lista = this.guardarDocumentos(listaDocumentos);

            return toResponse(new Message<>((Serializable)
                    assambleDocumentoEFPSFromMcltDocumentoEFPS.assembleList(lista)));
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR DocumentoEFPSEndpoint.crearDocumentos ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new DocumentoEFPSException(DocumentoEFPSException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }

    @GET
    @Path("/{idEntidadFinanciera}/{idTipoPrestadorDeServicios}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerListaDeDocumentos(@PathParam("idEntidadFinanciera") Long idEntidadFinanciera,
        @PathParam("idTipoPrestadorDeServicios") Integer idTipoPrestadorDeServicios) {

        try {
            List<McltDocumentoEFPS> mcltDocumentList =
                    service.obtenerDocumentosPorIdEntidadFinancieraYIdTipoPrestadorDeServicios(idEntidadFinanciera,
                            idTipoPrestadorDeServicios);

            if(mcltDocumentList != null && !mcltDocumentList.isEmpty())
                return toResponse(new Message<>((Serializable)
                        assambleDocumentoEFPSFromMcltDocumentoEFPS.assembleList(mcltDocumentList)));
            else
                return Response.noContent().build();

        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR DocumentoEFPSEndpoint.obtenerListaDeDocumentos ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new DocumentoEFPSException(DocumentoEFPSException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }
}
