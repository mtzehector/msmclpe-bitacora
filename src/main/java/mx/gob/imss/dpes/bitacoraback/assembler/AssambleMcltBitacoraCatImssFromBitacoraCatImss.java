package mx.gob.imss.dpes.bitacoraback.assembler;

import mx.gob.imss.dpes.bitacoraback.entity.MclcTipoEvento;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraCatImss;
import mx.gob.imss.dpes.bitacoraback.entity.McltDocumento;
import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraCatImss;

import javax.ws.rs.ext.Provider;

@Provider
public class AssambleMcltBitacoraCatImssFromBitacoraCatImss extends BaseAssembler<BitacoraCatImss, McltBitacoraCatImss> {

    @Override
    public McltBitacoraCatImss assemble(BitacoraCatImss source) {

        if(source == null)
            return null;

        McltBitacoraCatImss mcltBitacoraCatImss = new McltBitacoraCatImss();

        if(source.getCveDocumento() != null) {
            McltDocumento mcltDocumento = new McltDocumento();
            mcltDocumento.setId(source.getCveDocumento());
            mcltBitacoraCatImss.setMcltDocumento(mcltDocumento);
        }

        if(source.getIdTipoEvento() != null) {
            MclcTipoEvento mclcTipoEvento = new MclcTipoEvento();
            mclcTipoEvento.setId(source.getIdTipoEvento());
            mcltBitacoraCatImss.setMclcTipoEvento(mclcTipoEvento);
        }

        if(source.getCurp() != null) {
            mcltBitacoraCatImss.setCurp(source.getCurp());
        }

        if(source.getCat() != null) {
            mcltBitacoraCatImss.setCat(source.getCat());
        }

        return mcltBitacoraCatImss;
    }
}
