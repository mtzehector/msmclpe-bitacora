package mx.gob.imss.dpes.bitacoraback.assembler;

import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraCatEF;
import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraCatEF;

import javax.ws.rs.ext.Provider;

@Provider
public class AssambleBitacoraCatEFFromMcltBitacoraCatEF extends BaseAssembler<McltBitacoraCatEF, BitacoraCatEF> {
    @Override
    public BitacoraCatEF assemble(McltBitacoraCatEF source) {
        if(source == null)
            return null;

        BitacoraCatEF bitacoraCatEF = new BitacoraCatEF();
        bitacoraCatEF.setId(source.getId());
        bitacoraCatEF.setIdCondicionOferta(source.getMclcCondicionOferta().getId());
        bitacoraCatEF.setIdTipoEvento(source.getMclcTipoEvento().getId());
        bitacoraCatEF.setDescripcionTipoEvento(source.getMclcTipoEvento().getDesTipoEvento());
        bitacoraCatEF.setCurp(source.getCurp());
        bitacoraCatEF.setIdPlazoAnterior(source.getMclcPlazoAnterior().getId());
        bitacoraCatEF.setDescripcionPlazoAnterior(source.getMclcPlazoAnterior().getDesPlazo());
        bitacoraCatEF.setIdPlazoNuevo(source.getMclcPlazoNuevo().getId());
        bitacoraCatEF.setDescripcionPlazoNuevo(source.getMclcPlazoNuevo().getDesPlazo());
        bitacoraCatEF.setCatAnterior(source.getCatAnterior());
        bitacoraCatEF.setCatNuevo(source.getCatNuevo());
        bitacoraCatEF.setAltaRegistro(source.getAltaRegistro());

        return bitacoraCatEF;
    }
}
