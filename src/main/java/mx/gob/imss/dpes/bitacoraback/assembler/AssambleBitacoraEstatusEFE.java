package mx.gob.imss.dpes.bitacoraback.assembler;

import mx.gob.imss.dpes.bitacoraback.entity.MclcTipoEvento;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraEstatusEF;
import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraEstatusEF;

import javax.ws.rs.ext.Provider;

@Provider
public class AssambleBitacoraEstatusEFE extends BaseAssembler<BitacoraEstatusEF, McltBitacoraEstatusEF> {

    @Override
    public McltBitacoraEstatusEF assemble(BitacoraEstatusEF source) {
        if (source == null)
            return null;

        McltBitacoraEstatusEF mcltBitacoraEstatusEF = new McltBitacoraEstatusEF();

        if (source.getIdEntidadFinanciera() != null) {
            mcltBitacoraEstatusEF.setIdEntidadFinanciera(source.getIdEntidadFinanciera());
        }

        if (source.getIdTipoEvento() != null) {
            mcltBitacoraEstatusEF.setIdTipoEvento(source.getIdTipoEvento());
        }

        if (source.getIdEstadoEFAnterior() != null) {
            mcltBitacoraEstatusEF.setIdEstadoEFAnterior(source.getIdEstadoEFAnterior());
        }

        if (source.getIdEstadoEFNuevo() != null) {
            mcltBitacoraEstatusEF.setIdEstadoEFNuevo(source.getIdEstadoEFNuevo());
        }

        if (source.getCurp() != null) {
            mcltBitacoraEstatusEF.setCurp(source.getCurp());
        }

        return mcltBitacoraEstatusEF;
    }
}
