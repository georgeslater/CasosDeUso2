/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entities.FeFlujoalternativopaso;
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
}
