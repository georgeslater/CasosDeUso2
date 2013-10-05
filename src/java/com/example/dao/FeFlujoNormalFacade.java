package com.example.dao;

import com.example.entities.FeEncabezado;
import com.example.entities.FeFlujonormal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
public class FeFlujoNormalFacade extends AbstractFacade<FeFlujonormal> {
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeFlujoNormalFacade() {
        super(FeFlujonormal.class);
    }

    public List<FeFlujonormal> obtenerFlujoNormalPasosPorEncabezado(FeEncabezado feEnc){
        
        try{
            
            List<FeFlujonormal> fe = em.createNamedQuery("FeFlujonormal.findByFeEncabezado")
                    .setParameter("feEnc", feEnc)
                        .getResultList();
            
            return fe;
            
        }catch(NoResultException e){
            
            return null;
        }
    }
}


