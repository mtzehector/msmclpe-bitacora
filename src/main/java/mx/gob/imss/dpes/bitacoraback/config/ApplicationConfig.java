/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author salvador.pocteco
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(mx.gob.imss.dpes.bitacoraback.endpoint.BitacoraCatEFEndpoint.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.endpoint.BitacoraCatImssEndpoint.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.endpoint.BitacoraEndPoint.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.endpoint.BitacoraEstatusEFEndpoint.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.endpoint.BitacoraPensionadoEndpoint.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.endpoint.CronTareaEndPoint.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.endpoint.DocumentoConciliacionEndPoint.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.endpoint.DocumentoEFPSEndpoint.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.endpoint.HistoricoUsuarioEndPoint.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.BitacoraCatEFService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.BitacoraCatImssService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.BitacoraEstatusEFService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.BitacoraFindBySolicitudService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.BitacoraInterfazPersistenceService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.BitacoraNotificacionService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.BitacoraPensionadoService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.BitacoraPersistenceService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.BitacoraPersonaService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.BitacoraReporteService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.CartaReciboFirmaService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.CronTareaService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.DocumentoConciliacionActualizarService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.DocumentoConciliacionService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.DocumentoEFPSService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.HistoricoUsuarioService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.TareaAccionService.class);
        resources.add(mx.gob.imss.dpes.bitacoraback.service.TareaEstadoOrigenService.class);
        resources.add(mx.gob.imss.dpes.common.exception.AlternateFlowMapper.class);
        resources.add(mx.gob.imss.dpes.common.exception.BusinessMapper.class);
        resources.add(mx.gob.imss.dpes.common.rule.MontoTotalRule.class);
        resources.add(mx.gob.imss.dpes.common.rule.PagoMensualRule.class);
    }

}
