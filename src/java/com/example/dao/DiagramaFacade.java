/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entities.Diagrama;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
public class DiagramaFacade extends AbstractFacade<Diagrama> {
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagramaFacade() {
        super(Diagrama.class);
    }
    
    public List<Diagrama> obtenerDiagramaPorUserID(Integer userID){
        
        List<Diagrama> resultados = em.createNamedQuery("Diagrama.findByUserid")
                .setParameter("userid", userID)
                    .getResultList();

        if (resultados == null) {
            return null;
        }
        else if (resultados.size() != 1){
            return null;
        }
        else {
            return resultados;
        }
    }
}
