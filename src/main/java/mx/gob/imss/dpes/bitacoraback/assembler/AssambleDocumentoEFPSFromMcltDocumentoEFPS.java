package mx.gob.imss.dpes.bitacoraback.assembler;

import mx.gob.imss.dpes.bitacoraback.entity.McltDocumentoEFPS;
import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.common.enums.TipoDocumentoEnum;
import mx.gob.imss.dpes.interfaces.bitacora.model.DocumentoEFPS;
import mx.gob.imss.dpes.interfaces.bitacora.model.DocumentoEFPSDocumento;
import mx.gob.imss.dpes.interfaces.documento.model.TipoDocumentoFront;

import javax.ws.rs.ext.Provider;

@Provider
public class AssambleDocumentoEFPSFromMcltDocumentoEFPS extends BaseAssembler<McltDocumentoEFPS, DocumentoEFPS> {
    @Override
    public DocumentoEFPS assemble(McltDocumentoEFPS source) {
        if(source == null)
            return null;

        DocumentoEFPS documentoEFPS = new DocumentoEFPS();

        documentoEFPS.setId(source.getId());
        documentoEFPS.setIdPrestadorServicios(source.getMcltPrestadorServiciosEF().getId());
        documentoEFPS.setIdDocumento(source.getMcltDocumento().getId());
        documentoEFPS.setCurp(source.getCurp());
        DocumentoEFPSDocumento documento = new DocumentoEFPSDocumento();
        documento.setId(source.getMcltDocumento().getId());
        documento.setTipoDocumentoEnum(new TipoDocumentoFront(
                TipoDocumentoEnum.forValue(source.getMcltDocumento().getTipoDocumento())));
        documento.setAltaRegistro(source.getMcltDocumento().getAltaRegistro());
        documentoEFPS.setDocumento(documento);

        return documentoEFPS;
    }
}
