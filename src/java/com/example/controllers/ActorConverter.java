/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.entities.Actor;
import com.example.negocio.CrearCasosService;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author George
 */

//Es un bean por esto http://stackoverflow.com/questions/7665673/jsf2-can-i-use-ejb-to-inject-a-service-into-a-facesconverter?lq=1
@Named("actorConverter")
public class ActorConverter implements Converter {
    
    @EJB
    private CrearCasosService ejbCasos;
    
    public ActorConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        return getEjbCasos().obtenerActorPorNombre(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof Actor){
            Actor a = (Actor) value;
            if(a.getNombre() != null){
                return ""+a.getNombre();
            }else{
                return "";
            }
        }
        
        return "";
    }

    /**
     * @return the ejbCasos
     */
    public CrearCasosService getEjbCasos() {
        return ejbCasos;
    }

    /**
     * @param ejbCasos the ejbCasos to set
     */
    public void setEjbCasos(CrearCasosService ejbCasos) {
        this.ejbCasos = ejbCasos;
    }
}
