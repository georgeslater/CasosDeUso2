/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.controllers.util.Messages;
import com.example.entities.CasoDeUso;
import com.example.entities.Diagrama;
import com.example.entities.UsuarioTable;
import com.example.negocio.ElegirCasoService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author George
 */
@Named(value = "elegirCasoBean")
@RequestScoped
public class ElegirCasoBean{
    
    private String diagramaId;
    private Boolean esInvalido;
    private Diagrama diagramaActual;
    private UsuarioTable usuarioLogueado;
    private List<CasoDeUso> cdus;
    
    @EJB
    private ElegirCasoService elegirCasoService;
    
    public ElegirCasoBean() {
    
       cdus = new ArrayList<CasoDeUso>();

        
    }
    
    public String obtenerTexto(String cduTexto){
        
        return cduTexto.length() > 15? cduTexto.substring(0, 14)+"...": cduTexto;
    }
    
    @PostConstruct
    public void init(){
        
        usuarioLogueado = elegirCasoService.getUsuarioLogueado();
        esInvalido = false;
        
        //if (usuarioLogueado != null && diagramaId != null) {
        
        
            try {

                int diagInt = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("diagramaId"));
                diagramaActual = getElegirCasoService().obtenerDiagramaPorId(diagInt);

                //checkeo de seguridad
                if (diagramaActual != null && diagramaActual.getUsuario() != null && diagramaActual.getUsuario().equals(usuarioLogueado)) {
                    cdus = elegirCasoService.obtenerCasosDeUsoPorDiagramaID(diagInt);
                } else {
                    Messages.addError("No tiene permiso de ver este diagrama!");
                    esInvalido = true;
                }

            } catch (NumberFormatException e) {
                Messages.addError("Error al cargar el diagrama.  Contacte un administrador.");
            }            
        //}
    }
    
    /**
     * @return the diagramaId
     */
    public String getDiagramaId() {
        return diagramaId;
    }

    /**
     * @param diagramaId the diagramaId to set
     */
    public void setDiagramaId(String diagramaId) {
        this.diagramaId = diagramaId;
    }

    /**
     * @return the diagramaActual
     */
    public Diagrama getDiagramaActual() {
        return diagramaActual;
    }

    /**
     * @param diagramaActual the diagramaActual to set
     */
    public void setDiagramaActual(Diagrama diagramaActual) {
        this.diagramaActual = diagramaActual;
    }

    

    /**
     * @return the esInvalido
     */
    public Boolean getEsInvalido() {
        return esInvalido;
    }

    /**
     * @param esInvalido the esInvalido to set
     */
    public void setEsInvalido(Boolean esInvalido) {
        this.esInvalido = esInvalido;
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
     * @return the cdus
     */
    public List<CasoDeUso> getCdus() {
        return cdus;
    }

    /**
     * @param cdus the cdus to set
     */
    public void setCdus(List<CasoDeUso> cdus) {
        this.setCdus(cdus);
    }

    /**
     * @return the elegirCasoService
     */
    public ElegirCasoService getElegirCasoService() {
        return elegirCasoService;
    }

    /**
     * @param elegirCasoService the elegirCasoService to set
     */
    public void setElegirCasoService(ElegirCasoService elegirCasoService) {
        this.elegirCasoService = elegirCasoService;
    }
}
