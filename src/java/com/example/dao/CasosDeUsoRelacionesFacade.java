/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entities.CasoDeUso;
import com.example.entities.CasosDeUsoRelaciones;
import java.util.List;
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
    
    public List<CasosDeUsoRelaciones> obtenerCduRelsPorCdu(CasoDeUso cdu){
        
        List<CasosDeUsoRelaciones> resultados = em.createNamedQuery("CasosDeUsoRelaciones.findByCdu")
                .setParameter("cdu", cdu)
                    .getResultList();
        
        if (resultados == null) {
            return null;
        }        
        else {
            return resultados;
        }
    }
    
    public List<CasosDeUsoRelaciones> obtenerCduRelsPorDiagramaID(int diagramaID){
        
        List<CasosDeUsoRelaciones> resultados = em.createNamedQuery("CasosDeUsoRelaciones.findByDiagramaId")
                .setParameter("diagramaid", diagramaID)
                    .getResultList();
        
        assert(resultados != null);

        if (resultados == null) {
            return null;
        }        
        else {
            return resultados;
        }
    }
    
}
