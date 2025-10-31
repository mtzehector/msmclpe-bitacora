package mx.gob.imss.dpes.bitacoraback.repository;

import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraPensionado;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BitacoraPensionadoRepository  extends JpaRepository<McltBitacoraPensionado, Long>,
        JpaSpecificationExecutor<McltBitacoraPensionado> {

    List<McltBitacoraPensionado> findByCvePersonaOrderByIdDesc(Long cvePersona);

}
