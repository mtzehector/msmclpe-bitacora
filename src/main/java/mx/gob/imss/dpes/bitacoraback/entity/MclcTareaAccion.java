/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;

/**
 *
 * @author juan.garfias
 */
@Entity
@Table(name = "MCLC_TAREA_ACCION")
public class MclcTareaAccion extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_TAREA_ACCION")
    @Getter
    @Setter
    private Long id;

    @Column(name = "CVE_ESTADO_SOLICITUD_DESTINO")
    @Getter
    @Setter
    private Long cveEstadoSolicitudDestino;

    @Column(name = "HORAS_VIGENCIA")
    @Getter
    @Setter
    private Long horasVigencia;

    @Column(name = "DESCRIPCION")
    @Getter
    @Setter
    private String descripccion;

    @Column(name = "PLANTILLA_CORREO")
    @Getter
    @Setter
    private String plantillaCorreo;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MclcTareaAccion)) {
            return false;
        }
        MclcTareaAccion other = (MclcTareaAccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
