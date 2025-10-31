package mx.gob.imss.dpes.bitacoraback.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class CartaReciboFirma extends BaseModel {

    @Getter
    @Setter
    private BigDecimal CVEENTIDADFINANCIERA;

    @Getter
    @Setter
    private String NOMBRECOMERCIAL;

    @Getter
    @Setter
    private BigDecimal ESTADOENTIDADFINANCIERA;

    @Getter
    @Setter
    private BigDecimal CVEDOCUMENTOCONCILIACION;

    @Getter
    @Setter
    private BigDecimal CVEDOCUMENTO;

    @Getter
    @Setter
    private BigDecimal TIPODOCUMENTO;

    @Getter
    @Setter
    private String REFBOVEDA;

    @Getter
    @Setter
    private String PERIODO;

    @Getter
    @Setter
    private Date FECHA_DESCARGA;

    @Getter
    @Setter
    private Date FECHA_FIRMA;

    @Getter
    @Setter
    private BigDecimal EROGACION;


}
