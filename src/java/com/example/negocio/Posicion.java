/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.negocio;

import com.google.gson.annotations.Expose;
import javax.ejb.Stateless;

/**
 *
 * @author George
 */
@Stateless
public class Posicion {

    @Expose
    private int x;
    @Expose
    private int y;
    @Expose
    private String relacion;
    
    public Posicion(){}
    
    public Posicion(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public Posicion(int x, int y, String relacion) {

        this.x = x;
        this.y = y;
        this.relacion = relacion;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the relacion
     */
    public String getRelacion() {
        return relacion;
    }

    /**
     * @param relacion the relacion to set
     */
    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }
}
