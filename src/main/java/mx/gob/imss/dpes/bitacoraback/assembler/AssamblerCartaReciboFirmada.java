package mx.gob.imss.dpes.bitacoraback.assembler;

import mx.gob.imss.dpes.bitacoraback.model.CartaReciboFirma;
import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.common.enums.TipoDocumentoEnum;
import mx.gob.imss.dpes.interfaces.bitacora.model.CartaReciboFirmada;
import mx.gob.imss.dpes.interfaces.documento.model.Documento;
import mx.gob.imss.dpes.interfaces.documento.model.TipoDocumentoFront;

import javax.ws.rs.ext.Provider;

@Provider
public class AssamblerCartaReciboFirmada extends BaseAssembler<CartaReciboFirma, CartaReciboFirmada> {

    @Override
    public CartaReciboFirmada assemble(CartaReciboFirma source) {
        if (source == null)
            return null;

        CartaReciboFirmada cartaRecibo = new CartaReciboFirmada();
        cartaRecibo.setCveEntidadFinanciera(source.getCVEENTIDADFINANCIERA() == null? 0 : source.getCVEENTIDADFINANCIERA().longValue());
        cartaRecibo.setNombreComercial(source.getNOMBRECOMERCIAL() == null || source.getNOMBRECOMERCIAL().isEmpty()? "" : source.getNOMBRECOMERCIAL());
        cartaRecibo.setEstadoEntidadFinanciera(source.getESTADOENTIDADFINANCIERA() == null? 0 : source.getESTADOENTIDADFINANCIERA().intValue());
        cartaRecibo.setCveDocumentoConciliacion(source.getCVEDOCUMENTOCONCILIACION() == null? 0 : source.getCVEDOCUMENTOCONCILIACION().longValue());
        if (source.getCVEDOCUMENTO() != null) {
            Documento documento = new Documento();
            documento.setId(source.getCVEDOCUMENTO().longValue());
            documento.setTipoDocumento(TipoDocumentoEnum.forValue(source.getTIPODOCUMENTO().longValue()));
            TipoDocumentoFront tipoDocumento = new TipoDocumentoFront();
            tipoDocumento.setId(documento.getTipoDocumento().getTipo());
            tipoDocumento.setDescripcion(documento.getTipoDocumento().getDescripcion());
            documento.setTipoDocumentoEnum(tipoDocumento);
            documento.setRefDocBoveda(source.getREFBOVEDA());
            documento.setCveEntidadFinanciera(source.getCVEENTIDADFINANCIERA() == null? 0 : source.getCVEENTIDADFINANCIERA().longValue());
            cartaRecibo.setDocumento(documento);
        }
        cartaRecibo.setPeriodo(source.getPERIODO() == null || source.getPERIODO().isEmpty()? "" : source.getPERIODO());
        cartaRecibo.setFechaDescarga(source.getFECHA_DESCARGA());
        cartaRecibo.setFechaFirma(source.getFECHA_FIRMA());
        Integer erogacion = source.getEROGACION() == null? 0 : source.getEROGACION().intValue();
        cartaRecibo.setErogacion(erogacion.equals(0)? false : true);
        return cartaRecibo;
    }
}
