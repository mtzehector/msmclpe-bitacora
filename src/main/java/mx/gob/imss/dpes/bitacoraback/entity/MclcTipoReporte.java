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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;

/**
 *
 * @author juan.garfias
 */
@Entity
@Table(name = "MCLC_TIPO_REPORTE")
public class MclcTipoReporte extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_TIPO_REPORTE")
    private Long id;

    @Getter
    @Setter
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DES_TIPO_REPORTE")
    private String desTipoReporte;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MclcTipoReporte)) {
            return false;
        }
        MclcTipoReporte other = (MclcTipoReporte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
