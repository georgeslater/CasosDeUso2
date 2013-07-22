package com.example.negocio;

import com.example.dao.ActorCasoDeUsoFacade;
import com.example.dao.ActorFacade;
import com.example.dao.CasoDeUsoFacade;
import com.example.dao.DiagramaFacade;
import com.example.dao.FilaFacade;
import com.example.dao.ImageFacade;
import com.example.dao.RelacionFacade;
import com.example.dao.UsuarioTableFacade;
import com.example.entities.Actor;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.Diagrama;
import com.example.entities.Fila;
import com.example.entities.Image;
import com.example.entities.UsuarioTable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @EJB
    private ImageFacade imageFacade;
    @EJB
    private DiagramaFacade diagFacade;

    public UsuarioTable getUsuarioLogueado() {

        //obtener id del usuario
        String usuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        return getUsuarioFacade().obtenerIDPorNombre(usuario);
    }

    public List<Fila> getFilasPrecargadas(int diagramaID) {

        return getFilaFacade().obtenerFilasPorDiagramaID(diagramaID);
    }

    public Diagrama obtenerDiagramaPorId(int diagId) {

        return getDiagFacade().obtenerDiagramaPorId(diagId);
    }

    public List<Actor> obtenerActoresPorDiagramaID(int diagramaID) {

        return getActorFacade().obtenerActoresPorDiagramaID(diagramaID);
    }

    public Actor obtenerActorPorNombre(String nombre) {

        return getActorFacade().findByName(nombre);
    }

    public int getobtenerImagenesCountPorUsuarioID(UsuarioTable usuario) {

        return getImageFacade().obtenerImagenesCountPorUsuarioID(usuario);
    }
    
    public Boolean casoDeUsoExiste(int diagramaid, String cduNombre){
        
        return getCduFacade().findByNameAndDiagrama(diagramaid, cduNombre) != null;
    }
    
    public Boolean actorExiste(String nombre){
        
        return obtenerActorPorNombre(nombre) != null;
    }

    public Image guardarNuevoImagen(Image viejoImagen, UsuarioTable usuario, Diagrama diagrama, String title, String relativeFilename) {

        Image imagen;

        if (viejoImagen != null) {

            imagen = viejoImagen;
            imagen.setDiagramID(diagrama);
            imagen.setPath(relativeFilename);
            imagen.setTitle(title);
            imagen.setUsuario(usuario);
            getImageFacade().edit(imagen);

        } else {

            imagen = new Image(usuario, diagrama, title, relativeFilename);
            getImageFacade().create(imagen);
        }

        return imagen;
    }
    
    public Diagrama guardarDiagrama(UsuarioTable usuarioLogueado){
        
        Diagrama diag = new Diagrama();
        diag.setUsuario(usuarioLogueado);
        diag.setNombre("Unnamed Diagram");
        return getDiagFacade().createAndReturn(diag);
    }
    
    public List<String> validar(List<Fila> filas) {

        List<String> errores = new ArrayList<String>();


        for (Fila f : filas) {

            if (isFilaContieneMismoCasoDeUso(f)) {

                errores.add("Una fila no puede tener un caso de uso repetido.");
            }

            if (isCeldaVacioIncorrectamente(f)) {

                errores.add("Cada fila tendria que ser una cadena continua, sin celdas vacias en el medio.");
            }
        }

        return errores;
    }

    
    public void guardarFilas(List<Fila> filas, Diagrama diagrama, UsuarioTable usuario) {

        Map<Integer, Boolean> viejasFilasIds = new HashMap<Integer, Boolean>();
        Map<Integer, Fila> viejasFilaMap = new HashMap<Integer, Fila>();
        Map<Integer, ActorCasoDeUso> viejasActCdu = new HashMap<Integer, ActorCasoDeUso>();
        Map<String, ActorCasoDeUso> actCduPreviosMap = new HashMap<String, ActorCasoDeUso>();
        Map<Integer, Boolean> actCduFoundMap = new HashMap<Integer, Boolean>();

        if (diagrama != null && diagrama.getId() != null) {

            List<Fila> viejasFilas = getFilaFacade().obtenerFilasPorDiagramaID(diagrama.getId());

            for (Fila f : viejasFilas) {

                viejasFilasIds.put(f.getId(), false);
                viejasFilaMap.put(f.getId(), f);
            }

            List<ActorCasoDeUso> actCduPrevios = getActorCasoDeUsoFacade().obtenerActorCdusPorDiagramaID(diagrama.getId());

            for (ActorCasoDeUso actCduP : actCduPrevios) {

                actCduPreviosMap.put(actCduP.getActorid() + "-" + actCduP.getCasodeusoid(), actCduP);
                viejasActCdu.put(actCduP.getId(), actCduP);
            }

        } else {

            diagrama = new Diagrama();
            diagrama.setUsuario(usuario);
            getDiagFacade().create(diagrama);
        }

        for (Fila f : filas) {

            if (f.getActorID() != null && f.getActorID().getNombre() != null && !actorExiste(f.getActorID().getNombre()) && f.getActorID().getId() == null) {

                f.getActorID().setDiagramid(diagrama);
                getActorFacade().create(f.getActorID());
            }

            if (f.getCasoDeUso1ID() != null && !casoDeUsoExiste(diagrama.getId(), f.getCasoDeUso1ID().getText())) {

                f.getCasoDeUso1ID().setDiagramid(diagrama);               
                getCduFacade().create(f.getCasoDeUso1ID());
            }

            if (f.getActorID() != null && f.getCasoDeUso1ID() != null) {

                if (actCduPreviosMap.get(f.getActorID() + "-" + f.getCasoDeUso1ID()) == null) {

                    ActorCasoDeUso actCdu = new ActorCasoDeUso();
                    actCdu.setActorid(f.getActorID());
                    actCdu.setCasodeusoid(f.getCasoDeUso1ID());
                    actCdu.setDiagramid(diagrama);
                    getActorCasoDeUsoFacade().create(actCdu);
                    
                } else {

                    actCduFoundMap.put(actCduPreviosMap.get(f.getActorID() + "-" + f.getCasoDeUso1ID()).getId(), true);

                }
            }

            if (viejasFilasIds.keySet().contains(f.getId())) {

                getFilaFacade().edit(f);
                viejasFilasIds.put(f.getId(), true);

            } else {

                f.setDiagramaID(diagrama);
                getFilaFacade().create(f);
            }
        }
        
        for(Integer i: actCduFoundMap.keySet()){
            
            if(!actCduFoundMap.get(i)){
                
                getActorCasoDeUsoFacade().remove(viejasActCdu.get(i));
            }
        }

        for (Integer i : viejasFilaMap.keySet()) {

            if (!viejasFilasIds.get(i)) {

                getFilaFacade().remove(viejasFilaMap.get(i));
            }
        }
    }

    public Boolean isCeldaVacioIncorrectamente(Fila f) {

        return ((f.getActorID() == null && (f.getCasoDeUso1ID() != null || f.getCasoDeUso2ID() != null || f.getCasoDeUso3ID() != null || f.getCasoDeUso4ID() != null || f.getCasoDeUso5ID() != null || f.getRelacion1ID() != null || f.getRelacion2ID() != null || f.getRelacion3ID() != null || f.getRelacion4ID() != null))
                || (f.getCasoDeUso1ID() == null && (f.getCasoDeUso2ID() != null || f.getCasoDeUso3ID() != null || f.getCasoDeUso3ID() != null || f.getCasoDeUso4ID() != null || f.getCasoDeUso5ID() != null || f.getRelacion1ID() != null || f.getRelacion2ID() != null || f.getRelacion3ID() != null || f.getRelacion4ID() != null))
                || (f.getCasoDeUso2ID() == null && (f.getCasoDeUso3ID() != null || f.getCasoDeUso4ID() != null || f.getCasoDeUso5ID() != null || f.getRelacion2ID() != null || f.getRelacion3ID() != null || f.getRelacion4ID() != null))
                || (f.getCasoDeUso3ID() == null && (f.getCasoDeUso4ID() != null || f.getCasoDeUso5ID() != null || f.getRelacion3ID() != null || f.getRelacion4ID() != null))
                || (f.getCasoDeUso4ID() == null && (f.getCasoDeUso5ID() != null || f.getRelacion4ID() != null))
                || (f.getRelacion1ID() == null && (f.getCasoDeUso2ID() != null || f.getCasoDeUso3ID() != null || f.getCasoDeUso4ID() != null || f.getCasoDeUso5ID() != null || f.getRelacion2ID() != null || f.getRelacion3ID() != null || f.getRelacion4ID() != null))
                || (f.getRelacion2ID() == null && (f.getCasoDeUso3ID() != null || f.getCasoDeUso4ID() != null || f.getCasoDeUso5ID() != null || f.getRelacion3ID() != null || f.getRelacion4ID() != null))
                || (f.getRelacion3ID() == null && (f.getCasoDeUso4ID() != null || f.getCasoDeUso5ID() != null || f.getRelacion4ID() != null))
                || (f.getRelacion4ID() == null && (f.getCasoDeUso5ID() != null)));
    }

    public Boolean isFilaContieneMismoCasoDeUso(Fila f) {

        return ((f.getCasoDeUso1ID() != null && f.getCasoDeUso2ID() != null) && f.getCasoDeUso1ID().getText().equals(f.getCasoDeUso2ID().getText()))
                || ((f.getCasoDeUso1ID() != null && f.getCasoDeUso3ID() != null) && f.getCasoDeUso1ID().getText().equals(f.getCasoDeUso3ID().getText()))
                || ((f.getCasoDeUso1ID() != null && f.getCasoDeUso4ID() != null) && f.getCasoDeUso1ID().getText().equals(f.getCasoDeUso4ID().getText()))
                || ((f.getCasoDeUso1ID() != null && f.getCasoDeUso5ID() != null) && f.getCasoDeUso1ID().getText().equals(f.getCasoDeUso5ID().getText()))
                || ((f.getCasoDeUso2ID() != null && f.getCasoDeUso3ID() != null) && f.getCasoDeUso2ID().getText().equals(f.getCasoDeUso3ID().getText()))
                || ((f.getCasoDeUso2ID() != null && f.getCasoDeUso4ID() != null) && f.getCasoDeUso2ID().getText().equals(f.getCasoDeUso4ID().getText()))
                || ((f.getCasoDeUso2ID() != null && f.getCasoDeUso5ID() != null) && f.getCasoDeUso2ID().getText().equals(f.getCasoDeUso5ID().getText()))
                || ((f.getCasoDeUso3ID() != null && f.getCasoDeUso4ID() != null) && f.getCasoDeUso3ID().getText().equals(f.getCasoDeUso4ID().getText()))
                || ((f.getCasoDeUso3ID() != null && f.getCasoDeUso5ID() != null) && f.getCasoDeUso3ID().getText().equals(f.getCasoDeUso5ID().getText()))
                || ((f.getCasoDeUso4ID() != null && f.getCasoDeUso5ID() != null) && f.getCasoDeUso4ID().getText().equals(f.getCasoDeUso5ID().getText()));
    }

    public List<CasoDeUso> obtenerCasosDeUsoPorDiagramaID(int diagramaID) {

        return getCduFacade().obtenerFilasPorDiagramaID(diagramaID);
    }

    public List<ActorCasoDeUso> obtenerActorCasoDeUsosPorDiagramaID(int diagramaID) {

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

    /**
     * @return the imageFacade
     */
    public ImageFacade getImageFacade() {
        return imageFacade;
    }

    /**
     * @param imageFacade the imageFacade to set
     */
    public void setImageFacade(ImageFacade imageFacade) {
        this.imageFacade = imageFacade;
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
