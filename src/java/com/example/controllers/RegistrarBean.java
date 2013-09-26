/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.dao.UsuarioTableFacade;
import com.example.entities.UsuarioTable;
import com.example.negocio.RegistrarService;
import javax.ejb.EJB;
import javax.inject.Named;
import com.example.controllers.util.Messages;
import java.io.Serializable;
import java.util.Set;
import javax.enterprise.context.SessionScoped;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author George
 */
@Named(value = "registrarBean")
@SessionScoped
public class RegistrarBean implements Serializable {

    private UsuarioTable ut;
    private Boolean mostrarLink;
    @EJB
    private RegistrarService regService;
    @EJB
    private UsuarioTableFacade utFacade;

    public RegistrarBean() {

        ut = new UsuarioTable();
    }

    public String registrarUsuario() {


        //if(ut != null && ut.getUsernameusuario() != null && ut.getContraseniausuario() != null){

        try {

            if (regService.usuarioNoExiste(ut.getUsernameusuario()) == true) {

                getUtFacade().create(ut);
                Boolean isExito = regService.asignarGrupoAUsuario(ut);

                if (isExito) {

                    String successMsg = ut.getNombre() != null ? ", " + ut.getNombre() : "";
                    Messages.addInfo("Bienvenido" + successMsg + "!");
                    mostrarLink = true;

                } else {

                    Messages.addFatal("Ha pasado un error de registracion");
                }
            } else {
                
                Messages.addWarn("El nombre de usuario que Ud. ha elegido no esta disponible.  Por favor, elige otro.");
            }
        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<?>> cves = e.getConstraintViolations();
        }

        return null;
        //       }
    }

    /**
     * @return the ut
     */
    public UsuarioTable getUt() {
        return ut;
    }

    /**
     * @param ut the ut to set
     */
    public void setUt(UsuarioTable ut) {
        this.ut = ut;
    }

    /**
     * @return the regService
     */
    public RegistrarService getRegService() {
        return regService;
    }

    /**
     * @param regService the regService to set
     */
    public void setRegService(RegistrarService regService) {
        this.regService = regService;
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
     * @return the mostrarLink
     */
    public Boolean getMostrarLink() {
        return mostrarLink;
    }

    /**
     * @param mostrarLink the mostrarLink to set
     */
    public void setMostrarLink(Boolean mostrarLink) {
        this.mostrarLink = mostrarLink;
    }
}
