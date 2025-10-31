package mx.gob.imss.dpes.bitacoraback.entity;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MCLT_BITACORA_ESTATUS_EF")
public class McltBitacoraEstatusEF extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "MCLS_BITACORA_ESTATUS_EF")
    @GenericGenerator(
            name = "MCLS_BITACORA_ESTATUS_EF",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "MCLS_BITACORA_ESTATUS_EF"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "CVE_BITACORA_ESTATUS")
    @Getter
    @Setter
    private Long id;

    @Column(name = "CVE_ENTIDAD_FINANCIERA")
    @Getter
    @Setter
    private Long idEntidadFinanciera;

    @Column(name = "CVE_TIPO_EVENTO")
    @Getter
    @Setter
    private Long idTipoEvento;

    @Column(name = "CVE_ESTADO_ANTERIOR")
    @Getter
    @Setter
    private Integer idEstadoEFAnterior;

    @Column(name = "CVE_ESTADO_NUEVO")
    @Getter
    @Setter
    private Integer idEstadoEFNuevo;

    @Column(name = "CVE_CURP")
    @Getter
    @Setter
    private String curp;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof McltBitacoraEstatusEF)) {
            return false;
        }
        McltBitacoraEstatusEF other = (McltBitacoraEstatusEF) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
