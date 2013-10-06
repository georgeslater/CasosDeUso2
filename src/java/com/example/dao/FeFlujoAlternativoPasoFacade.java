/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entities.FeFlujoalternativo;
import com.example.entities.FeFlujoalternativopaso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
public class FeFlujoAlternativoPasoFacade extends AbstractFacade<FeFlujoalternativopaso>{
   
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeFlujoAlternativoPasoFacade() {
        super(FeFlujoalternativopaso.class);
    }
    
    public List<FeFlujoalternativopaso> obtenerFlujosAlternativoPasosPorFlujosAlternativos(List<Integer> faIdList){
        
        List<FeFlujoalternativopaso> fap = em.createNamedQuery("FeFlujoalternativopaso.findByFlujosAlternativos")
                .setParameter("faList", faIdList)
                .getResultList();

        return fap;
    }
}
