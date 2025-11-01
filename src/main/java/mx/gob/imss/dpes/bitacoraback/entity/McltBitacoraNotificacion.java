/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import mx.gob.imss.dpes.support.config.CustomDateDeserializer;
import mx.gob.imss.dpes.support.config.CustomDateSerializer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author edgar.arenas
 */
@Entity
@Table(name = "MCLT_BITACORA_NOTIFICACION")
public class McltBitacoraNotificacion extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "MCLS_BITACORA_NOTIFICACION")
    @GenericGenerator(
            name = "MCLS_BITACORA_NOTIFICACION",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_BITACORA_NOTIFICACION"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    
    @Column(name = "CVE_BITACORA_NOTIFICACION")
    @Getter
    @Setter
    private Long id;
    
    @Column(name = "CVE_NOTIFICACION")
    @NotNull
    @Getter
    @Setter
    private Long cveNotificacion;

    @Column(name = "CURP_USUARIO")
    @NotNull
    @Getter
    @Setter
    private String curp;

    @Column(name = "CVE_ESTADO_NOTIFICACION")
    @Getter
    @Setter
    private Long cveEstadoNotificacion;
    
    @Column(name = "FEC_NUEVO_VENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date fecNuevoVencimiento;

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof McltBitacoraNotificacion)) {
            return false;
        }
        McltBitacoraNotificacion other = (McltBitacoraNotificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

       
}
