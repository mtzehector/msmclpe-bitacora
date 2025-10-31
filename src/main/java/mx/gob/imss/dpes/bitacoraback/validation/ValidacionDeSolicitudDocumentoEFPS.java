package mx.gob.imss.dpes.bitacoraback.validation;

import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraCatImss;
import mx.gob.imss.dpes.interfaces.bitacora.model.DocumentoEFPS;

import javax.ws.rs.ext.Provider;
import java.math.BigDecimal;
import java.util.List;

@Provider
public class ValidacionDeSolicitudDocumentoEFPS {
    public boolean esDocumentoEFPSValidoParaInsercionEnBD(DocumentoEFPS source) {
        if(source == null)
            return false;

        if(
                source.getIdPrestadorServicios() != null
                && source.getIdPrestadorServicios() > 0
                && source.getIdDocumento() != null
                && source.getIdDocumento() > 0
                && source.getCurp() != null
                && source.getCurp().trim().length() == 18
        )
            return true;

        return false;
    }

    public boolean sonDocumentoEFPSValidosParaInsercionEnBD(List<DocumentoEFPS> lista) {
        if(lista == null || lista.isEmpty())
            return false;

        for(DocumentoEFPS documento : lista) {
            if(!this.esDocumentoEFPSValidoParaInsercionEnBD(documento))
                return false;
        }

        return true;
    }
}
