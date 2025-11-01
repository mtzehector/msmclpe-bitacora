package mx.gob.imss.dpes.bitacoraback.entity;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MCLT_DOCUMENTO_EF_PS")
public class McltDocumentoEFPS extends LogicDeletedEntity<Long> {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "MCLS_DOCUMENTO_EF_PS")
    @GenericGenerator(
            name = "MCLS_DOCUMENTO_EF_PS",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_DOCUMENTO_EF_PS"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "CVE_DOC_EF_PS")
    @Getter
    @Setter
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="CVE_PRESTADOR_SERVICIOS")
    @Getter
    @Setter
    private McltPrestadorServiciosEF mcltPrestadorServiciosEF;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="CVE_DOCUMENTO")
    @Getter
    @Setter
    private McltDocumento mcltDocumento;
    
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
        if (!(object instanceof McltDocumentoEFPS)) {
            return false;
        }
        McltDocumentoEFPS other = (McltDocumentoEFPS) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    } 
}
