package mx.gob.imss.dpes.bitacoraback.assembler;

import mx.gob.imss.dpes.bitacoraback.entity.McltPrestadorServiciosEF;
import mx.gob.imss.dpes.bitacoraback.entity.McltDocumentoEFPS;
import mx.gob.imss.dpes.bitacoraback.entity.McltDocumento;
import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.interfaces.bitacora.model.DocumentoEFPS;

import javax.ws.rs.ext.Provider;

@Provider
public class AssambleMcltDocumentoEFPSFromDocumentoEFPS extends BaseAssembler<DocumentoEFPS, McltDocumentoEFPS> {

    @Override
    public McltDocumentoEFPS assemble(DocumentoEFPS source) {

        if(source == null)
            return null;

        McltDocumentoEFPS mcltDocumentoEFPS = new McltDocumentoEFPS();

        if(source.getIdPrestadorServicios() != null) {
            McltPrestadorServiciosEF mcltPrestadorServiciosEF = new McltPrestadorServiciosEF();
            mcltPrestadorServiciosEF.setId(source.getIdPrestadorServicios());
            mcltDocumentoEFPS.setMcltPrestadorServiciosEF(mcltPrestadorServiciosEF);
        }

        if(source.getIdDocumento() != null) {
            McltDocumento mcltDocumento = new McltDocumento();
            mcltDocumento.setId(source.getIdDocumento());
            mcltDocumentoEFPS.setMcltDocumento(mcltDocumento);
        }

        if(source.getCurp() != null) {
            mcltDocumentoEFPS.setCurp(source.getCurp());
        }

        return mcltDocumentoEFPS;
    }
}
