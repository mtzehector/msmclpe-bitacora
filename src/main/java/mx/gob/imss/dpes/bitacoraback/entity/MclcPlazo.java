package mx.gob.imss.dpes.bitacoraback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="MCLC_PLAZO")
public class MclcPlazo extends LogicDeletedEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_PLAZO")
    @Getter
    @Setter
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DES_PLAZO")
    @Getter
    @Setter
    private String desPlazo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_MESES")
    @Getter
    @Setter
    private Integer numMeses;

    @OneToMany(mappedBy="mclcPlazoAnterior")
    @Getter
    @Setter
    @JsonIgnore
    private List<McltBitacoraCatEF> listaMcltBitacorasCatEFPA;

    @OneToMany(mappedBy="mclcPlazoNuevo")
    @Getter
    @Setter
    @JsonIgnore
    private List<McltBitacoraCatEF> listaMcltBitacorasCatEFPN;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MclcPlazo)) {
            return false;
        }
        MclcPlazo other = (MclcPlazo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
