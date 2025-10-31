package mx.gob.imss.dpes.bitacoraback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="MCLC_TIPO_EVENTO")
public class MclcTipoEvento extends LogicDeletedEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "MCLS_TIPO_EVENTO")
    @GenericGenerator(
            name = "MCLS_TIPO_EVENTO",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "MCLS_TIPO_EVENTO"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "CVE_TIPO_EVENTO")
    @Getter
    @Setter
    private Long id;

    @Column(name="DES_TIPO_EVENTO")
    @Getter
    @Setter
    private String desTipoEvento;

    @OneToMany(mappedBy="mclcTipoEvento")
    @Getter
    @Setter
    @JsonIgnore
    private List<McltBitacoraCatImss> listaMcltBitacorasCatImss;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MclcTipoEvento)) {
            return false;
        }
        MclcTipoEvento other = (MclcTipoEvento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
