/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.rest;

import com.example.dao.ImageFacade;
import com.example.dao.UsuarioTableFacade;
import com.example.entities.UsuarioTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.swing.ImageIcon;

/**
 *
 * @author George
 */
@WebService(serviceName = "CasosDeUsoWS")
@Stateless()
public class CasosDeUsoWS {
    
    //private final Logger log = Logger.getLogger(getClass().getName());  
    @EJB
    private UsuarioTableFacade utFacade;
    @EJB
    private ImageFacade imgFacade;

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
     * @return the imgFacade
     */
    public ImageFacade getImgFacade() {
        return imgFacade;
    }

    /**
     * @param imgFacade the imgFacade to set
     */
    public void setImgFacade(ImageFacade imgFacade) {
        this.imgFacade = imgFacade;
    }

    /**
     * Web service operation
     */
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getImagenes")
    public List<ImagenData> getImagenes(@WebParam(name = "usuarioId") int usuarioId) throws IOException {

        UsuarioTable usuario = utFacade.find(usuarioId);
        
        List<ImagenData> imagenData = new ArrayList<ImagenData>();
        
        if (usuario != null) {

            Date dt = usuario.getUltimoSync();

            List<com.example.entities.Image> imagenesTrasUltimoSync = getImgFacade().obtenerImagenesTrasFecha(dt);

            for (com.example.entities.Image i : imagenesTrasUltimoSync) {

                if (i.getBody() != null) {

                    java.awt.Image img = new ImageIcon(i.getBody()).getImage();

                    if (img != null) {
                        
                        ImagenData id = new ImagenData(img, i.getFechaGuardado(), i.getTitle());
                        imagenData.add(id);
                    }
                }
            }
        }

        if (!imagenData.isEmpty()) {

           //actualizar ultimoSync del usuario
           usuario.setUltimoSync(new Date());
           getUtFacade().edit(usuario);
            
            return imagenData;
            
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUsuarioId")
    public Integer getUsuarioId(@WebParam(name = "usuario") String usuario, @WebParam(name = "contrasenia") String contrasenia) {
        
        if(usuario != null && contrasenia != null){
            return getUtFacade().obtenerIdPorUsuarioYContrasenia(usuario, contrasenia);
        }else{
            return null;
        }
    }
}
