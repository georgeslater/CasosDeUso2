package com.example.negocio;

import com.example.dao.ActorCasoDeUsoFacade;
import com.example.dao.ActorFacade;
import com.example.dao.CasoDeUsoFacade;
import com.example.dao.CasosDeUsoRelacionesFacade;
import com.example.dao.DiagramaFacade;
import com.example.dao.FilaFacade;
import com.example.dao.ImageFacade;
import com.example.dao.RelacionFacade;
import com.example.dao.UsuarioTableFacade;
import com.example.entities.Actor;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.CasosDeUsoRelaciones;
import com.example.entities.Diagrama;
import com.example.entities.Fila;
import com.example.entities.Image;
import com.example.entities.Relacion;
import com.example.entities.UsuarioTable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

@Stateless
public class CrearCasosService {

    /**
     * @return the log
     */
    public static Logger getLog() {
        return log;
    }

    /**
     * @param aLog the log to set
     */
    public static void setLog(Logger aLog) {
        log = aLog;
    }

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
    @EJB
    private CasosDeUsoRelacionesFacade cduRelFacade;
        private static Logger log = Logger.getLogger(CrearCasosService.class.getName());

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
    
    public List<CasosDeUsoRelaciones> obtenerCasosDeUsoRelacionesPorDiagramaID(int diagramaID){
        
        return getCduRelFacade().obtenerCduRelsPorDiagramaID(diagramaID);
    }

    public Actor obtenerActorPorNombreYDiagramaId(int diagramaid, String nombre) {

        return getActorFacade().findByNameYDiagramaId(diagramaid, nombre);
    }

    public Actor obtenerActorPorNombre(String nombre) {

        return getActorFacade().findByName(nombre);
    }

    public int getobtenerImagenesCountPorUsuarioID(UsuarioTable usuario) {

        return getImageFacade().obtenerImagenesCountPorUsuarioID(usuario);
    }

    public CasoDeUso obtenerCasoDeUsoPorTextoYDiagramaId(int diagramaId, String cduTexto) {

        return getCduFacade().findByNameAndDiagrama(diagramaId, cduTexto);
    }

    public Boolean actorExiste(int diagramaid, String nombre) {

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
            imagen.setFechaGuardado(new Date());
            getImageFacade().edit(imagen);

        } else {

            imagen = new Image(usuario, diagrama, title, relativeFilename, new Date());
            getImageFacade().create(imagen);
        }

