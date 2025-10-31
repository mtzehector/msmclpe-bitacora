package mx.gob.imss.dpes.bitacoraback.validation;

import mx.gob.imss.dpes.interfaces.bitacora.model.DocumentoConciliacionRequest;

import javax.ws.rs.ext.Provider;

@Provider
public class ValidacionCartaRecibo {

    public boolean validaCveEntidadPeriodo(DocumentoConciliacionRequest req){

        if (req == null)
            return false;

        if (req.getCveEntidadFinanciera() == null)
            return false;

        if (req.getPeriodo() == null ||req.getPeriodo().isEmpty())
            return false;

        return true;
    }

}
