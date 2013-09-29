/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.negocio;

import com.example.dao.ActorCasoDeUsoFacade;
import com.example.dao.CasoDeUsoFacade;
import com.example.dao.CasosDeUsoRelacionesFacade;
import com.example.dao.FeEncabezadoFacade;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.CasosDeUsoRelaciones;
import com.example.entities.FeEncabezado;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class FichaExpandidaService {

    @EJB
    private CasoDeUsoFacade cduFacade;
    @EJB
    private FeEncabezadoFacade feEncFacade;
    @EJB
    private ActorCasoDeUsoFacade feActorFacade;
    @EJB
    private CasosDeUsoRelacionesFacade cduRelFacade;

    public CasoDeUso obtenerCasoDeUsoPorId(int cduId) {

        return cduFacade.find(cduId);
    }

    public FeEncabezado obtenerFeEncabezadoPorCdu(CasoDeUso cdu) {

        return feEncFacade.obtenerEncabezadoPorCdu(cdu);
    }

    public List<ActorCasoDeUso> obtenerFeActoresPorCdu(CasoDeUso cdu) {

        return feActorFacade.obtenerActoresPorCdu(cdu);
    }
    
    public List<CasosDeUsoRelaciones> obtenerCduRelacionesPorCdu(CasoDeUso cdu){
        
        return cduRelFacade.obtenerCduRelsPorCdu(cdu);
    }

    public boolean guardarEncabezado(FeEncabezado fe, Boolean esNuevo) {

        //try {

            if (esNuevo) {

                feEncFacade.create(fe);

            } else {

                feEncFacade.edit(fe);
            }

            return true;

        //}/* catch (Exception e) {
          //  return false;
        //}*/
    }

    /**
     * @return the cduRelFacade
     */
    public CasosDeUsoRelacionesFacade getCduRelFacade() {
        return cduRelFacade;
    }

    /**
     * @param cduRelFacade the cduRelFacade to set
     */
    public void setCduRelFacade(CasosDeUsoRelacionesFacade cduRelFacade) {
        this.cduRelFacade = cduRelFacade;
    }
}
