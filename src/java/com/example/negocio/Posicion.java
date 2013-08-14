package com.example.negocio;

import com.google.gson.annotations.Expose;

/**
 *
 * @author George
 * 
 * Clase que representa la posicion de un caso de uso.
 * 
 */
public class Posicion implements Cloneable{
    
    @Expose
    private int x;
    @Expose
    private int y;
    
    public Posicion(){}
    
    public Posicion(int x, int y) {

        this.x = x;
        this.y = y;
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
}
