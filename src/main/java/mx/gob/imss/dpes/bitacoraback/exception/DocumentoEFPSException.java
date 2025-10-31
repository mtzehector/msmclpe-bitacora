package mx.gob.imss.dpes.bitacoraback.exception;

import mx.gob.imss.dpes.common.exception.BusinessException;

public class DocumentoEFPSException extends BusinessException {

    public final static String ERROR_DESCONOCIDO_EN_EL_SERVICIO = "msg001";
    public final static String MENSAJE_DE_SOLICITUD_INCORRECTO = "msg002";
    public final static String ERROR_DE_ESCRITURA_EN_BD = "msg003";
    public final static String ERROR_DE_LECTURA_DE_BD = "msg004";

    public DocumentoEFPSException(String causa) {
        super(causa);
    }

}
