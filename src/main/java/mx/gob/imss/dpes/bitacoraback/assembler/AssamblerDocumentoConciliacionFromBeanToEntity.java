package mx.gob.imss.dpes.bitacoraback.assembler;

import mx.gob.imss.dpes.bitacoraback.entity.MclcEntidadFinanciera;
import mx.gob.imss.dpes.bitacoraback.entity.McltDocumento;
import mx.gob.imss.dpes.bitacoraback.entity.McltDocumentoConciliacion;
import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.interfaces.bitacora.model.DocumentoConciliacionRequest;

import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class AssamblerDocumentoConciliacionFromBeanToEntity extends BaseAssembler<DocumentoConciliacionRequest, McltDocumentoConciliacion> {
    @Override
    public McltDocumentoConciliacion assemble(DocumentoConciliacionRequest source) {
        if (source == null)
            return null;
        McltDocumentoConciliacion documentoConciliacion = new McltDocumentoConciliacion();
        if (source.getId() != null)
            documentoConciliacion.setId(source.getId());
        McltDocumento documento = new McltDocumento();
        documento.setId(source.getCveDocumento());
        documentoConciliacion.setDocumento(documento);
        MclcEntidadFinanciera ef = new MclcEntidadFinanciera();
        ef.setId(source.getCveEntidadFinanciera());
        documentoConciliacion.setEntidadFinanciera(ef);
        documentoConciliacion.setCurp(source.getCurp());
        documentoConciliacion.setPeriodo(source.getPeriodo());
        documentoConciliacion.setFechaDescarga(source.getFechaDescarga());
        documentoConciliacion.setErogacion(source.getErogacion());
        documentoConciliacion.setAltaRegistro(new Date());
        return documentoConciliacion;
    }
}
