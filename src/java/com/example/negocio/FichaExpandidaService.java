/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.negocio;

import com.example.dao.ActorCasoDeUsoFacade;
import com.example.dao.CasoDeUsoFacade;
import com.example.dao.CasosDeUsoRelacionesFacade;
import com.example.dao.FeEncabezadoFacade;
import com.example.dao.FeFlujoAlternativoFacade;
import com.example.dao.FeFlujoAlternativoPasoFacade;
import com.example.dao.FeFlujoNormalFacade;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.CasosDeUsoRelaciones;
import com.example.entities.FeEncabezado;
import com.example.entities.FeFlujoalternativo;
import com.example.entities.FeFlujoalternativopaso;
import com.example.entities.FeFlujonormal;
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
    @EJB
    private FeFlujoNormalFacade feFnFacade;
    @EJB
    private FeFlujoAlternativoFacade faFacade;
    @EJB
    private FeFlujoAlternativoPasoFacade fapFacade;

    public CasoDeUso obtenerCasoDeUsoPorId(int cduId) {

        return getCduFacade().find(cduId);
    }

    public FeEncabezado obtenerFeEncabezadoPorCdu(CasoDeUso cdu) {

        return getFeEncFacade().obtenerEncabezadoPorCdu(cdu);
    }

    public List<ActorCasoDeUso> obtenerFeActoresPorCdu(CasoDeUso cdu) {

        return getFeActorFacade().obtenerActoresPorCdu(cdu);
    }

    public List<CasosDeUsoRelaciones> obtenerCduRelacionesPorCdu(CasoDeUso cdu) {

        return cduRelFacade.obtenerCduRelsPorCdu(cdu);
    }

    public List<FeFlujonormal> obtenerFlujoNormalPasosPorEncabezado(FeEncabezado feEnc) {

        return feFnFacade.obtenerFlujoNormalPasosPorEncabezado(feEnc);
    }
    
    public void crearFlujoAlternativo(FeFlujoalternativo fa){
        
        getFaFacade().create(fa);
    }
    
    public void crearFlujoAlternativoPaso(FeFlujoalternativopaso fap){
        
        getFapFacade().create(fap);
    }
    
    public void editarFlujoAlternativo(FeFlujoalternativo fa){
        
        getFaFacade().edit(fa);
    }
    
    public void editarFlujoAlternativoPaso(FeFlujoalternativopaso fap){
        
        getFapFacade().edit(fap);
    }
    
    public boolean guardarFeFlujoNormal(List<FeFlujonormal> fnList) {
        
        try{
        
            for (FeFlujonormal fn : fnList) {

                if (fn.getId() == null) {

                    feFnFacade.create(fn);
                
                }else{
                    
                    feFnFacade.edit(fn);
                }
            }
            
            return true;
            
        }catch(Exception e){
            
            return false;
        }
    }

    public boolean guardarEncabezado(FeEncabezado fe, Boolean esNuevo) {

        try {

            if (esNuevo) {

                getFeEncFacade().create(fe);

            } else {

                getFeEncFacade().edit(fe);
            }

            return true;

        } catch (Exception e) {
            return false;
        }
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

    /**
     * @return the feFnFacade
     */
    public FeFlujoNormalFacade getFeFnFacade() {
        return feFnFacade;
    }

    /**
     * @param feFnFacade the feFnFacade to set
     */
    public void setFeFnFacade(FeFlujoNormalFacade feFnFacade) {
        this.feFnFacade = feFnFacade;
    }

    /**
     * @return the cduFacade
     */
    public CasoDeUsoFacade getCduFacade() {
        return cduFacade;
    }

    /**
     * @param cduFacade the cduFacade to set
     */
    public void setCduFacade(CasoDeUsoFacade cduFacade) {
        this.cduFacade = cduFacade;
    }

    /**
     * @return the feEncFacade
     */
    public FeEncabezadoFacade getFeEncFacade() {
        return feEncFacade;
    }

    /**
     * @param feEncFacade the feEncFacade to set
     */
    public void setFeEncFacade(FeEncabezadoFacade feEncFacade) {
        this.feEncFacade = feEncFacade;
    }

    /**
     * @return the feActorFacade
     */
    public ActorCasoDeUsoFacade getFeActorFacade() {
        return feActorFacade;
    }

    /**
     * @param feActorFacade the feActorFacade to set
     */
    public void setFeActorFacade(ActorCasoDeUsoFacade feActorFacade) {
        this.feActorFacade = feActorFacade;
    }

    /**
     * @return the faFacade
     */
    public FeFlujoAlternativoFacade getFaFacade() {
        return faFacade;
    }

    /**
     * @param faFacade the faFacade to set
     */
    public void setFaFacade(FeFlujoAlternativoFacade faFacade) {
        this.faFacade = faFacade;
    }

    /**
     * @return the fapFacade
     */
    public FeFlujoAlternativoPasoFacade getFapFacade() {
        return fapFacade;
    }

    /**
     * @param fapFacade the fapFacade to set
     */
    public void setFapFacade(FeFlujoAlternativoPasoFacade fapFacade) {
        this.fapFacade = fapFacade;
    }
}
