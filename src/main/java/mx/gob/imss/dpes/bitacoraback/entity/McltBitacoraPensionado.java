package mx.gob.imss.dpes.bitacoraback.entity;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraPensionadoRequest;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "MCLT_BITACORA_PENSIONADO")
public class McltBitacoraPensionado extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "MCLS_BITACORA_PENSIONADO")
    @GenericGenerator(
            name = "MCLS_BITACORA_PENSIONADO",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "MCLS_BITACORA_PENSIONADO"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "CVE_BITACORA_PENSIONADO")
    @Getter
    @Setter
    private Long id;

    @Column(name = "CVE_CURP")
    @NotNull
    @Getter
    @Setter
    private String cveCurp;

    @Column(name = "CVE_PERSONA")
    @NotNull
    @Getter
    @Setter
    private Long cvePersona;

    @Column(name = "VALOR_ANTERIOR")
    @NotNull
    @Getter
    @Setter
    private String valorAnterior;

    @Column(name = "VALOR_ACTUAL")
    @NotNull
    @Getter
    @Setter
    private String valorActual;

    @OneToOne
    @JoinColumn(name = "CVE_TIPO_MODIFICACION")
    @Getter
    @Setter
    private MclcTipoModificacion mclcTipoModificacion;

    @Override
    public Long getId() {
        return this.id;
    }

    public McltBitacoraPensionado() {
    }

    public McltBitacoraPensionado(BitacoraPensionadoRequest bitacoraPensionadoRequest) {
        this.id = bitacoraPensionadoRequest.getId();
        this.cveCurp = bitacoraPensionadoRequest.getCveCurp();
        this.cvePersona = bitacoraPensionadoRequest.getCvePersona();
        this.valorAnterior = bitacoraPensionadoRequest.getValorAnterior();
        this.valorActual = bitacoraPensionadoRequest.getValorActual();
        MclcTipoModificacion mclcTipoModificacion1 = new MclcTipoModificacion();
        mclcTipoModificacion1.setCveTipoModificacion(bitacoraPensionadoRequest.getTipoModificacion().getCveTipoModificacion());
        this.mclcTipoModificacion = mclcTipoModificacion1;
        this.setAltaRegistro(new Date());
    }

    @Override
    public String toString() {
        return "McltBitacoraPensionado{" +
                "id=" + id +
                ", cveCurp='" + cveCurp + '\'' +
                ", cvePersona=" + cvePersona +
                ", valorAnterior='" + valorAnterior + '\'' +
                ", valorActual='" + valorActual + '\'' +
                ", mclcTipoModificacion=" + mclcTipoModificacion +
                ", fecRegistro= " + this.getAltaRegistro() +
                '}';
    }
}