        return imagen;
    }

    public Diagrama guardarDiagrama(UsuarioTable usuarioLogueado) {

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
        Map<Integer, CasosDeUsoRelaciones> viejasCduRel = new HashMap<Integer, CasosDeUsoRelaciones>();
        Map<String, ActorCasoDeUso> actCduPreviosMap = new HashMap<String, ActorCasoDeUso>();
        Map<String, CasosDeUsoRelaciones> cduRelPreviosMap = new HashMap<String, CasosDeUsoRelaciones>();
        Map<Integer, Actor> actPreviosMap = new HashMap<Integer, Actor>();
        Map<Integer, CasoDeUso> cduPreviosMap = new HashMap<Integer, CasoDeUso>();
        Map<Integer, Boolean> actCduFoundMap = new HashMap<Integer, Boolean>();
        Map<Integer, Boolean> actFoundMap = new HashMap<Integer, Boolean>();
        Map<Integer, Boolean> cduFoundMap = new HashMap<Integer, Boolean>();
        Map<Integer, Boolean> cduRelFoundMap = new HashMap<Integer, Boolean>();

        if (diagrama != null && diagrama.getId() != null) {

            List<Fila> viejasFilas = getFilaFacade().obtenerFilasPorDiagramaID(diagrama.getId());

            for (Fila f : viejasFilas) {

                viejasFilasIds.put(f.getId(), false);
                viejasFilaMap.put(f.getId(), f);

                if (f.getActorID() != null) {

                    actPreviosMap.put(f.getActorID().getId(), f.getActorID());
                    actFoundMap.put(f.getActorID().getId(), false);
                }


                if (f.getCasoDeUso1ID() != null) {
                    cduFoundMap.put(f.getCasoDeUso1ID().getId(), false);
                    cduPreviosMap.put(f.getCasoDeUso1ID().getId(), f.getCasoDeUso1ID());
                }
                if (f.getCasoDeUso2ID() != null) {
                    cduFoundMap.put(f.getCasoDeUso2ID().getId(), false);
                    cduPreviosMap.put(f.getCasoDeUso2ID().getId(), f.getCasoDeUso2ID());

                }
                if (f.getCasoDeUso3ID() != null) {
                    cduFoundMap.put(f.getCasoDeUso3ID().getId(), false);
                    cduPreviosMap.put(f.getCasoDeUso3ID().getId(), f.getCasoDeUso3ID());
                }

                if (f.getCasoDeUso4ID() != null) {
                    cduFoundMap.put(f.getCasoDeUso4ID().getId(), false);
                    cduPreviosMap.put(f.getCasoDeUso4ID().getId(), f.getCasoDeUso4ID());
                }
                if (f.getCasoDeUso5ID() != null) {
                    cduFoundMap.put(f.getCasoDeUso5ID().getId(), false);
                    cduPreviosMap.put(f.getCasoDeUso5ID().getId(), f.getCasoDeUso5ID());
                }
            }

            List<ActorCasoDeUso> actCduPrevios = getActorCasoDeUsoFacade().obtenerActorCdusPorDiagramaID(diagrama.getId());

            for (ActorCasoDeUso actCduP : actCduPrevios) {

                actCduPreviosMap.put(actCduP.getActorid().getNombre() + "-" + actCduP.getCasodeusoid().getText(), actCduP);
                viejasActCdu.put(actCduP.getId(), actCduP);
            }
            
            List<CasosDeUsoRelaciones> cduRelPrevios = getCduRelFacade().obtenerCduRelsPorDiagramaID(diagrama.getId());
            
            for(CasosDeUsoRelaciones cduRel: cduRelPrevios){
                
                cduRelPreviosMap.put(cduRel.getCasodeuso1id().getText()+"-"+cduRel.getRelacionid().getNombre()+"-"+cduRel.getCasodeuso2id().getText(), cduRel);
                viejasCduRel.put(cduRel.getId(), cduRel);
            }

        } else {

            diagrama = new Diagrama();
            diagrama.setUsuario(usuario);
            getDiagFacade().create(diagrama);
        }

        for (Fila f : filas) {

            Actor a = null;
            CasoDeUso cdu1 = null;
            CasoDeUso cdu2 = null;
            CasoDeUso cdu3 = null;
            CasoDeUso cdu4 = null;
            CasoDeUso cdu5 = null;
            Relacion rel1 = null;
            Relacion rel2 = null;
            Relacion rel3 = null;
            Relacion rel4 = null;

            if (f.getActorID() != null) {

                if (f.getActorID().getNombre() != null) {

                    a = obtenerActorPorNombreYDiagramaId(diagrama.getId(), f.getActorID().getNombre());
                    
                    if (a == null) {

                        f.getActorID().setDiagramid(diagrama);
                        getActorFacade().create(f.getActorID());

                    } else if (actFoundMap.get(a.getId()) != null) {

                        actFoundMap.put(a.getId(), true);
                    }
                }
            }

            if (f.getCasoDeUso1ID() != null) {

                if (f.getCasoDeUso1ID().getText() != null) {

                    cdu1 = obtenerCasoDeUsoPorTextoYDiagramaId(diagrama.getId(), f.getCasoDeUso1ID().getText());

                    if (cdu1 == null) {

                        f.getCasoDeUso1ID().setDiagramid(diagrama);
                        getCduFacade().create(f.getCasoDeUso1ID());

                    } else if (cduFoundMap.get(cdu1.getId()) != null) {

                        f.setCasoDeUso1ID(cdu1);
                        cduFoundMap.put(cdu1.getId(), true);
                    }
                }
            }

            if (f.getCasoDeUso2ID() != null) {

                if (f.getCasoDeUso2ID().getText() != null) {

                    cdu2 = obtenerCasoDeUsoPorTextoYDiagramaId(diagrama.getId(), f.getCasoDeUso2ID().getText());

                    if (cdu2 == null) {

                        f.getCasoDeUso2ID().setDiagramid(diagrama);
                        getCduFacade().create(f.getCasoDeUso2ID());

                    } else if (cduFoundMap.get(cdu2.getId()) != null) {
                        f.setCasoDeUso2ID(cdu2);
                        cduFoundMap.put(cdu2.getId(), true);
                    }
                }
            }

            if (f.getCasoDeUso3ID() != null) {

                if (f.getCasoDeUso3ID().getText() != null) {

                    cdu3 = obtenerCasoDeUsoPorTextoYDiagramaId(diagrama.getId(), f.getCasoDeUso3ID().getText());

                    if (cdu3 == null) {

                        f.getCasoDeUso3ID().setDiagramid(diagrama);
                        getCduFacade().create(f.getCasoDeUso3ID());

                    } else if (cduFoundMap.get(cdu3.getId()) != null) {

                        f.setCasoDeUso3ID(cdu3);
                        cduFoundMap.put(cdu3.getId(), true);
                    }
                }
            }

            if (f.getCasoDeUso4ID() != null) {

                if (f.getCasoDeUso4ID().getText() != null) {

                    cdu4 = obtenerCasoDeUsoPorTextoYDiagramaId(diagrama.getId(), f.getCasoDeUso4ID().getText());

                    if (cdu4 == null) {

                        f.getCasoDeUso4ID().setDiagramid(diagrama);
                        getCduFacade().create(f.getCasoDeUso4ID());

                    } else if (cduFoundMap.get(cdu4.getId()) != null) {

                        f.setCasoDeUso4ID(cdu4);
                        cduFoundMap.put(cdu4.getId(), true);
                    }
                }
            }

            if (f.getCasoDeUso5ID() != null) {

                if (f.getCasoDeUso5ID().getText() != null) {

                    cdu5 = obtenerCasoDeUsoPorTextoYDiagramaId(diagrama.getId(), f.getCasoDeUso5ID().getText());

                    if (cdu5 == null) {

                        f.getCasoDeUso5ID().setDiagramid(diagrama);
                        getCduFacade().create(f.getCasoDeUso5ID());

                    } else if (cduFoundMap.get(cdu5.getId()) != null) {

                        f.setCasoDeUso5ID(cdu5);
                        cduFoundMap.put(cdu5.getId(), true);
                    }
                }
            }

            if (f.getActorID() != null && f.getCasoDeUso1ID() != null) {

                if (actCduPreviosMap.get(f.getActorID().getNombre() + "-" + f.getCasoDeUso1ID().getText()) == null) {

                    ActorCasoDeUso actCdu = new ActorCasoDeUso();

                    if (a != null) {
                        actCdu.setActorid(a);
                    } else {
                        actCdu.setActorid(f.getActorID());
                    }

                    actCdu.setCasodeusoid(f.getCasoDeUso1ID());
                    actCdu.setDiagramid(diagrama);
                    getActorCasoDeUsoFacade().create(actCdu);

                } else {

                    actCduFoundMap.put(actCduPreviosMap.get(f.getActorID().getNombre() + "-" + f.getCasoDeUso1ID().getText()).getId(), true);

                }
            }

            if (f.getCasoDeUso1ID() != null && f.getCasoDeUso1ID().getText() != null && f.getRelacion1ID() != null && f.getRelacion1ID().getNombre() != null && f.getCasoDeUso2ID() != null && f.getCasoDeUso2ID().getText() != null) {
                
                //las relaciones son INCLUDES y EXTENDS
                rel1 = getRelFacade().obtenerRelacionPorNombre(f.getRelacion1ID().getNombre().toUpperCase());
                                
                if (rel1 != null) {

                    f.setRelacion1ID(rel1);

                    if (cduRelPreviosMap.get(f.getCasoDeUso1ID().getText() + "-" + f.getRelacion1ID().getNombre() + "-" + f.getCasoDeUso2ID().getText()) == null) {

                        CasosDeUsoRelaciones cduRel1 = new CasosDeUsoRelaciones();

                        if (cdu1 != null) {
                            cduRel1.setCasodeuso1id(cdu1);
                        } else {
                            cduRel1.setCasodeuso1id(f.getCasoDeUso1ID());
                        }

                        if (cdu2 != null) {
                            cduRel1.setCasodeuso2id(cdu2);
                        } else {
                            cduRel1.setCasodeuso2id(f.getCasoDeUso2ID());
                        }

                        cduRel1.setDiagramid(diagrama);
                        cduRel1.setRelacionid(rel1);
                        getCduRelFacade().create(cduRel1);
                    
                    } else {
                       
                        cduRelFoundMap.put(cduRelPreviosMap.get(f.getCasoDeUso1ID().getText() + "-" + f.getRelacion1ID().getNombre() + "-" + f.getCasoDeUso2ID().getText()).getId(), true);
                    }
                }else{
                    f.setRelacion1ID(null);
                }
            }
            
            if (f.getCasoDeUso2ID() != null && f.getCasoDeUso2ID().getText() != null && f.getRelacion2ID() != null && f.getRelacion2ID().getNombre() != null && f.getCasoDeUso3ID() != null && f.getCasoDeUso3ID().getText() != null) {

                rel2 = getRelFacade().obtenerRelacionPorNombre(f.getRelacion2ID().getNombre());

                if (rel2 != null) {
                    
                    f.setRelacion2ID(rel2);

                    if (cduRelPreviosMap.get(f.getCasoDeUso2ID().getText() + "-" + f.getRelacion2ID().getNombre() + "-" + f.getCasoDeUso3ID().getText()) == null) {

                        CasosDeUsoRelaciones cduRel2 = new CasosDeUsoRelaciones();

                        if (cdu2 != null) {
                            cduRel2.setCasodeuso1id(cdu2);
                        } else {
                            cduRel2.setCasodeuso1id(f.getCasoDeUso2ID());
                        }

                        if (cdu3 != null) {
                            cduRel2.setCasodeuso2id(cdu3);
                        } else {
                            cduRel2.setCasodeuso2id(f.getCasoDeUso3ID());
                        }

                        cduRel2.setDiagramid(diagrama);
                        cduRel2.setRelacionid(rel2);
                        getCduRelFacade().create(cduRel2);
                    
                    } else {
                       
                        cduRelFoundMap.put(cduRelPreviosMap.get(f.getCasoDeUso2ID().getText() + "-" + f.getRelacion2ID().getNombre() + "-" + f.getCasoDeUso3ID().getText()).getId(), true);
                    }
                }
            }
            
            if (f.getCasoDeUso3ID() != null && f.getCasoDeUso3ID().getText() != null && f.getRelacion3ID() != null && f.getRelacion3ID().getNombre() != null && f.getCasoDeUso4ID() != null && f.getCasoDeUso4ID().getText() != null) {

                rel3 = getRelFacade().obtenerRelacionPorNombre(f.getRelacion3ID().getNombre());

                if (rel3 != null) {

                    f.setRelacion3ID(rel3);

                    if (cduRelPreviosMap.get(f.getCasoDeUso3ID().getText() + "-" + f.getRelacion3ID().getNombre() + "-" + f.getCasoDeUso4ID().getText()) == null) {

                        CasosDeUsoRelaciones cduRel3 = new CasosDeUsoRelaciones();

                        if (cdu3 != null) {
                            cduRel3.setCasodeuso1id(cdu3);
                        } else {
                            cduRel3.setCasodeuso1id(f.getCasoDeUso3ID());
                        }

                        if (cdu4 != null) {
                            cduRel3.setCasodeuso2id(cdu4);
                        } else {
                            cduRel3.setCasodeuso2id(f.getCasoDeUso4ID());
                        }

                        cduRel3.setDiagramid(diagrama);
                        cduRel3.setRelacionid(rel3);
                        getCduRelFacade().create(cduRel3);
                    
                    } else {
                       
                        cduRelFoundMap.put(cduRelPreviosMap.get(f.getCasoDeUso3ID().getText() + "-" + f.getRelacion3ID().getNombre() + "-" + f.getCasoDeUso4ID().getText()).getId(), true);
                    }
                }
            }
            
            if (f.getCasoDeUso4ID() != null && f.getCasoDeUso4ID().getText() != null && f.getRelacion4ID() != null && f.getRelacion4ID().getNombre() != null && f.getCasoDeUso5ID() != null && f.getCasoDeUso5ID().getText() != null) {

                rel4 = getRelFacade().obtenerRelacionPorNombre(f.getRelacion4ID().getNombre());

                if (rel4 != null) {

                    f.setRelacion4ID(rel4);                    
                    
                    if (cduRelPreviosMap.get(f.getCasoDeUso4ID().getText() + "-" + f.getRelacion4ID().getNombre() + "-" + f.getCasoDeUso5ID().getText()) == null) {

                        CasosDeUsoRelaciones cduRel4 = new CasosDeUsoRelaciones();

                        if (cdu4 != null) {
                            cduRel4.setCasodeuso1id(cdu4);
                        } else {
                            cduRel4.setCasodeuso1id(f.getCasoDeUso4ID());
                        }

                        if (cdu5 != null) {
                            cduRel4.setCasodeuso2id(cdu5);
                        } else {
                            cduRel4.setCasodeuso2id(f.getCasoDeUso5ID());
                        }

                        cduRel4.setDiagramid(diagrama);
                        cduRel4.setRelacionid(rel4);
                        getCduRelFacade().create(cduRel4);
                    
                    } else {
                       
                        cduRelFoundMap.put(cduRelPreviosMap.get(f.getCasoDeUso4ID().getText() + "-" + f.getRelacion4ID().getNombre() + "-" + f.getCasoDeUso5ID().getText()).getId(), true);
                    }
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

        for (Integer i : viejasFilaMap.keySet()) {

            if (!viejasFilasIds.get(i)) {

                getFilaFacade().remove(viejasFilaMap.get(i));
            }
        }

        for (String i : actCduPreviosMap.keySet()) {
            
            Integer j = actCduPreviosMap.get(i).getId();
            
            if (!actCduFoundMap.containsKey(j) || !actCduFoundMap.get(j)) {

                getActorCasoDeUsoFacade().remove(viejasActCdu.get(j));
            }
        }
        
        for(String i: cduRelPreviosMap.keySet()){
            
            Integer j = cduRelPreviosMap.get(i).getId();
            
            if(!cduRelFoundMap.containsKey(j) || !cduRelFoundMap.get(j)){
                getCduRelFacade().remove(viejasCduRel.get(j));
            }
        }

        //borrar objetos que no existen mas
        for (Integer i : actFoundMap.keySet()) {

            if (!actFoundMap.get(i)) {

                getActorFacade().remove(actPreviosMap.get(i));
            }
        }

        for (Integer i : cduPreviosMap.keySet()) {

            if (!cduFoundMap.get(i)) {

                getCduFacade().remove(cduPreviosMap.get(i));
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

    /**
     * @return the cduRelFacade
     */
    public CasosDeUsoRelacionesFacade getCduRelFacade() {
        return cduRelFacade;
    }

    /**
     * @param cduRelFacade the cduRelFacade to set
     */
    public void setCduRelFacade(CasosDeUsoRelacionesFacade cduRelFacade) {
        this.cduRelFacade = cduRelFacade;
    }
}
