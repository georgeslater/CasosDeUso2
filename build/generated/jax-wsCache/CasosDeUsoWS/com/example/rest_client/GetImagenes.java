
package com.example.rest_client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getImagenes complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getImagenes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usuarioId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getImagenes", propOrder = {
    "usuarioId"
})
public class GetImagenes {

    protected int usuarioId;

    /**
     * Obtiene el valor de la propiedad usuarioId.
     * 
     */
    public int getUsuarioId() {
        return usuarioId;
    }

    /**
     * Define el valor de la propiedad usuarioId.
     * 
     */
    public void setUsuarioId(int value) {
        this.usuarioId = value;
    }

}
