package mx.gob.imss.dpes.bitacoraback.repository;

import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraCatEF;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BitacoraCatEFRepository
        extends JpaRepository<McltBitacoraCatEF,Long>,JpaSpecificationExecutor<McltBitacoraCatEF> {
    @EntityGraph(attributePaths = {"mclcCondicionOferta", "mclcTipoEvento", "mclcPlazoAnterior", "mclcPlazoNuevo"})
    @Query("SELECT mcltBitacoraCatEF FROM McltBitacoraCatEF AS mcltBitacoraCatEF " +
            "WHERE mcltBitacoraCatEF.mclcCondicionOferta.id = :mclcCondicionOfertaId " +
            "ORDER BY mcltBitacoraCatEF.id DESC")
    List<McltBitacoraCatEF> findByMclcCondicionOfertaId(@Param("mclcCondicionOfertaId") Long mclcCondicionOfertaId);
}
