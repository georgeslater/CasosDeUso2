package com.example.controllers;

import com.example.entities.*;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ManagedBean(name = "casosDeUsoBean")
@SessionScoped
public class CasosDeUsoBean implements Serializable {

    private List<CasoDeUso> myCasosDeUso;
    @EJB
    private CasoDeUsoFacade cduFacade;
    private String gsonString;
    
    private com.google.gson.Gson myGSON;
    
    public CasosDeUsoBean() {
        
        
    }
    
    public String getGsonString(){
        
        return gsonString;
    }
    
    public com.google.gson.Gson getMyGSON(){
        
        return myGSON;
    }
    
    @PostConstruct
    public void init(){
        
        myCasosDeUso = getFacade().findAll();
        
        for(CasoDeUso c: myCasosDeUso){
            
            GsonBuilder = new GsonBuilder();
            gsonString = myGSON.toJson(c);
        }
    }
    
    private CasoDeUsoFacade getFacade() {
        return cduFacade;
    }
    
    public List<CasoDeUso> getMyCasosDeUso(){
        
        return myCasosDeUso;
    }
    
}
