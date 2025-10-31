/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import mx.gob.imss.dpes.baseback.persistence.BaseSpecification;
import mx.gob.imss.dpes.bitacoraback.entity.McltCronTarea;
import mx.gob.imss.dpes.bitacoraback.entity.McltCronTarea_;

/**
 *
 * @author juanf.barragan
 */
public class TareaBySolicitudAccionSpecification extends BaseSpecification<McltCronTarea>{
    
     private final Long cveSol;
     private final Long tarea = 11L;

    public TareaBySolicitudAccionSpecification(Long cveSol) {
        this.cveSol = cveSol;
    }
     
     

    @Override
    public Predicate toPredicate(Root<McltCronTarea> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.and(cb.equal(root.get(McltCronTarea_.CVE_SOLICITUD), this.cveSol), cb.equal(root.get(McltCronTarea_.TAREA_ACCION), tarea));
    }
    
    
}
