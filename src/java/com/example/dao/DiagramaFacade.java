/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entities.Diagrama;
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
public class DiagramaFacade extends AbstractFacade<Diagrama> {

    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagramaFacade() {
        super(Diagrama.class);
    }
    
    public List<Diagrama> obtenerDiagramaPorNombreYUsuario(String nombre, UsuarioTable usuario){
        
        List<Diagrama> resultados = em.createNamedQuery("Diagrama.findByNombreYUserId")
                .setParameter("nombre", nombre)
                .setParameter("usuario", usuario)
                .getResultList();
        
        if (resultados == null) {
            return null;
        } else {
            return resultados;
        }
    }
    
    public List<Diagrama> obtenerDiagramaPorUserID(Integer userID) {

        List<Diagrama> resultados = em.createNamedQuery("Diagrama.findByUserid")
                .setParameter("userid", userID)
                .getResultList();

        if (resultados == null) {
            return null;
        } else {
            return resultados;
        }

    }

    public Diagrama obtenerDiagramaPorId(int diagId) {
        
        try{
            Diagrama resultado = (Diagrama) em.createNamedQuery("Diagrama.findById")
                    .setParameter("id", diagId)
                    .getSingleResult();

            if (resultado == null) {

                return null;

            } else {

                return resultado;
            }
        }catch(NoResultException e){
            
            return null;
        }
    }
}
