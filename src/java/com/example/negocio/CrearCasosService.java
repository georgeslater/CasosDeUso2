
package com.example.negocio;

import com.example.dao.ActorCasoDeUsoFacade;
import com.example.dao.ActorFacade;
import com.example.dao.CasoDeUsoFacade;
import com.example.dao.FilaFacade;
import com.example.dao.RelacionFacade;
import com.example.dao.UsuarioTableFacade;
import com.example.entities.Actor;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.Fila;
import com.example.entities.UsuarioTable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

@Stateless
public class CrearCasosService {
    
    @EJB
    private ActorFacade actorFacade;
    @EJB
    private ActorCasoDeUsoFacade actorCasoDeUsoFacade;
    @EJB
    private CasoDeUsoFacade cduFacade;
    @EJB
    private RelacionFacade relFacade;
    @EJB
    private FilaFacade filaFacade;
    @EJB
    private UsuarioTableFacade usuarioFacade;
    
    public UsuarioTable getUsuarioLogueado(){
                           
        //obtener id del usuario
        String usuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        return getUsuarioFacade().obtenerIDPorNombre(usuario);
    }
    
    public List<Fila> getFilasPrecargadas(int diagramaID){
        
        return getFilaFacade().obtenerFilasPorDiagramaID(diagramaID);
    }
    
    public List<Actor> obtenerActoresPorDiagramaID(int diagramaID){
        
        return getActorFacade().obtenerActoresPorDiagramaID(diagramaID);
    }
    
    public List<CasoDeUso> obtenerCasosDeUsoPorDiagramaID(int diagramaID){
        
        return getCduFacade().obtenerFilasPorDiagramaID(diagramaID);
    }
    
    public List<ActorCasoDeUso> obtenerActorCasoDeUsosPorDiagramaID(int diagramaID){
        
        return getActorCasoDeUsoFacade().obtenerActorCdusPorDiagramaID(diagramaID);
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
     * @return the actorFacade
     */
    public ActorFacade getActorFacade() {
        return actorFacade;
    }

    /**
     * @param actorFacade the actorFacade to set
     */
    public void setActorFacade(ActorFacade actorFacade) {
        this.actorFacade = actorFacade;
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
     * @return the relFacade
     */
    public RelacionFacade getRelFacade() {
        return relFacade;
    }

    /**
     * @param relFacade the relFacade to set
     */
    public void setRelFacade(RelacionFacade relFacade) {
        this.relFacade = relFacade;
    }
    
    
    public ActorCasoDeUsoFacade getActorCasoDeUsoFacade() {
        return actorCasoDeUsoFacade;
    }

    /**
     * @param actorCasoDeUsoFacade the actorCasoDeUsoFacade to set
     */
    public void setActorCasoDeUsoFacade(ActorCasoDeUsoFacade actorCasoDeUsoFacade) {
        this.actorCasoDeUsoFacade = actorCasoDeUsoFacade;
    }
    
     /**
     * @return the filaFacade
     */
    public FilaFacade getFilaFacade() {
        return filaFacade;
    }

    /**
     * @param filaFacade the filaFacade to set
     */
    public void setFilaFacade(FilaFacade filaFacade) {
        this.filaFacade = filaFacade;
    }
}
