package mx.gob.imss.dpes.bitacoraback.validation;

import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraEstatusEF;

import javax.ws.rs.ext.Provider;

@Provider
public class ValidacionDeSolicitudBitacoraEstatusEF {

    public boolean esBitacoraEstatusEFValidoParaInsercionEnBD(BitacoraEstatusEF source) {
        if (source == null) {
            return false;
        }
        if (source.getIdEntidadFinanciera() != null &&
                source.getIdEntidadFinanciera() > 0 &&
                source.getIdTipoEvento() != null &&
                source.getIdTipoEvento() > 0 &&
                source.getIdEstadoEFAnterior() != null &&
                source.getIdEstadoEFAnterior() > 0 &&
                source.getIdEstadoEFNuevo() != null &&
                source.getIdEstadoEFNuevo() > 0 &&
                source.getCurp() != null &&
                source.getCurp().trim().length() == 18) {
            return true;
        }
        return false;
    }
}
