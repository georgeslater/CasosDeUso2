/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entities.Diagrama;
import com.example.entities.Image;
import com.example.entities.UsuarioTable;
import java.util.Date;
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
public class ImageFacade extends AbstractFacade<Image> {
    @PersistenceContext(unitName = "CasosDeUso5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImageFacade() {
        super(Image.class);
    }
    
    public List<Image> obtenerImagenesTrasFecha(Date dt){
        
        //el usuario no hizo sync;
        if(dt == null){
            
            return this.findAll();
        
        }else{
        
            return (List<Image>) em.createNamedQuery("Image.findAllAfterLastSync").setParameter("ultimoSync", dt).getResultList();
        }
    }
    
    public int obtenerImagenesCountPorUsuarioID(UsuarioTable usuario){
                
        return ((Number)em.createNamedQuery("Image.countByUser").setParameter("userid", usuario).getSingleResult()).intValue();                      
    }
    
    public Image obtenerImagenPorDiagramaId(Diagrama diagId){
        
        try{
        
        Image resultado = (Image)em.createNamedQuery("Image.findByDiagram")
               .setParameter("diagramID", diagId)
                    .getSingleResult();
        
        return resultado;
        
        }catch(NoResultException e){
            
            return null;       
        }
    }
}
