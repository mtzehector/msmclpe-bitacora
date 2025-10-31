package mx.gob.imss.dpes.bitacoraback.consumer;

import mx.gob.imss.dpes.bitacoraback.entity.McltBitacoraInterfaz;
import mx.gob.imss.dpes.bitacoraback.entity.McltToken;
import mx.gob.imss.dpes.bitacoraback.exception.BitacoraInterfazException;
import mx.gob.imss.dpes.common.consumer.BaseConsumer;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.interfaces.bitacora.model.BitacoraInterfaz;


import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

@MessageDriven(name = "BitacoraInterfazConsumer", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/MCLPEBitacoraInterfaz"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class BitacoraInterfazConsumer extends BaseConsumer {

  @PersistenceContext
  private EntityManager em;

  private Date agregarHoras(Date fecha, int horas) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fecha);
    calendar.add(Calendar.HOUR, horas);
    return calendar.getTime();
  }

  private McltToken validarSesion (String tokenSeguridad, Date altaRegistro) {
    String jql = "SELECT t FROM McltToken t " +
            "WHERE t.token = :tokenSeguridad AND t.bajaRegistro is null " +
            "AND t.altaRegistro >= :fechaInicio " +
            "AND t.altaRegistro <= :fechaFin";

    TypedQuery<McltToken> query = em.createQuery(jql, McltToken.class);
    query.setParameter("tokenSeguridad", tokenSeguridad);
    query.setParameter("fechaInicio", agregarHoras(altaRegistro, -4));
    query.setParameter("fechaFin", agregarHoras(altaRegistro, 4));
    List<McltToken> tokenList = query.getResultList();

    if (tokenList != null && tokenList.size() == 1)
      return tokenList.get(0);

    log.log(Level.WARNING,
          "BitacoraInterfazConsumer.validarSesion - tokenSeguridad = [" + tokenSeguridad + "], altaRegistro = [" +
                altaRegistro + "] NO ENCONTRADO");
    return null;
  }

  private String ajustaValorCadena(Long sesion, String valor, int numPosiciones) {
    if (valor == null)
      return valor;

    if (valor.isEmpty())
      return valor;

    if(valor.length() <= numPosiciones)
      return valor;

    log.log(Level.WARNING,
            "BitacoraInterfazConsumer.ajustaValorCadena - sesion = [" + sesion + "], valor = [" +
                    valor + "], numPosiciones = [" + numPosiciones + "]");
    return valor.substring(0, numPosiciones);
  }

  private McltBitacoraInterfaz getMcltBitacoraInterfazFromBitacoraInterfaz(BitacoraInterfaz bitacoraInterfaz)
        throws BitacoraInterfazException {
    try {
      McltBitacoraInterfaz mcltBitacoraInterfaz = new McltBitacoraInterfaz();

      Long sesion = (bitacoraInterfaz.getSesion() != null ? bitacoraInterfaz.getSesion() : 0L);

      mcltBitacoraInterfaz.setSesion(sesion);
      mcltBitacoraInterfaz.setIdSolicitud(bitacoraInterfaz.getIdSolicitud());
      mcltBitacoraInterfaz.setIdTipoServicio(bitacoraInterfaz.getIdTipoServicio());
      mcltBitacoraInterfaz.setDescRequest(ajustaValorCadena(sesion, bitacoraInterfaz.getDescRequest(), 4000));
      mcltBitacoraInterfaz.setDescRespuesta(ajustaValorCadena(sesion, bitacoraInterfaz.getDescRespuesta(), 4000));
      mcltBitacoraInterfaz.setNumTiempoResp(bitacoraInterfaz.getNumTiempoResp());
      mcltBitacoraInterfaz.setExito(bitacoraInterfaz.getExito());
      mcltBitacoraInterfaz.setCveReporte(bitacoraInterfaz.getCveReporte());
      mcltBitacoraInterfaz.setEndpoint(ajustaValorCadena(sesion, bitacoraInterfaz.getEndpoint(), 1000));
      mcltBitacoraInterfaz.setReponseEndpoint(bitacoraInterfaz.getReponseEndpoint());
      mcltBitacoraInterfaz.setAltaRegistro(bitacoraInterfaz.getAltaRegistro());

      return mcltBitacoraInterfaz;
    } catch(Exception e) {
      log.log(Level.SEVERE,
          "BitacoraInterfazConsumer.getMcltBitacoraInterfazFromBitacoraInterfaz - bitacoraInterfaz = [" +
                bitacoraInterfaz + "]", e);
    }
    throw new BitacoraInterfazException(BitacoraInterfazException.ERROR_DESCONOCIDO_EN_EL_SERVICIO);
  }

  @Override
  protected void proccess(Message message) {
    try {
      BitacoraInterfaz bitacoraInterfaz = (BitacoraInterfaz) message.getPayload();

      if(bitacoraInterfaz.getToken() != null && bitacoraInterfaz.getAltaRegistro() != null) {
        McltToken mcltToken = validarSesion(bitacoraInterfaz.getToken(), bitacoraInterfaz.getAltaRegistro());
        if(mcltToken != null)
          bitacoraInterfaz.setSesion(mcltToken.getSesion());
      }

      em.persist(
          this.getMcltBitacoraInterfazFromBitacoraInterfaz(bitacoraInterfaz)
      );
    } catch (BusinessException ex) {
      log.log(Level.SEVERE, "BitacoraInterfazConsumer.proccess - message = [" + message.getPayload() + "]", ex);
    } catch (Exception ex) {
      log.log(Level.SEVERE, "BitacoraInterfazConsumer.proccess - message = [" + message.getPayload() + "]", ex);
    }
  }
}
