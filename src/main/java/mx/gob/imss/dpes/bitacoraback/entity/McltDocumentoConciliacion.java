package mx.gob.imss.dpes.bitacoraback.entity;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "MCLT_DOCUMENTO_CONCILIACION")
public class McltDocumentoConciliacion extends LogicDeletedEntity<Long>  {

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_DOC_CONCILIACION")
    @GeneratedValue(generator = "SEQ_GEN_MCLS_DOCUMENTO_CONCILIACION")
    @GenericGenerator(
            name = "SEQ_GEN_MCLS_DOCUMENTO_CONCILIACION",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "MCLS_DOCUMENTO_CONCILIACION"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Getter
    @Setter
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CVE_DOCUMENTO")
    @Getter
    @Setter
    private McltDocumento Documento;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CVE_ENTIDAD_FINANCIERA")
    @Getter
    @Setter
    private MclcEntidadFinanciera entidadFinanciera;

    @Column(name = "CVE_CURP")
    @Getter
    @Setter
    private String curp;

    @Column(name = "PERIODO")
    @Getter
    @Setter
    private String periodo;

    @Column(name = "FECHA_DESCARGA_IMSS")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date fechaDescarga;

    @Column(name = "GENERO_EROGACION")
    @Getter
    @Setter
    private Boolean erogacion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        McltDocumentoConciliacion other = (McltDocumentoConciliacion) o;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
}
