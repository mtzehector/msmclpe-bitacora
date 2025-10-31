package mx.gob.imss.dpes.bitacoraback.endpoint;

import mx.gob.imss.dpes.bitacoraback.assembler.AssamblerCartaReciboFirmada;
import mx.gob.imss.dpes.bitacoraback.assembler.AssamblerDocumentoConciliacionFromBeanToEntity;
import mx.gob.imss.dpes.bitacoraback.assembler.AssamblerDocumentoConciliacionFromEntityToBean;
import mx.gob.imss.dpes.bitacoraback.entity.McltDocumentoConciliacion;
import mx.gob.imss.dpes.bitacoraback.exception.DocumentoConciliacionException;
import mx.gob.imss.dpes.bitacoraback.model.CartaReciboFirma;
import mx.gob.imss.dpes.bitacoraback.repository.PaginationUtil;
import mx.gob.imss.dpes.bitacoraback.service.CartaReciboFirmaService;
import mx.gob.imss.dpes.bitacoraback.service.DocumentoConciliacionActualizarService;
import mx.gob.imss.dpes.bitacoraback.service.DocumentoConciliacionService;
import mx.gob.imss.dpes.bitacoraback.validation.ValidacionCartaRecibo;
import mx.gob.imss.dpes.common.constants.Constantes;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.enums.TipoDocumentoEnum;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.PageRequestModel;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.interfaces.bitacora.model.CartaReciboFirmada;
import mx.gob.imss.dpes.interfaces.bitacora.model.DocumentoConciliacionRequest;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.ConciliacionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;


@ApplicationScoped
@Path("/documentoConciliacion")
public class DocumentoConciliacionEndPoint extends BaseGUIEndPoint<McltDocumentoConciliacion, McltDocumentoConciliacion, McltDocumentoConciliacion> {

    @Inject
    private DocumentoConciliacionService documentoService;
    @Inject
    private CartaReciboFirmaService cartaReciboFirmaService;
    @Inject
    private AssamblerDocumentoConciliacionFromEntityToBean assamblerEntityToBean;
    @Inject
    private AssamblerDocumentoConciliacionFromBeanToEntity assamblerBeanToEntity;
    @Inject
    private AssamblerCartaReciboFirmada assamblerCartaReciboFirmada;
    @Inject
    private ValidacionCartaRecibo validacionCartaRecibo;
    @Inject
    private DocumentoConciliacionActualizarService actualizarService;

    @GET
    @Path("/{idEntidadFinanciera}/{idTipoDocumento}/{periodo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDocumentoConciliacion(@PathParam("idEntidadFinanciera") Long cveEntidadFinanciera, @PathParam("idTipoDocumento") Long cveTipoDocumento, @PathParam("periodo") String periodo) throws BusinessException{
        try {
            McltDocumentoConciliacion documentoConciliacion = documentoService.obtenerDocumentoConciliacion(
                    cveEntidadFinanciera,
                    cveTipoDocumento,
                    periodo
            );
            if (documentoConciliacion == null)
                return Response.noContent().build();

            return Response.ok(assamblerEntityToBean.assemble(documentoConciliacion)).build();
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionEndPoint.obtenerDocumentoConciliacion() ", e);
        }
        return toResponse(new Message(
                null,
                ServiceStatusEnum.EXCEPCION,
                new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                null
        ));
    }
    
    @GET
    @Path("/ultimasCartas/{idEntidadFinanciera}/{periodo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDocumentoFinal(@PathParam("idEntidadFinanciera") Long idEntidadFinanciera, @PathParam("periodo") String periodo) throws BusinessException{
        try {
        	List<McltDocumentoConciliacion> documentosCartas = documentoService.obtenerUltimasCartas(
            		idEntidadFinanciera,
            		periodo
            );
        	 McltDocumentoConciliacion carta = documentoService.filtroCartas(documentosCartas);
        	 if (carta == null)
                 return Response.noContent().build();

             return Response.ok(assamblerEntityToBean.assemble(carta)).build();
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionEndPoint.obtenerDocumentoConciliacion() ", e);
        }
        return toResponse(new Message(
                null,
                ServiceStatusEnum.EXCEPCION,
                new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                null
        ));
    }

