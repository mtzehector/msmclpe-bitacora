package mx.gob.imss.dpes.bitacoraback.entity;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 *
 * @author juanf.barragan
 */

@Entity
@Table(name = "MCLT_BITACORA_CAT_EF")
public class McltBitacoraCatEF extends LogicDeletedEntity<Long> {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "MCLS_BITACORA_CAT_EF")
    @GenericGenerator(
            name = "MCLS_BITACORA_CAT_EF",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_BITACORA_CAT_EF"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "CVE_BITACORA_CAT")
    @Getter
    @Setter
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="CVE_CONDICION_OFERTA")
    @Getter
    @Setter
    private MclcCondicionOferta mclcCondicionOferta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="CVE_TIPO_EVENTO")
    @Getter
    @Setter
    private MclcTipoEvento mclcTipoEvento;
    
    @Column(name = "CVE_CURP")
    @Getter
    @Setter
    private String curp;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="CVE_PLAZO_ANTERIOR")
    @Getter
    @Setter
    private MclcPlazo mclcPlazoAnterior;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="CVE_PLAZO_NUEVO")
    @Getter
    @Setter
    private MclcPlazo mclcPlazoNuevo;

    @Column(name = "CAT_ANTERIOR")
    @Getter
    @Setter
    private BigDecimal catAnterior;

    @Column(name = "CAT_NUEVO")
    @Getter
    @Setter
    private BigDecimal catNuevo;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof McltBitacoraCatEF)) {
            return false;
        }
        McltBitacoraCatEF other = (McltBitacoraCatEF) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    } 
}
