package mx.gob.imss.dpes.bitacoraback.entity;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="MCLC_TIPO_MODIFICACION")
public class MclcTipoModificacion extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_TIPO_MODIFICACION")
    @Getter
    @Setter
    private Long cveTipoModificacion;

    @Column(name = "NOMBRE_TIPO_MODIFICACION")
    @Getter
    @Setter
    private String nombreTipoModificacion;

    @Override
    public Long getId() {
        return this.cveTipoModificacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MclcTipoModificacion that = (MclcTipoModificacion) o;
        return Objects.equals(cveTipoModificacion, that.cveTipoModificacion) && Objects.equals(nombreTipoModificacion, that.nombreTipoModificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cveTipoModificacion, nombreTipoModificacion);
    }
}
