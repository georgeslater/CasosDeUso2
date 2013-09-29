/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
public class ActorCasoDeUsoFacade extends AbstractFacade<ActorCasoDeUso> {
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActorCasoDeUsoFacade() {
        super(ActorCasoDeUso.class);
    }
    
    public List<ActorCasoDeUso> obtenerActoresPorCdu(CasoDeUso cdu){
        
        List<ActorCasoDeUso> resultados = em.createNamedQuery("ActorCasoDeUso.findByCdu")
                .setParameter("cdu", cdu)
                    .getResultList();
        
        if(resultados == null){
            
            return null;
        
        }else{
            
            return resultados;
        }
    
    }
    
    public List<ActorCasoDeUso> obtenerActorCdusPorDiagramaID(int diagramaID){
        
        List<ActorCasoDeUso> resultados = em.createNamedQuery("ActorCasoDeUso.findByDiagramaId")
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
