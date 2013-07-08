/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entities.Actor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
public class ActorFacade extends AbstractFacade<Actor> {
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActorFacade() {
        super(Actor.class);
    }
    
    public List<Actor> obtenerActoresPorDiagramaID(Integer diagramaID){
        
        List<Actor> resultados = em.createNamedQuery("Actor.findByDiagramaId")
                .setParameter("diagramaid", diagramaID)
                    .getResultList();

        if (resultados == null) {
            return null;
        }
        else {
            return resultados;
        }
    }
}
