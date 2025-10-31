package mx.gob.imss.dpes.bitacoraback.repository;

import mx.gob.imss.dpes.bitacoraback.model.CartaReciboFirma;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CartaReciboFirmaRepository {

    @PersistenceContext
    private EntityManager em;

    private String obtenerConsultaSQL(Long cveEntidadFinanciera) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT financiera.cve_entidad_financiera cveentidadfinanciera,financiera.nom_comercial nombrecomercial,");
        sb.append("financiera.cve_estado_ent_financiera estadoentidadfinanciera,conciliacion.cve_doc_conciliacion cvedocumentoconciliacion,");
        sb.append("documento.cve_documento cvedocumento,documento.cve_tipo_documento tipodocumento,documento.ref_doc_boveda refboveda,");
        sb.append("conciliacion.periodo periodo,conciliacion.fecha_descarga_imss fecha_descarga,conciliacion.fec_registro_alta fecha_firma,");
        sb.append("conciliacion.genero_erogacion erogacion  ");
        sb.append("FROM mgpmclpe04.mclt_documento_conciliacion conciliacion ");
        sb.append("INNER JOIN mgpmclpe04.mclt_documento documento ");
        sb.append("ON conciliacion.cve_documento = documento.cve_documento  ");
        sb.append("INNER JOIN mgpmclpe04.mclc_entidad_financiera financiera ");
        sb.append("ON conciliacion.cve_entidad_financiera = financiera.cve_entidad_financiera ");
        sb.append("WHERE conciliacion.periodo = :periodo AND documento.cve_tipo_documento = :cveTipoDocumento ");
        if (cveEntidadFinanciera != null && cveEntidadFinanciera > 0L)
            sb.append("AND financiera.cve_entidad_financiera = :cveEntidadFinanciera ");
        sb.append("ORDER BY conciliacion.fec_registro_alta DESC");
        return sb.toString();
    }

    public List<CartaReciboFirma> obtenerListCartaReciboConFirma(String periodo, Long tipoDocumento,
        Long cveEntidadFinanciera) {

        try{
            Query query = em.createNativeQuery(this.obtenerConsultaSQL(cveEntidadFinanciera));
            query.setParameter("periodo", periodo);
            query.setParameter("cveTipoDocumento", tipoDocumento);

            if (cveEntidadFinanciera != null && cveEntidadFinanciera > 0L)
                query.setParameter("cveEntidadFinanciera", cveEntidadFinanciera);

            return query.unwrap(org.hibernate.Query.class)
                    .setResultTransformer(
                            Transformers.aliasToBean(CartaReciboFirma.class)
                    ).getResultList();
        }catch (Exception e){
            throw e;
        }
    }
}
