/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entities.FeFlujoalternativo;
import com.example.entities.FeFlujonormal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
public class FeFlujoAlternativoFacade extends AbstractFacade<FeFlujoalternativo>{
   
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeFlujoAlternativoFacade() {
        super(FeFlujoalternativo.class);
    }
    
    public List<FeFlujoalternativo> obtenerFlujosAlternativosPorFlujosNormales(List<Integer> fnIdList){
        
        List<FeFlujoalternativo> fa = em.createNamedQuery("FeFlujoalternativo.findByFlujosNormales")
                .setParameter("fnList", fnIdList)
                .getResultList();

        return fa;
    
    }
}
