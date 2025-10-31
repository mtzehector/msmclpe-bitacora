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
import mx.gob.imss.dpes.bitacoraback.entity.McltHistoricoUsuario;
import mx.gob.imss.dpes.bitacoraback.service.HistoricoUsuarioService;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;

/**
 *
 * @author juanf.barragan
 */
@ApplicationScoped
@Path("/historicoUsuario")
public class HistoricoUsuarioEndPoint extends BaseGUIEndPoint<McltHistoricoUsuario,McltHistoricoUsuario,McltHistoricoUsuario>{
    
    @Inject
    private HistoricoUsuarioService service;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response create(McltHistoricoUsuario request)  throws BusinessException{
        Message<McltHistoricoUsuario> response = service.execute(new Message<>(request));

        return toResponse(response);

    }
    
}
