package mx.gob.imss.dpes.bitacoraback.assembler;

import mx.gob.imss.dpes.bitacoraback.entity.McltDocumentoConciliacion;
import mx.gob.imss.dpes.common.assembler.BaseAssembler;
import mx.gob.imss.dpes.common.enums.TipoDocumentoEnum;
import mx.gob.imss.dpes.interfaces.bitacora.model.DocumentoConciliacionRequest;
import mx.gob.imss.dpes.interfaces.documento.model.Documento;
import mx.gob.imss.dpes.interfaces.documento.model.TipoDocumentoFront;
import mx.gob.imss.dpes.interfaces.entidadfinanciera.model.EntidadFinanciera;

import javax.ws.rs.ext.Provider;

@Provider
public class AssamblerDocumentoConciliacionFromEntityToBean extends BaseAssembler<McltDocumentoConciliacion, DocumentoConciliacionRequest> {
    @Override
    public DocumentoConciliacionRequest assemble(McltDocumentoConciliacion source) {
        if (source == null)
            return null;

        DocumentoConciliacionRequest documentoConciliacion = new DocumentoConciliacionRequest();
        if (source.getId() != null)
            documentoConciliacion.setId(source.getId());
        documentoConciliacion.setCveDocumento(source.getDocumento().getId());
        if (source.getEntidadFinanciera() != null){
            EntidadFinanciera ef = new EntidadFinanciera();
            ef.setId(source.getEntidadFinanciera().getId());
            ef.setNombreComercial(source.getEntidadFinanciera().getNombreComercial());
            ef.setRazonSocial(source.getEntidadFinanciera().getRazonSocial());
            ef.setCatPromedio(source.getEntidadFinanciera().getCatPromedio() == null ? 0.0 : source.getEntidadFinanciera().getCatPromedio().doubleValue());
            ef.setCveEntidadFinancieraSipre(source.getEntidadFinanciera().getCveEntidadFinancieraSipre());
            ef.setNumProveedor(source.getEntidadFinanciera().getNumProveedor());
            ef.setNumTelefono(source.getEntidadFinanciera().getNumeroTelefono());
            ef.setTelefonoAtencionClientes(source.getEntidadFinanciera().getTelefonoAtencionClientes());
            ef.setPaginaWeb(source.getEntidadFinanciera().getPaginaWeb());
            ef.setCorreoElectronico(source.getEntidadFinanciera().getCorreoElectronico());
            ef.setCorreoAdminEF(source.getEntidadFinanciera().getCorreoAdminEF());
            documentoConciliacion.setEntidadFinanciera(ef);
        }
        documentoConciliacion.setCurp(source.getCurp());
        documentoConciliacion.setPeriodo(source.getPeriodo());
        documentoConciliacion.setFechaDescarga(source.getFechaDescarga());
        documentoConciliacion.setFechaAlta(source.getAltaRegistro());
        documentoConciliacion.setErogacion(source.getErogacion());
        if (source.getDocumento() != null){
            Documento documento = new Documento();
            documento.setId(source.getDocumento().getId());
            documento.setCveSolicitud(source.getDocumento().getCveSolicitud());
            documento.setTipoDocumento(source.getDocumento().getTipoDocumento() == null ? null: TipoDocumentoEnum.forValue(source.getDocumento().getTipoDocumento()));
            documento.setTipoDocumentoEnum(documento.getTipoDocumento() == null ? null : new TipoDocumentoFront(documento.getTipoDocumento()));
            documento.setCvePersona(source.getDocumento().getCvePersona());
            documento.setRefSello(source.getDocumento().getRefSello());
            documento.setRefDocumento(source.getDocumento().getRefDocumento());
            documento.setRefDocBoveda(source.getDocumento().getRefDocBoveda());
            documento.setCveEntidadFinanciera(source.getDocumento().getCveEntidadFinanciera());
            documento.setCvePrestamoRecuperacion(source.getDocumento().getCvePrestamoRecuperacion());
            documento.setRefDocumentoClob(source.getDocumento().getRefDocumentoClob());
            documento.setIndDocumentoHistorico(source.getDocumento().isIndDocumentoHistorico());
            documento.setAltaRegistro(source.getAltaRegistro());
            documento.setActualizacionRegistro(source.getActualizacionRegistro());
            documento.setBajaRegistro(source.getBajaRegistro());
            documentoConciliacion.setDocumento(documento);
        }
        return documentoConciliacion;
    }
}
