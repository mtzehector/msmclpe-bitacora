/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.entity;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
 * @author Diego
 */
@Entity
@Table(name = "MCLC_ENTIDAD_FINANCIERA")
public class MclcEntidadFinanciera extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_ENTIDAD_FINANCIERA")
    @GeneratedValue(generator = "SEQ_GEN_MCLS_CONDICION_ENTFED")
    @GenericGenerator(
            name = "SEQ_GEN_MCLS_CONDICION_ENTFED",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_CONDICION_ENTFED"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @Getter
    @Setter
    private Long id;

    @Size(max = 200)
    @Column(name = "NOM_COMERCIAL")
    @Getter
    @Setter
    private String nombreComercial;

    @Size(max = 200)
    @Column(name = "NOM_RAZON_SOCIAL")
    @Getter
    @Setter
    private String razonSocial;

    @Size(max = 13)
    @Column(name = "REF_RFC")
    @Getter
    @Setter
    private String rfc;

    @Size(max = 25)
    @Column(name = "NUM_TELEFONO")
    @Getter
    @Setter
    private String numeroTelefono;

    @Size(max = 25)
    @Column(name = "NUM_TEL_ATENCION_CLIENTES")
    @Getter
    @Setter
    private String telefonoAtencionClientes;

    @Size(max = 200)
    @Column(name = "REF_PAGINA_WEB")
    @Getter
    @Setter
    private String paginaWeb;

    @Size(max = 50)
    @Column(name = "DES_CORREO_ELECTRONICO")
    @Getter
    @Setter
    private String correoElectronico;

    @Column(name = "NUM_CAT_PROMEDIO")
    @Getter
    @Setter
    private BigDecimal catPromedio;

    @Size(max = 11)
    @Column(name = "CVE_REG_PATRONAL")
    @Getter
    @Setter
    private String registroPatronal;

    @Column(name = "CVE_ESTADO_ENT_FINANCIERA")
    @Getter
    @Setter
    private Short estadoEF;

    @Column(name = "CORREO_ADMIN")
    @Getter
    @Setter
    private String correoAdminEF;

    @Column(name = "CVE_ENTIDAD_FINANCIERA_SIPRE")
    @Getter
    @Setter
    private String cveEntidadFinancieraSipre;

    @Column(name = "NUMERO_PROVEEDOR")
    @Getter
    @Setter
    private Long numProveedor;

    @Column(name = "CONVENIO")
    @Getter
    @Setter
    private Long sinConvenio;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MclcEntidadFinanciera that = (MclcEntidadFinanciera) o;
        return Objects.equals(id, that.id) && Objects.equals(nombreComercial, that.nombreComercial) && Objects.equals(rfc, that.rfc);
    }
}
