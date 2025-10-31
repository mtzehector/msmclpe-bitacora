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
@Table(name = "MCLT_HISTORICO_USUARIO")
public class McltHistoricoUsuario extends LogicDeletedEntity<Long>{
    
    private static final long serialVersionUID = 1L;

    
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "MCLS_HISTORICO_USUARIO")
    @GenericGenerator(
            name = "MCLS_HISTORICO_USUARIO",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_HISTORICO_USUARIO"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "CVE_HISTORICO_USUARIO")
    @Getter
    @Setter
    private Long id;
    
    @Column(name = "CVE_CURP_MODIFICADOR")
    @Getter
    @Setter
    private String curpModificador;
    
    @Column(name = "CVE_PERSONA")
    @NotNull
    @Getter
    @Setter
    private Long cvePersona;
    
    @Column(name = "CORREO_ELECTRONICO")
    @Getter
    @Setter
    private String correoElectronico;
    
    @Column(name = "TEL_LOCAL")
    @Getter
    @Setter
    private Long telLocal;
    
    @Column(name = "TEL_CELULAR")
    @Getter
    @Setter
    private Long telCelular;
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof McltHistoricoUsuario)) {
            return false;
        }
        McltHistoricoUsuario other = (McltHistoricoUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
