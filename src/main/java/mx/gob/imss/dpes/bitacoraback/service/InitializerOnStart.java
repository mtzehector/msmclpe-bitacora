/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.bitacoraback.service;

/**
 *
 * @author gabriel.rios
 */
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

@ApplicationScoped
@Startup
public class InitializerOnStart {

    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object pointless) {
        System.out.println(">>>InitializerOnStart.onStart() ");
    }
}
