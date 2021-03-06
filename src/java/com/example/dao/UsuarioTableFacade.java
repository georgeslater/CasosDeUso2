/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entities.UsuarioTable;
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
public class UsuarioTableFacade extends AbstractFacade<UsuarioTable> {
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public UsuarioTableFacade() {
        super(UsuarioTable.class);
    }
    
    public Integer obtenerIdPorUsuarioYContrasenia(String usuario, String contrasenia){
        
        try{
        
            UsuarioTable usuarioEncontrado = (UsuarioTable)em.createNamedQuery("UsuarioTable.findByUsuarioYContrasenia")
                .setParameter("usuario", usuario)
                    .setParameter("contrasenia", contrasenia)
                        .getSingleResult();
            
            return usuarioEncontrado.getIduser();
            
        }catch(NoResultException e){
            
            return null;
        }
    }
    
    public UsuarioTable obtenerIDPorNombre(String nombre){
        
        List<UsuarioTable> resultados = em.createNamedQuery("UsuarioTable.findByUsernameusuario")
                .setParameter("usernameusuario", nombre)
                    .getResultList();

        if (resultados == null) {
            return null;
        }
        else if (resultados.size() != 1){
            return null;
        }
        else {
            return resultados.get(0);
        }
    }
}
