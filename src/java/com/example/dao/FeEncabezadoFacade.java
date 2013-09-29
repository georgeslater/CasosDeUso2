package com.example.dao;

import com.example.entities.CasoDeUso;
import com.example.entities.FeEncabezado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
public class FeEncabezadoFacade extends AbstractFacade<FeEncabezado> {
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeEncabezadoFacade() {
        super(FeEncabezado.class);
    }

    public FeEncabezado obtenerEncabezadoPorCdu(CasoDeUso cdu){
        
        try{
            
            FeEncabezado fe = (FeEncabezado)em.createNamedQuery("FeEncabezado.findByCasoDeUso")
                    .setParameter("cdu", cdu)
                        .getSingleResult();
            
            return fe;
            
        }catch(NoResultException e){
            
            return null;
        }
    }
}

