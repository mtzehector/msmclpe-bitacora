package mx.gob.imss.dpes.bitacoraback.repository;

import mx.gob.imss.dpes.bitacoraback.entity.McltDocumentoEFPS;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentoEFPSRepository
        extends JpaRepository<McltDocumentoEFPS,Long>,JpaSpecificationExecutor<McltDocumentoEFPS> {

    @EntityGraph(attributePaths = {"mcltPrestadorServiciosEF", "mcltDocumento"})
    @Query("SELECT mcltDocumentoEFPS FROM McltDocumentoEFPS AS mcltDocumentoEFPS " +
            "WHERE mcltDocumentoEFPS.mcltPrestadorServiciosEF.cveEntidadFinanciera = :cveEntidadFinanciera " +
            "AND mcltDocumentoEFPS.mcltPrestadorServiciosEF.cveTipoPrestadorServicios = :cveTipoPrestadorServicios " +
            "AND mcltDocumentoEFPS.bajaRegistro is null " +
            "ORDER BY mcltDocumentoEFPS.id DESC")
    List<McltDocumentoEFPS> buscarDocumentosPorIdEntidadFinancieraYIdTipoPrestadorDeServicios(
            @Param("cveEntidadFinanciera") Long cveEntidadFinanciera,
            @Param("cveTipoPrestadorServicios") Integer cveTipoPrestadorServicios);

    @EntityGraph(attributePaths = {"mcltPrestadorServiciosEF", "mcltDocumento"})
    McltDocumentoEFPS findById(Long id);
}
