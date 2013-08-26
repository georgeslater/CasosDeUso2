/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.rest;

import com.example.rest_client.CasosDeUsoWS;
import com.example.rest_client.GetImagenesResponse;
import com.example.rest_client.ImageFacade;
import com.example.rest_client.UsuarioTableFacade;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

/**
 * REST Web Service
 *
 * @author George
 */
@Path("casosdeusowsport")
public class CasosDeUsoWSPort {
    private CasosDeUsoWS port;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CasosDeUsoWSPort
     */
    public CasosDeUsoWSPort() {
        port = getPort();
    }

    /**
     * Invokes the SOAP method getImagenes
     * @param usuarioId resource URI parameter
     * @return an instance of javax.xml.bind.JAXBElement<com.example.rest_client.GetImagenesResponse>
     */
    @GET
    @Produces("application/xml")
    @Consumes("text/plain")
    @Path("getimagenes/")
    public JAXBElement<GetImagenesResponse> getImagenes(@QueryParam("usuarioId")
            @DefaultValue("0") int usuarioId) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.util.List<com.example.rest_client.ImagenData> result = port.getImagenes(usuarioId);

                class GetImagenesResponse_1 extends com.example.rest_client.GetImagenesResponse {

                    GetImagenesResponse_1(java.util.List<com.example.rest_client.ImagenData> _return) {
                        this._return = _return;
                    }
                }
                com.example.rest_client.GetImagenesResponse response = new GetImagenesResponse_1(result);
                return new com.example.rest_client.ObjectFactory().createGetImagenesResponse(response);
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method getUsuarioId
     * @param usuario resource URI parameter
     * @param contrasenia resource URI parameter
     * @return an instance of javax.xml.bind.JAXBElement<java.lang.Integer>
     */
    @GET
    @Produces("application/xml")
    @Consumes("text/plain")
    @Path("getusuarioid/")
    public JAXBElement<Integer> getUsuarioId(@QueryParam("usuario") String usuario, @QueryParam("contrasenia") String contrasenia) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.Integer result = port.getUsuarioId(usuario, contrasenia);
                return new JAXBElement<java.lang.Integer>(new QName("http//lang.java/", "integer"), java.lang.Integer.class, result);
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     *
     */
    private CasosDeUsoWS getPort() {
        try {
            // Call Web Service Operation
            com.example.rest_client.CasosDeUsoWS_Service service = new com.example.rest_client.CasosDeUsoWS_Service();
            com.example.rest_client.CasosDeUsoWS p = service.getCasosDeUsoWSPort();
            return p;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
}