    @GET
    @Path("/cartaReciboFirmadaAdministradorEF/{idEntidadFinanciera}/{periodo}/{cveTipoDocumento}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCartaReciboAdministradorEF(@PathParam("idEntidadFinanciera") Long idEntidadFinanciera, 
    		@PathParam("periodo") String periodo, @PathParam("cveTipoDocumento") Long cveTipoDocumento) throws BusinessException{
        try {
        	McltDocumentoConciliacion cartaFirmada = documentoService.obtenerCartaReciboFirmadaAdministradorEF(
            		idEntidadFinanciera,
            		cveTipoDocumento,
            		periodo
            );
        	 if (cartaFirmada == null)
                 return Response.noContent().build();

             return Response.ok(assamblerEntityToBean.assemble(cartaFirmada)).build();
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionEndPoint.obtenerDocumentoConciliacion() ", e);
        }
        return toResponse(new Message(
                null,
                ServiceStatusEnum.EXCEPCION,
                new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                null
        ));
    }
    
    
    @POST
    @Path("/obtenerCartaReciboPorEntidadFinanciera")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCartaReciboPorEntidadFinanciera(PageRequestModel<ConciliacionRequest> request) throws BusinessException{
        try {
            List<McltDocumentoConciliacion> listCartaRecibo = documentoService.obtenerCartaRecibo(
                    request.getModel().getCveEntidadFinanciera(),
                    request.getModel().getCveTipoDocumento(),
                    request.getModel().getPeriodo()
            );
            if (listCartaRecibo == null || listCartaRecibo.isEmpty())
                return Response.noContent().build();

            Pageable pageable = new PageRequest((request.getPage() - 1), Constantes.DIEZ);
            Page<DocumentoConciliacionRequest> page = PaginationUtil.paginateList(
                    pageable,
                    assamblerEntityToBean.assembleList(listCartaRecibo)
            );
            return Response.ok(page).build();
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionEndPoint.obtenerDocumentoConciliacion() ", e);
        }
        return toResponse(new Message(
                null,
                ServiceStatusEnum.EXCEPCION,
                new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                null
        ));
    }

