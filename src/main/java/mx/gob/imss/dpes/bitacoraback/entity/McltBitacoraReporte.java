/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.entity;

import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author juan.garfias
 */
@Entity
@Table(name = "MCLT_BITACORA_REPORTE")
public class McltBitacoraReporte extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "MCLS_BITACORA_REPORTE")
    @GenericGenerator(
            name = "MCLS_BITACORA_REPORTE",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_BITACORA_REPORTE"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "CVE_BITACORA_REPORTE")
    @Getter
    @Setter
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_REPORTE")
    @Getter
    @Setter
    private Long cveReporte;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PERIODO_NOMINA")
    @Getter
    @Setter
    private String periodoNomina;

    @Getter
    @Setter
    @Size(max = 20)
    @Column(name = "CURP_USUARIO")
    private String curpUsuario;

    @Getter
    @Setter
    @Size(max = 100)
    @Column(name = "DESCRIPCCION")
    private String descripccion;

    @Getter
    @Setter
    @JoinColumn(name = "CVE_ESTADO_REPORTE", referencedColumnName = "CVE_ESTADO_REPORTE")
    @ManyToOne(optional = false)
    private MclcEstadoReporte estadoReporte;

    @Getter
    @Setter
    @JoinColumn(name = "CVE_SUB_TIPO_REPORTE", referencedColumnName = "CVE_SUB_TIPO_REPORTE")
    @ManyToOne(optional = false)
    private MclcSubTipoReporte subTipoReporte;

    @Getter
    @Setter
    @JoinColumn(name = "CVE_TIPO_REPORTE", referencedColumnName = "CVE_TIPO_REPORTE")
    @ManyToOne(optional = false)
    private MclcTipoReporte tipoReporte;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof McltBitacoraReporte)) {
            return false;
        }
        McltBitacoraReporte other = (McltBitacoraReporte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
