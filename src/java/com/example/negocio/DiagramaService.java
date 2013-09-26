/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.negocio;

import com.example.dao.DiagramaFacade;
import com.example.entities.UsuarioTable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author George
 */
@Stateless
public class DiagramaService {
    
    @EJB
    private DiagramaFacade diagFacade;
    
    public Boolean nombreDiagramaExiste(String nombre, UsuarioTable usuario){
               
        return !diagFacade.obtenerDiagramaPorNombreYUsuario(nombre, usuario).isEmpty();
    }

    /**
     * @return the diagFacade
     */
    public DiagramaFacade getDiagFacade() {
        return diagFacade;
    }

    /**
     * @param diagFacade the diagFacade to set
     */
    public void setDiagFacade(DiagramaFacade diagFacade) {
        this.diagFacade = diagFacade;
    }

}