    @POST
    @Path("/obtenerListCartaReciboEFTodas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerListCartaReciboEFTodas(PageRequestModel<ConciliacionRequest> request) throws BusinessException {
        try {
            List<McltDocumentoConciliacion> listCartaRecibo = documentoService.obtenerListCartaReciboEFTodas(
                    request.getModel().getCvePerfil(),
                    request.getModel().getPeriodo()
            );
            
            if (listCartaRecibo == null || listCartaRecibo.isEmpty())
                return Response.noContent().build();
            
            if(request.getModel().getFiltroEF() != null && !request.getModel().getFiltroEF().isEmpty()) 
            	listCartaRecibo = documentoService.filtrarCartaFirmadaOEF(listCartaRecibo, request.getModel().getFiltroEF());
            
            if (listCartaRecibo == null || listCartaRecibo.isEmpty())
                return Response.noContent().build();
       
            Pageable pageable = new PageRequest((request.getPage() - 1), Constantes.DIEZ);
            Page<DocumentoConciliacionRequest> page = PaginationUtil.paginateList(
                    pageable,
                    assamblerEntityToBean.assembleList(listCartaRecibo)
            );
            return Response.ok(page).build();
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionEndPoint.obtenerListCartaReciboEFTodas() ", e);
        }
        return toResponse(new Message(
                null,
                ServiceStatusEnum.EXCEPCION,
                new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                null
        ));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarDocumentoConciliacion(DocumentoConciliacionRequest request) throws BusinessException {
        try {
            McltDocumentoConciliacion documentoConciliacion = documentoService.guardarDocumentoConciliacion(assamblerBeanToEntity.assemble(request));
            return Response.ok(assamblerEntityToBean.assemble(documentoConciliacion)).build();
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionEndPoint.guardarDocumentoConciliacion() ", e);
        }
        return toResponse(new Message(
                null,
                ServiceStatusEnum.EXCEPCION,
                new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                null
        ));
    }

    @POST
    @Path("/cartaRecibo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerListCartaReciboConFirma(PageRequestModel<ConciliacionRequest> request) throws BusinessException {
        try {
            if ( !(
                request != null && request.getModel() != null &&
                request.getModel().getPeriodo() != null && !request.getModel().getPeriodo().trim().isEmpty() &&
                request.getModel().getCveEntidadFinanciera() != null && request.getModel().getCveEntidadFinanciera() >= 0
            ))
                throw new DocumentoConciliacionException(DocumentoConciliacionException.MENSAJE_DE_SOLICITUD_INCORRECTO);

            List<CartaReciboFirma> listCartaReciboConFirma = cartaReciboFirmaService.obtenerListCartaReciboFirma(
                    request.getModel().getPeriodo(),
                    TipoDocumentoEnum.CARTA_RECIBO_TITULAR_DIVISION.getTipo(),
                    request.getModel().getCveEntidadFinanciera()
            );
            if (listCartaReciboConFirma == null || listCartaReciboConFirma.isEmpty())
                return Response.noContent().build();

            Pageable pageable = new PageRequest((request.getPage() - 1), Constantes.DIEZ);
            Page<CartaReciboFirmada> page = PaginationUtil.paginateList(
                    pageable,
                    assamblerCartaReciboFirmada.assembleList(listCartaReciboConFirma)
            );
            return Response.ok(page).build();
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionEndPoint.obtenerListCartaReciboConFirma() ", e);
        }
        return toResponse(new Message(
                null,
                ServiceStatusEnum.EXCEPCION,
                new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                null
        ));
    }

    @POST
    @Path("/cartaRecibo/fechaDescarga")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarFechaDescarga(DocumentoConciliacionRequest request) throws BusinessException {

        if (request.getId() == null)
            throw new DocumentoConciliacionException(DocumentoConciliacionException.MENSAJE_DE_SOLICITUD_INCORRECTO);

        try {
            McltDocumentoConciliacion documentoConciliacion = documentoService.guardarFechaDescarga(request.getId());
            return Response.ok(assamblerEntityToBean.assemble(documentoConciliacion)).build();
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionEndPoint.guardarFechaDescarga() ", e);
        }
        return toResponse(new Message(
                null,
                ServiceStatusEnum.EXCEPCION,
                new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                null
        ));
    }

    @POST
    @Path("/cartaRecibo/erogacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizaCampoErogacion(DocumentoConciliacionRequest request) throws BusinessException {

        if (!validacionCartaRecibo.validaCveEntidadPeriodo(request))
            throw new DocumentoConciliacionException(DocumentoConciliacionException.MENSAJE_DE_SOLICITUD_INCORRECTO);

        try {
            McltDocumentoConciliacion cartaRecibo = actualizarService.actualizarCampoErogacion(
                    request.getCveEntidadFinanciera(),
                    TipoDocumentoEnum.CARTA_RECIBO_TITULAR_DIVISION.getTipo(),
                    request.getPeriodo()
            );

            if (cartaRecibo == null)
                return Response.noContent().build();

            return Response.ok(cartaRecibo).build();
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionEndPoint.actualizaCampoErogacion() ", e);
        }
        return toResponse(new Message(
                null,
                ServiceStatusEnum.EXCEPCION,
                new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                null
        ));

    }

    @GET
    @Path("/{tipoDocumento}/{periodo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerResumenConciliacion(@PathParam("tipoDocumento") Long cveTipoDocumento, @PathParam("periodo") String periodo) throws BusinessException {
        try{
            McltDocumentoConciliacion documentoConciliacion = documentoService.obtenerDocumento(
                    cveTipoDocumento,
                    periodo
            );
            if (documentoConciliacion == null)
                return Response.noContent().build();

            return Response.ok(assamblerEntityToBean.assemble(documentoConciliacion)).build();
        }catch (BusinessException e){
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        }catch (Exception e){
            log.log(Level.SEVERE, "ERROR DocumentoConciliacionEndPoint.obtenerDocumento() ", e);
        }
        return toResponse(new Message(
                null,
                ServiceStatusEnum.EXCEPCION,
                new DocumentoConciliacionException(DocumentoConciliacionException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                null
        ));
    }

}
