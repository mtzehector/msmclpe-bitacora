package mx.gob.imss.dpes.bitacoraback.assembler;

import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraCatImss;
import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.common.enums.TipoDocumentoEnum;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraCatImss;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraCatImssDocumento;
import mx.gob.imss.dpes.interfaces.documento.model.TipoDocumentoFront;

import javax.ws.rs.ext.Provider;

@Provider
public class AssambleBitacoraCatImssFromMcltBitacoraCatImss extends BaseAssembler<McltBitacoraCatImss, BitacoraCatImss> {
    @Override
    public BitacoraCatImss assemble(McltBitacoraCatImss source) {
        if(source == null)
            return null;

        BitacoraCatImss bitacoraCatImss = new BitacoraCatImss();

        bitacoraCatImss.setId(source.getId());
        bitacoraCatImss.setCat(source.getCat());
        bitacoraCatImss.setIdTipoEvento(source.getMclcTipoEvento().getId());
        bitacoraCatImss.setDesTipoEvento(source.getMclcTipoEvento().getDesTipoEvento());
        bitacoraCatImss.setCurp(source.getCurp());
        bitacoraCatImss.setCveDocumento(source.getMcltDocumento().getId());
        BitacoraCatImssDocumento documento = new BitacoraCatImssDocumento();
        documento.setId(source.getMcltDocumento().getId());
        documento.setTipoDocumentoEnum(new TipoDocumentoFront(
                TipoDocumentoEnum.forValue(source.getMcltDocumento().getTipoDocumento())));
        documento.setAltaRegistro(source.getMcltDocumento().getAltaRegistro());
        bitacoraCatImss.setDocumento(documento);

        return bitacoraCatImss;
    }
}
