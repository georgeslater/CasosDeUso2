/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers.util;

/**
 *
 * @author George
 */

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {

    public static void addInfo(String errorMain) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, errorMain, errorMain));
    }

    public static void addWarn(String errorMain) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, errorMain, errorMain));
    }

    public static void addError(String errorMain) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMain, errorMain));
    }

    public static void addFatal(String errorMain) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, errorMain, errorMain));
    }
}
