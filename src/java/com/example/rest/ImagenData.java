/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.rest;

import java.util.Date;

/**
 *
 * @author George
 * Es un wrapper por este motivo:
 * http://stackoverflow.com/questions/13782797/jax-ws-exception-return-is-not-a-valid-property
 */
public class ImagenData {
   
    private java.awt.Image imagen;
    private Date fechaCreada;
    private String titulo;
    
    public ImagenData(java.awt.Image imagen, Date fechaCreada, String titulo){
        
        this.imagen = imagen;
        this.fechaCreada = fechaCreada;
        this.titulo = titulo;
    } 

    /**
     * @return the imagen
     */
    public java.awt.Image getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(java.awt.Image imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the fechaCreada
     */
    public Date getFechaCreada() {
        return fechaCreada;
    }

    /**
     * @param fechaCreada the fechaCreada to set
     */
    public void setFechaCreada(Date fechaCreada) {
        this.fechaCreada = fechaCreada;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
