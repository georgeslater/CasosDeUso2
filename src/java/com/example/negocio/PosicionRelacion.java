
package com.example.negocio;

import com.google.gson.annotations.Expose;

/**
 *
 * @author George
 * Clase que representa la posicion de un caso de uso, y el tipo de relacion que lo constituye.
 * Una posicion puede ser compartida (C1 -> C3 y C2 -> C3, por ejemplo) pero tener diferentes relaciones.
 * Por eso hicimos una clase aparte.
 * 
 */
public class PosicionRelacion {
    
      @Expose
      private Posicion p;
      @Expose
      private String relacion;
      
      public PosicionRelacion(Posicion p, String relacion){
          
          this.p = p;
          this.relacion = relacion;
      }

    /**
     * @return the p
     */
    public Posicion getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Posicion p) {
        this.p = p;
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
