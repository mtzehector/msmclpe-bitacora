package mx.gob.imss.dpes.bitacoraback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "MCLC_CONDICION_OFERTA")
public class MclcCondicionOferta extends LogicDeletedEntity<Long> {

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_CONDICION_OFERTA")
    @Getter
    @Setter
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_ENTIDAD_FINANCIERA")
    @Getter
    @Setter
    private long cveEntidadFinanciera;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_PLAZO")
    @Getter
    @Setter
    private Long cvePlazo;

    @Column(name = "POR_TASA_ANUAL")
    @Getter
    @Setter
    private BigDecimal porTasaAnual;

    @Column(name = "POR_CAT")
    @Getter
    @Setter
    private BigDecimal porCat;

    @OneToMany(mappedBy="mclcCondicionOferta")
    @Getter
    @Setter
    @JsonIgnore
    private List<McltBitacoraCatEF> listaMcltBitacorasCatEF;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MclcCondicionOferta)) {
            return false;
        }
        MclcCondicionOferta other = (MclcCondicionOferta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
