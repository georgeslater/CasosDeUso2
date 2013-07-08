/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.dao.DiagramaFacade;
import com.example.dao.UsuarioTableFacade;
import com.example.entities.Diagrama;
import com.example.entities.UsuarioTable;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;

/**
 *
 * @author George
 */
@Named(value = "misDiagramas")
@SessionScoped
public class MisDiagramas implements Serializable{
    
    private List<Diagrama> misDiagramas;
    @EJB
    private DiagramaFacade diagFacade;
    @EJB
    private UsuarioTableFacade utFacade;
    private UsuarioTable usuarioLogueado;
    /**
     * Creates a new instance of MisDiagramas
     */
    public MisDiagramas() {
    }
    
    @PostConstruct
    public void init(){
       
        //obtener id del usuario
        String usuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        if (usuario != null) {

            setUsuarioLogueado(getUtFacade().obtenerIDPorNombre(usuario));

            if (getUsuarioLogueado() != null && getUsuarioLogueado().getIduser() != null) {
                
                misDiagramas = getDiagFacade().obtenerDiagramaPorUserID(usuarioLogueado.getIduser());
            }
        }
    }

    /**
     * @return the misDiagramas
     */
    public List<Diagrama> getMisDiagramas() {
        return misDiagramas;
    }

    /**
     * @param misDiagramas the misDiagramas to set
     */
    public void setMisDiagramas(List<Diagrama> misDiagramas) {
        this.misDiagramas = misDiagramas;
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

    /**
     * @return the usuarioLogueado
     */
    public UsuarioTable getUsuarioLogueado() {
        return usuarioLogueado;
    }

    /**
     * @param usuarioLogueado the usuarioLogueado to set
     */
    public void setUsuarioLogueado(UsuarioTable usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    /**
     * @return the utFacade
     */
    public UsuarioTableFacade getUtFacade() {
        return utFacade;
    }

    /**
     * @param utFacade the utFacade to set
     */
    public void setUtFacade(UsuarioTableFacade utFacade) {
        this.utFacade = utFacade;
    }
}
