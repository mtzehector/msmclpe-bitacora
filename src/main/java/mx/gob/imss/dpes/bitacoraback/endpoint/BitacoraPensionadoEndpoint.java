package mx.gob.imss.dpes.bitacoraback.endpoint;

import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraPensionado;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraPensionadoException;
import mx.gob.imss.dpes.bitacoraback.repository.PaginationUtil;
import mx.gob.imss.dpes.bitacoraback.service.BitacoraPensionadoService;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.PageRequestModel;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraPensionadoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;

@ApplicationScoped
@Path("/bitacoraPensionado")
public class BitacoraPensionadoEndpoint extends BaseGUIEndPoint<McltBitacoraPensionado,McltBitacoraPensionado,McltBitacoraPensionado> {

    @Inject
    private BitacoraPensionadoService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response create(McltBitacoraPensionado request) {
        try {
            Message<McltBitacoraPensionado> response = service.execute(new Message<>(request));
            return toResponse(response);
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR::BitacoraPensionadoEndPoint.create {0}", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new BitacoraPensionadoException(BitacoraPensionadoException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }

    @POST
    @Path("/registrosActualizados")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarRegistrosActualizados(List<BitacoraPensionadoRequest> mcltBitacoraPensionados) {
        try {
            List<Object> response = service.saveAll(mcltBitacoraPensionados);
            return Response.ok(response).build();
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR::BitacoraPensionadoEndPoint.guardarRegistrosActualizados {0}", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new BitacoraPensionadoException(BitacoraPensionadoException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }

    }

    @POST
    @Path("/buscarPorCvePersona")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCvePersona(PageRequestModel<BitacoraPensionadoRequest> request) {
        try {
            List<BitacoraPensionadoRequest> mcltBitacoraPensionados = service.findByCvePerson(request);
            Pageable pageable = new PageRequest((request.getPage() - 1), request.getPageSize());
            Page<BitacoraPensionadoRequest> page = PaginationUtil.paginateList(pageable, mcltBitacoraPensionados);
            return Response.ok(page).build();
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR::BitacoraPensionadoEndPoint.findByCvePersona {0}", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                    new BitacoraPensionadoException(BitacoraPensionadoException.ERROR_DESCONOCIDO_EN_EL_SERVICIO),
                    null));
        }
    }

}
