package mx.gob.imss.dpes.bitacoraback.repository;

import mx.gob.imss.dpes.bitacoraback.entity.McltDocumentoConciliacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DocumentoConciliacionRepository extends JpaRepository<McltDocumentoConciliacion,Long>, JpaSpecificationExecutor<McltDocumentoConciliacion> {

    @Query("SELECT conciliacion FROM McltDocumentoConciliacion conciliacion WHERE conciliacion.periodo = :periodo " +
            "AND conciliacion.Documento.tipoDocumento = :cveTipoDocumento AND conciliacion.bajaRegistro is null " +
            "AND conciliacion.entidadFinanciera.id = :cveEntidadFinanciera")
    McltDocumentoConciliacion obtenerDocumentoConciliacion(
            @Param("cveEntidadFinanciera") Long cveEF,
            @Param("cveTipoDocumento") Long cveTipoDocumento,
            @Param("periodo") String periodo
    );
    @Query("SELECT conciliacion FROM McltDocumentoConciliacion conciliacion WHERE conciliacion.periodo = :periodo " +
            "AND conciliacion.Documento.tipoDocumento = :cveTipoDocumento AND conciliacion.bajaRegistro is null " +
            "AND conciliacion.entidadFinanciera.id is null")
    List<McltDocumentoConciliacion> obtenerDocumentoEFPorCuentaContable(
            @Param("periodo") String periodo,
            @Param("cveTipoDocumento") Long cveTipoDocumento
    );
    @Query("SELECT conciliacion FROM McltDocumentoConciliacion conciliacion WHERE conciliacion.periodo = :periodo " +
            "AND conciliacion.Documento.tipoDocumento = :cveTipoDocumento AND conciliacion.bajaRegistro is null " +
            "AND conciliacion.entidadFinanciera.id = 0")
    McltDocumentoConciliacion obtenerDocumentoResumenConciliacion(
            @Param("cveTipoDocumento") Long cveTipoDocumento,
            @Param("periodo") String periodo
    );

    @Query("SELECT conciliacion FROM McltDocumentoConciliacion conciliacion WHERE conciliacion.periodo = :periodo " +
            "AND conciliacion.Documento.tipoDocumento = :cveTipoDocumento AND conciliacion.bajaRegistro is null " +
            "AND conciliacion.entidadFinanciera.id = :cveEntidadFinanciera")
    List<McltDocumentoConciliacion> obtenerCartaRecibo(
            @Param("cveEntidadFinanciera") Long cveEF,
            @Param("cveTipoDocumento") Long cveTipoDocumento,
            @Param("periodo") String periodo
    );

    @Query("SELECT conciliacion FROM McltDocumentoConciliacion conciliacion WHERE conciliacion.periodo = :periodo " +
            "AND conciliacion.Documento.tipoDocumento in (:listTipoDocumento) AND conciliacion.bajaRegistro is null " +
            "ORDER BY conciliacion.entidadFinanciera.id, conciliacion.Documento.tipoDocumento" )
    List<McltDocumentoConciliacion> obtenerListCartaReciboEFTodas(
            @Param("listTipoDocumento") List<Long> listTipoDocumento,
            @Param("periodo") String periodo
    );

    @Query("SELECT conciliacion FROM McltDocumentoConciliacion conciliacion WHERE conciliacion.periodo = :periodo " +
            "AND conciliacion.Documento.tipoDocumento IN (28,29,35,36) AND conciliacion.bajaRegistro is null " +
            "AND conciliacion.entidadFinanciera.id = :cveEntidadFinanciera")
    List<McltDocumentoConciliacion> obtenerUltimasCartas(
    		 @Param("cveEntidadFinanciera") Long cveEF,
             @Param("periodo") String periodo
    );
    
    @Query("SELECT conciliacion FROM McltDocumentoConciliacion conciliacion WHERE conciliacion.periodo = :periodo " +
            "AND conciliacion.Documento.tipoDocumento = :cveTipoDocumento AND conciliacion.bajaRegistro is null " +
            "AND conciliacion.entidadFinanciera.id = :cveEntidadFinanciera")
    McltDocumentoConciliacion obtenerCartaReciboFirmadaAdministradorEF(
            @Param("cveEntidadFinanciera") Long cveEF,
            @Param("cveTipoDocumento") Long cveTipoDocumento,
            @Param("periodo") String periodo
    );
}
