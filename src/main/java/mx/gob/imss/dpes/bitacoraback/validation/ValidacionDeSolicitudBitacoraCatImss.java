package mx.gob.imss.dpes.bitacoraback.validation;

import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraCatImss;

import javax.ws.rs.ext.Provider;
import java.math.BigDecimal;

@Provider
public class ValidacionDeSolicitudBitacoraCatImss {
    public boolean esBitacoraCatImssValidoParaInsercionEnBD(BitacoraCatImss source) {
        if(source == null) {
            return false;
        }

        if(
                source.getCveDocumento() != null
                && source.getCveDocumento() > 0
                && source.getIdTipoEvento() != null
                && source.getIdTipoEvento() > 0
                && source.getCurp() != null
                && source.getCurp().trim().length() == 18
                && source.getCat() != null
                && source.getCat().compareTo(BigDecimal.ZERO) >= 0
        )
            return true;

        return false;
    }
}
