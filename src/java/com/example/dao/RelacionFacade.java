/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entities.Relacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
public class RelacionFacade extends AbstractFacade<Relacion> {
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelacionFacade() {
        super(Relacion.class);
    }
    
    public Relacion obtenerRelacionPorNombre(String nombre){
        
        try{
        
            Relacion resultado = (Relacion)em.createNamedQuery("Relacion.findByNombre")
                   .setParameter("nombre", nombre)
                        .getSingleResult();

            return resultado;
        
        }catch(NoResultException e){
            
            return null;       
        }
    }
}
