/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.negocio;

import com.example.dao.CasoDeUsoFacade;
import com.example.dao.DiagramaFacade;
import com.example.dao.UsuarioTableFacade;
import com.example.entities.CasoDeUso;
import com.example.entities.Diagrama;
import com.example.entities.UsuarioTable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

/**
 *
 * @author George
 */
@Stateless
public class ElegirCasoService {
    
    @EJB
    private CasoDeUsoFacade cduFacade;
    @EJB
    private UsuarioTableFacade usuarioFacade;
    @EJB
    private DiagramaFacade diagFacade;
    
    public Diagrama obtenerDiagramaPorId(int diagId) {

        return getDiagFacade().obtenerDiagramaPorId(diagId);
    }
    
    public UsuarioTable getUsuarioLogueado() {

        //obtener id del usuario
        String usuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        return getUsuarioFacade().obtenerIDPorNombre(usuario);
    }
    
    public List<CasoDeUso> obtenerCasosDeUsoPorDiagramaID(int diagramaID) {

        return getCduFacade().obtenerCdusPorDiagramaID(diagramaID);
    }

    /**
     * @return the cduFacade
     */
    public CasoDeUsoFacade getCduFacade() {
        return cduFacade;
    }

    /**
     * @param cduFacade the cduFacade to set
     */
    public void setCduFacade(CasoDeUsoFacade cduFacade) {
        this.cduFacade = cduFacade;
    }

    /**
     * @return the usuarioFacade
     */
    public UsuarioTableFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    /**
     * @param usuarioFacade the usuarioFacade to set
     */
    public void setUsuarioFacade(UsuarioTableFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    /**
     * @return the diagFacade
     */
    public DiagramaFacade getDiagFacade() {
        return diagFacade;
    }

    /**
     * @param diagFacade the diagFacade to set
     */
    public void setDiagFacade(DiagramaFacade diagFacade) {
        this.diagFacade = diagFacade;
    }

}
