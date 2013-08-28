/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.controllers.util.Constantes;
import com.example.entities.Grupo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
public class GrupoFacade extends AbstractFacade<Grupo> {
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;
    
    public Grupo obtenerUsuarioGrupo(){
        
        try{
        
            Grupo resultado = (Grupo)em.createNamedQuery("Grupo.findByNombre")
                   .setParameter("nombre", Constantes.USER_GRUPO_NOMBRE)
                        .getSingleResult();

            return resultado;
        
        }catch(NoResultException e){
            
            return null;       
        }
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoFacade() {
        super(Grupo.class);
    }
    
}
