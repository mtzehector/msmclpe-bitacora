/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "MCLT_BITACORA_PERSONA")
public class McltBitacoraPersona extends LogicDeletedEntity<Long> {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "MCLS_BITACORA_PERSONA")
    @GenericGenerator(
            name = "MCLS_BITACORA_PERSONA",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_BITACORA_PERSONA"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "CVE_BITACORA_PERSONA")
    @Getter
    @Setter
    private Long id;
    
    @Column(name = "CVE_PERSONAL_EF")
    @NotNull
    @Getter
    @Setter
    private Long cvePersonalEF;
    
    @Column(name = "CVE_PERSONA")
    @NotNull
    @Getter
    @Setter
    private Long cvePersona;
    
    @Column(name = "CVE_REGISTRO_PROMOTOR")
    @NotNull
    @Getter
    @Setter
    private Long cveRegistroPromotor;
    
    @Column(name = "ACTIVIDAD")
    @Getter
    @Setter
    private String actividad;
    
    @Column(name = "MOTIVO")
    @Getter
    @Setter
    private String motivo;
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof McltBitacoraPersona)) {
            return false;
        }
        McltBitacoraPersona other = (McltBitacoraPersona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
