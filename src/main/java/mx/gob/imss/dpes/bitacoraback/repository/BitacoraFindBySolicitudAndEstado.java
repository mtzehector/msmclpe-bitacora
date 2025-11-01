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
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacora;
import mx.gob.imss.dpes.bitacoraback.entity.McltBitacora_;

/**
 *
 * @author eduardo.loyo
 */
public class BitacoraFindBySolicitudAndEstado extends BaseSpecification<McltBitacora> {
    private final Long solicitud;
    private final Long estado;
    
    public BitacoraFindBySolicitudAndEstado(Long solicitud, Long estado){
        this.solicitud = solicitud;
        this.estado = estado;
        
    }

    @Override
    public Predicate toPredicate(Root<McltBitacora> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.and(cb.equal(root.get(McltBitacora_.idSolicitud), this.solicitud),cb.equal(root.get(McltBitacora_.ESTADO_SOLICITUD), this.estado));
    }
    
}
