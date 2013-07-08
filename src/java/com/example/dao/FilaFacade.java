/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entities.Fila;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
public class FilaFacade extends AbstractFacade<Fila> {
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FilaFacade() {
        super(Fila.class);
    }
    
    public List<Fila> obtenerFilasPorDiagramaID(Integer diagramaID){
        
        List<Fila> resultados = em.createNamedQuery("Fila.findByDiagram")
                .setParameter("diagramaID", diagramaID)
                    .getResultList();

        if (resultados == null) {
            return null;
        }
        else {
            return resultados;
        }
    }
}
