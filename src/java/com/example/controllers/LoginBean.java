package com.example.controllers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author George
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{
    
    private String user;
    private static Logger log = Logger.getLogger(LoginBean.class.getName());
    
    public String getUser(){
        return user;
    }
    
    public void setUser(String s){
        user = s;
    }
    
    public LoginBean(){
        
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        user = request.getRemoteUser();
    }
    
    public String logout() {
                
        String result="/welcome.xhtml?faces-redirect=true";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();

        try {
          request.logout();
        } catch (ServletException e) {
          log.log(Level.SEVERE, "Failed to logout user!", e);
          result = "/ErrorLogin.xhtml?faces-redirect=true";
        }

        return result;
      }
}
