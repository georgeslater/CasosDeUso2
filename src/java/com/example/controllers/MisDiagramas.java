/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.controllers.util.DiagramaDataModel;
import com.example.dao.DiagramaFacade;
import com.example.dao.UsuarioTableFacade;
import com.example.entities.Diagrama;
import com.example.entities.UsuarioTable;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author George
 */
@Named(value = "misDiagramas")
@RequestScoped
public class MisDiagramas implements Serializable{
    
    private String nombreNuevoDiagrama;
    private Diagrama diagramaSeleccionado;
    private DiagramaDataModel diagramaModel; 
    
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
    
    public void editar(RowEditEvent event){
        
        Diagrama diagramaAEditar = (Diagrama) event.getObject();
        getDiagFacade().edit(diagramaAEditar);
    }
    
    public String agregarDiagrama(){
        
        if(nombreNuevoDiagrama != null && !nombreNuevoDiagrama.equals("")){
            
            Diagrama d = new Diagrama();
            d.setNombre(nombreNuevoDiagrama);
            d.setUsuario(usuarioLogueado);
            getDiagFacade().create(d);
            return "CrearCasosDeUso.xhtml?faces-redirect=true&id="+d.getId();
        }
        
        return null;
    }
    
    @PostConstruct
    public void init(){
       
        //obtener id del usuario
        String usuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        if (usuario != null) {

            setUsuarioLogueado(getUtFacade().obtenerIDPorNombre(usuario));

            if (getUsuarioLogueado() != null && getUsuarioLogueado().getIduser() != null) {
                
                misDiagramas = getDiagFacade().obtenerDiagramaPorUserID(usuarioLogueado.getIduser());
                diagramaModel = new DiagramaDataModel(misDiagramas);
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

    /**
     * @return the nombreNuevoDiagrama
     */
    public String getNombreNuevoDiagrama() {
        return nombreNuevoDiagrama;
    }

    /**
     * @param nombreNuevoDiagrama the nombreNuevoDiagrama to set
     */
    public void setNombreNuevoDiagrama(String nombreNuevoDiagrama) {
        this.nombreNuevoDiagrama = nombreNuevoDiagrama;
    }

    /**
     * @return the diagramaSeleccionado
     */
    public Diagrama getDiagramaSeleccionado() {
        return diagramaSeleccionado;
    }

    /**
     * @param diagramaSeleccionado the diagramaSeleccionado to set
     */
    public void setDiagramaSeleccionado(Diagrama diagramaSeleccionado) {
        this.diagramaSeleccionado = diagramaSeleccionado;
    }

    /**
     * @return the diagramaModel
     */
    public DiagramaDataModel getDiagramaModel() {
        return diagramaModel;
    }

    /**
     * @param diagramaModel the diagramaModel to set
     */
    public void setDiagramaModel(DiagramaDataModel diagramaModel) {
        this.diagramaModel = diagramaModel;
    }
}
