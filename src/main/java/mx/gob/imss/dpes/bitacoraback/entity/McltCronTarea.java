/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
 * @author juan.garfias
 */
@Entity
@Table(name = "MCLT_CRON_TAREA")
public class McltCronTarea extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "MCLS_CRON_TAREA")
    @GenericGenerator(
            name = "MCLS_CRON_TAREA",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_CRON_TAREA"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "CVE_CRON_TAREA")
    @Getter
    @Setter
    private Long id;

    @Column(name = "CVE_SOLICITUD")
    @Getter
    @Setter
    private Long cveSolicitud;

    @JoinColumn(name = "CVE_TAREA_ACCION", referencedColumnName = "CVE_TAREA_ACCION")
    @Getter
    @Setter
    @ManyToOne(optional = false)
    private MclcTareaAccion tareaAccion;

    @Column(name = "FECHA_LIMITE")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date fechaLimite;

    @Column(name = "ACTIVO")
    @Getter
    @Setter
    private Long activo;

    @Column(name = "EJECUTADO")
    @Getter
    @Setter
    private Long ejecutado;

    @Getter
    @Setter
    @Transient
    List<MclcTareaEstadoOrigen> lstEstadosOrigen;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof McltCronTarea)) {
            return false;
        }
        McltCronTarea other = (McltCronTarea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
