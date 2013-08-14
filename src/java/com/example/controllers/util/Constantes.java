/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers.util;

/**
 *
 * @author George
 */
public class Constantes {
   
    public static final String CELDAS_VACIAS_MSJ = "Cada fila tendría que ser una cadena continua, sin celdas vacías en el medio.";
    public static final String CASO_DE_USO_REPETIDO_MSJ = "Una fila no puede tener un caso de uso repetido.";
    public static final String RELACION_SIN_CDU_DESPUES_MSJ = "Fijese que un caso de uso siga cada relación.";
    
    public static final int NRO_MAXIMO_FILAS = 50;
    public static final String NRO_MAXIMO_FILAS_PASADO_MSJ = "No puede haber más de "+Constantes.NRO_MAXIMO_FILAS+" filas.";
}
