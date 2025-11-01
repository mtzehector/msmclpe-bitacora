/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.entity;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author juanf.barragan
 */

@Entity
@Table(name = "MCLT_BITACORA_CAT_IMSS")
public class McltBitacoraCatImss extends LogicDeletedEntity<Long> {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "MCLS_BITACORA_CAT_IMSS")
    @GenericGenerator(
            name = "MCLS_BITACORA_CAT_IMSS",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_BITACORA_CAT_IMSS"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "CVE_BITACORA_CAT")
    @Getter
    @Setter
    private Long id;
    
    @Column(name = "CVE_CURP")
    @Getter
    @Setter
    private String curp;
    
    @Column(name = "CAT_MAXIMO")
    @Getter
    @Setter
    private BigDecimal cat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="CVE_TIPO_EVENTO")
    @Getter
    @Setter
    private MclcTipoEvento mclcTipoEvento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="CVE_DOCUMENTO")
    @Getter
    @Setter
    private McltDocumento mcltDocumento;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof McltBitacoraCatImss)) {
            return false;
        }
        McltBitacoraCatImss other = (McltBitacoraCatImss) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    } 
}
