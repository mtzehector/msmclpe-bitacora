package mx.gob.imss.dpes.bitacoraback.repository;

import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraEstatusEF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BitacoraEstatusEFRepository
extends JpaRepository<McltBitacoraEstatusEF,Long> , JpaSpecificationExecutor<McltBitacoraEstatusEF> {

}
