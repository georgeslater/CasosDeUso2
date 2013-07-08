/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

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
public class CasoDeUsoFacade extends AbstractFacade<CasoDeUso> {
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CasoDeUsoFacade() {
        super(CasoDeUso.class);
    }
    
    public List<CasoDeUso> obtenerFilasPorDiagramaID(Integer diagramaID){
        
        List<CasoDeUso> resultados = em.createNamedQuery("CasoDeUso.findByDiagramaId")
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
