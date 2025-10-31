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
import javax.persistence.Lob;
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
 * @author salvador.pocteco
 */
@Entity
@Table(name = "MCLT_BITACORA_INTERFAZ")
public class McltBitacoraInterfaz extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "MCLS_BITACORA_INTERFAZ")
    @GenericGenerator(
            name = "MCLS_BITACORA_INTERFAZ",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_BITACORA_INTERFAZ"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "CVE_BITACORA_INTERFAZ")
    @Getter
    @Setter
    private Long id;

    @Column(name = "NUM_SESION")
    @Getter
    @Setter
    private Long sesion;

    @Column(name = "CVE_SOLICITUD")
    @Getter
    @Setter
    private Long idSolicitud;

    @Column(name = "CVE_TIPO_SERVICIO")
    @Getter
    @Setter
    private Long idTipoServicio;

    @Size(max = 4000)
    @Column(name = "DES_REQUEST_MSG")
    @Getter
    @Setter
    private String descRequest;

    @Size(max = 4000)
    @Column(name = "DES_RESPUESTA_MSG")
    @Getter
    @Setter
    private String descRespuesta;

    @Column(name = "NUM_TIEMPO_RESP")
    @Getter
    @Setter
    private Long numTiempoResp;

    @Column(name = "IND_EXITO")
    @Getter
    @Setter
    private Integer exito;

    @Lob
    @Column(name = "RESPONSE_ENDPOINT")
    @Getter
    @Setter
    private String reponseEndpoint;

    @Size(max = 1000)
    @Column(name = "ENDPOINT")
    @Getter
    @Setter
    private String endpoint;

    @Column(name = "CVE_REPORTE")
    @Getter
    @Setter
    private Long cveReporte;

}
