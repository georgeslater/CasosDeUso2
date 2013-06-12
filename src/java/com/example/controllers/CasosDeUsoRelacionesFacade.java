/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.entities.CasosDeUsoRelaciones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
public class CasosDeUsoRelacionesFacade extends AbstractFacade<CasosDeUsoRelaciones> {
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CasosDeUsoRelacionesFacade() {
        super(CasosDeUsoRelaciones.class);
    }
    
}
