package mx.gob.imss.dpes.bitacoraback.assembler;

import mx.gob.imss.dpes.bitacoraback.entity.MclcCondicionOferta;
import mx.gob.imss.dpes.bitacoraback.entity.MclcPlazo;
import mx.gob.imss.dpes.bitacoraback.entity.MclcTipoEvento;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraCatEF;
import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraCatEF;

import javax.ws.rs.ext.Provider;

@Provider
public class AssambleMcltBitacoraCatEFFromBitacoraCatEF extends BaseAssembler<BitacoraCatEF, McltBitacoraCatEF> {

    @Override
    public McltBitacoraCatEF assemble(BitacoraCatEF source) {

        if(source == null)
            return null;

        McltBitacoraCatEF mcltBitacoraCatEF = new McltBitacoraCatEF();

        if(source.getIdCondicionOferta() != null) {
            MclcCondicionOferta mclcCondicionOferta = new MclcCondicionOferta();
            mclcCondicionOferta.setId(source.getIdCondicionOferta());
            mcltBitacoraCatEF.setMclcCondicionOferta(mclcCondicionOferta);
        }

        if(source.getIdTipoEvento() != null) {
            MclcTipoEvento mclcTipoEvento = new MclcTipoEvento();
            mclcTipoEvento.setId(source.getIdTipoEvento());
            mcltBitacoraCatEF.setMclcTipoEvento(mclcTipoEvento);
        }

        if(source.getCurp() != null) {
            mcltBitacoraCatEF.setCurp(source.getCurp());
        }

        if(source.getIdPlazoAnterior() != null) {
            MclcPlazo mclcPlazo = new MclcPlazo();
            mclcPlazo.setId(source.getIdPlazoAnterior());
            mcltBitacoraCatEF.setMclcPlazoAnterior(mclcPlazo);
        }

        if(source.getIdPlazoNuevo() != null) {
            MclcPlazo mclcPlazo = new MclcPlazo();
            mclcPlazo.setId(source.getIdPlazoNuevo());
            mcltBitacoraCatEF.setMclcPlazoNuevo(mclcPlazo);
        }

        if(source.getCatAnterior() != null) {
            mcltBitacoraCatEF.setCatAnterior(source.getCatAnterior());
        }

        if(source.getCatNuevo() != null) {
            mcltBitacoraCatEF.setCatNuevo(source.getCatNuevo());
        }

        return mcltBitacoraCatEF;
    }
}
