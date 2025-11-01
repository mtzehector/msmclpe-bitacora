package mx.gob.imss.dpes.bitacoraback.validation;

import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraCatEF;

import javax.ws.rs.ext.Provider;
import java.math.BigDecimal;
import java.util.List;

@Provider
public class ValidacionDeSolicitudBitacoraCatEF {
    public boolean esBitacoraCatEFValidoParaInsercionEnBD(BitacoraCatEF source) {
        if(source == null) {
            return false;
        }

        if(
                source.getIdCondicionOferta() != null
                && source.getIdCondicionOferta() > 0
                && source.getIdTipoEvento() != null
                && source.getIdTipoEvento() > 0
                && source.getCurp() != null
                && source.getCurp().trim().length() == 18
                && source.getIdPlazoAnterior() != null
                && source.getIdPlazoAnterior() > 0
                && source.getIdPlazoNuevo() != null
                && source.getIdPlazoNuevo() > 0
                && source.getCatAnterior() != null
                && source.getCatAnterior().compareTo(BigDecimal.ZERO) >= 0
                && source.getCatNuevo() != null
                && source.getCatNuevo().compareTo(BigDecimal.ZERO) >= 0
        )
            return true;

        return false;
    }

    public boolean esListaBitacoraCatEFValidoParaInsercionEnBD(List<BitacoraCatEF> source) {
        if(source != null && source.size() > 0) {
            for (BitacoraCatEF elemento : source) {
                if(!esBitacoraCatEFValidoParaInsercionEnBD(elemento))
                    return false;
            }
            return true;
        }
        return false;
    }
}
