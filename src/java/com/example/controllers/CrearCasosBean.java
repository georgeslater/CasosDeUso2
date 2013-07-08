/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.controllers.exceptions.UserNotRecognizedException;
import com.example.entities.Actor;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.Fila;
import com.example.entities.Relacion;
import com.example.entities.UsuarioTable;
import com.example.negocio.CrearCasosService;
import com.example.negocio.DibujarService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author George
 */
@Named("crearCasosBean")
@ConversationScoped
public class CrearCasosBean implements Serializable {

    private static final long serialVersionUID = 4771270804699990999L;
    @Inject
    private Conversation conversation;
    private List<CasoDeUsoRow> casoRow;
    private List<Actor> actores;
    private List<Relacion> relaciones;
    private List<CasoDeUso> cdus;
    private List<ActorCasoDeUso> actCdu;
    private List<Fila> filas;
    private String diagramaID;
    private UsuarioTable usuarioLogueado;
    private Actor actorActual;
    private String nombreNuevoActor;
    private CasoDeUso cduActual;
    private String nombreNuevoCdu;
    private String relacion;
    private int thexPositionSelected;
    private int theyPositionSelected;
    private String json;
    @EJB
    private CrearCasosService crearCasosService;
    @EJB
    private DibujarService dibujarService;

    /**
     * Creates a new instance of CrearCasosBean
     */
    public CrearCasosBean() {

        casoRow = new ArrayList<CasoDeUsoRow>();
    }

    public String addRow() {

        CasoDeUsoRow newCduRow = new CasoDeUsoRow();
        getCasoRow().add(newCduRow);
        return null;
    }

    public void cargarValores() {

        if (!FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest() && !FacesContext.getCurrentInstance().isPostback()
                && conversation.isTransient()) {

            conversation.begin();

            usuarioLogueado = getCrearCasosService().getUsuarioLogueado();

            if (usuarioLogueado != null && usuarioLogueado.getIduser() != null) {

                if (diagramaID != null) {

                    try {

                        int diagInt = Integer.parseInt(diagramaID);

                        filas = crearCasosService.getFilasPrecargadas(diagInt);

                    } catch (NumberFormatException e) {
                    }

                    if (filas.size() > 0) {

                        generarFilas(filas);

                    } else {

                        //si no hay filas
                        CasoDeUsoRow cduRow = new CasoDeUsoRow();
                        casoRow.add(cduRow);
                    }

                } else {
                    //if casoRow is nullsfefwedgdsgdsfs
                    CasoDeUsoRow cduRow = new CasoDeUsoRow();
                    casoRow.add(cduRow);
                }

            } else {

                UserNotRecognizedException e = new UserNotRecognizedException("Ha producido un error de autentificacion.");
                System.exit(1);
            }
        }
    }

    public void generarFilas(List<Fila> filas) {

        for (Fila f : filas) {

            CasoDeUsoRow cduRow = new CasoDeUsoRow(f.getActorID(), f.getCasoDeUso1ID(), f.getRelacion1ID(), f.getCasoDeUso2ID(), f.getRelacion2ID(), f.getCasoDeUso3ID(), f.getRelacion3ID(), f.getCasoDeUso4ID(), f.getRelacion4ID(), f.getCasoDeUso5ID());
            casoRow.add(cduRow);
        }
    }

    public void evaluarDialogsEliminar() {

        CasoDeUsoRow selectedCduRow = casoRow.get(thexPositionSelected);
        
        switch(theyPositionSelected){
        
        case 1:           
            selectedCduRow.setAct(null);
            break;
        
        case 2: 
            selectedCduRow.setCdu1(null);
            break;
            
        case 3:
            selectedCduRow.setRel1(null);
            break;
        
        case 4:
            selectedCduRow.setCdu2(null);
            break;
        
        case 5:
            selectedCduRow.setRel2(null);
            break;
            
        case 6:
            selectedCduRow.setCdu3(null);
            break;
        
        case 7:
            selectedCduRow.setRel3(null);
            break;
            
        case 8: 
            selectedCduRow.setCdu4(null);
            break;
            
        case 9:
            selectedCduRow.setRel4(null);
            break;
        
        case 10:
            selectedCduRow.setCdu5(null);
            break;
        }
    }

    public void agregarActor() {

        CasoDeUsoRow selectedCduRow = casoRow.get(thexPositionSelected);

        Actor act = null;

        if (actorActual != null) {

            act = actorActual;

        } else if (nombreNuevoActor != null) {

            //TODO: Fijamos si no existe en la base

            act = new Actor();
            act.setNombre(nombreNuevoActor);
            actores.add(act);
        }

        if (act != null) {

            selectedCduRow.setAct(act);
        }

        nombreNuevoActor = null;
    }

    public void agregarCdu() {

        CasoDeUsoRow selectedCduRow = casoRow.get(thexPositionSelected);

        CasoDeUso cdu = null;

        if (cduActual != null) {

            cdu = cduActual;

        } else if (nombreNuevoCdu != null) {

            //TODO: Fijamos si no existe en la base

            cdu = new CasoDeUso();
            cdu.setText(nombreNuevoCdu);
            cdus.add(cdu);
        }

        if (cdu != null) {

            if (theyPositionSelected == 2) {

                selectedCduRow.setCdu1(cdu);

            } else if (theyPositionSelected == 4) {

                selectedCduRow.setCdu2(cdu);

            } else if (theyPositionSelected == 6) {

                selectedCduRow.setCdu3(cdu);

            } else if (theyPositionSelected == 8) {

                selectedCduRow.setCdu4(cdu);

            } else if (theyPositionSelected == 10) {

                selectedCduRow.setCdu5(cdu);
            }
        }

        nombreNuevoActor = null;
    }

    public void generarDiagrama() {

        actores = null;
        cdus = null;
        actCdu = null;
        json = null;

        int diagramaidInt = Integer.parseInt(diagramaID);
        actores = crearCasosService.obtenerActoresPorDiagramaID(diagramaidInt);
        cdus = crearCasosService.obtenerCasosDeUsoPorDiagramaID(diagramaidInt);
        actCdu = crearCasosService.obtenerActorCasoDeUsosPorDiagramaID(diagramaidInt);

        Object[][] diagrama = dibujarService.generarDiagrama(actores, actCdu, cdus, null);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        json = gson.toJson(diagrama);
    }

    public void guardar() {
        //TODO validar!
        /*
         ArrayList<Actor> actoresAInsertar = new ArrayList<Actor>();
         ArrayList<CasoDeUso> cdusAInsertar = new ArrayList<CasoDeUso>();
         ArrayList<ActorCasoDeUso> actcduAInsertar = new ArrayList<ActorCasoDeUso>();

         for (CasoDeUsoRow cdur : casoRow) {

         Actor a = null;
         CasoDeUso cdu1 = null;
         Relacion rel1;
         CasoDeUso cdu2;
         Relacion rel2;
         CasoDeUso cdu3;
         Relacion rel3;
         CasoDeUso cdu4;
         Relacion rel4;
         CasoDeUso cdu5;

         if (cdur.act != null) {

         a = new Actor();
         a.setNombre(cdur.act.getNombre());
         actoresAInsertar.add(a);
         }

         if (cdur.getCdu1() != null) {

         cdu1 = new CasoDeUso();
         cdu1.setText(cdur.getCdu1().getText());
         cdusAInsertar.add(cdu1);

         }

         if (actoresAInsertar.size() > 0) {

         for (Actor actor : actoresAInsertar) {
         getActorFacade().create(actor);
         }
         }

         if (cdusAInsertar.size() > 0) {

         for (CasoDeUso cas : cdusAInsertar) {
         getCduFacade().create(cas);
         }
         }

         if (a != null && cdu1 != null) {

         ActorCasoDeUso actCdu = new ActorCasoDeUso();
         actCdu.setActorid(a);
         actCdu.setCasodeusoid(cdu1);
         getActorCasoDeUsoFacade().create(actCdu);
         }
         }
         * */
    }

    public void agregarRelacion() {

        if (relacion != null) {

            CasoDeUsoRow selectedCduRow = casoRow.get(thexPositionSelected);

            Relacion rel = new Relacion();
            rel.setNombre(relacion);

            if (theyPositionSelected == 3) {

                selectedCduRow.setRel1(rel);

            } else if (theyPositionSelected == 5) {

                selectedCduRow.setRel2(rel);

            } else if (theyPositionSelected == 7) {

                selectedCduRow.setRel3(rel);

            } else if (theyPositionSelected == 9) {

                selectedCduRow.setRel4(rel);
            }
        }

        relacion = null;
    }

    /**
     * @param theyPositionSelected the yPositionSelected to set
     */
    public void setTheyPositionSelected(int yPositionSelected) {
        this.theyPositionSelected = yPositionSelected;
    }

    /**
     * @return the cduActual
     */
    public CasoDeUso getCduActual() {
        return cduActual;
    }

    /**
     * @param cduActual the cduActual to set
     */
    public void setCduActual(CasoDeUso cduActual) {
        this.cduActual = cduActual;
    }

    /**
     * @return the nombreNuevoCdu
     */
    public String getNombreNuevoCdu() {
        return nombreNuevoCdu;
    }

    /**
     * @param nombreNuevoCdu the nombreNuevoCdu to set
     */
    public void setNombreNuevoCdu(String nombreNuevoCdu) {
        this.nombreNuevoCdu = nombreNuevoCdu;
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

    /**
     * @return the actCdu
     */
    public List<ActorCasoDeUso> getActCdu() {
        return actCdu;
    }

    /**
     * @param actCdu the actCdu to set
     */
    public void setActCdu(List<ActorCasoDeUso> actCdu) {
        this.actCdu = actCdu;
    }

    /**
     * @return the filas
     */
    public List<Fila> getFilas() {
        return filas;
    }

    /**
     * @param filas the filas to set
     */
    public void setFilas(List<Fila> filas) {
        this.filas = filas;
    }

    /**
     * @return the usuarioLogueado
     */
    public UsuarioTable getUsuarioLogueado() {
        return usuarioLogueado;
    }

    /**
     * @param usuarioLogueado the usuarioLogueado to set
     */
    public void setUsuarioLogueado(UsuarioTable usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    /**
     * @return the diagramaID
     */
    public String getDiagramaID() {
        return diagramaID;
    }

    /**
     * @param diagramaID the diagramaID to set
     */
    public void setDiagramaID(String diagramaID) {
        this.diagramaID = diagramaID;
    }

    /**
     * @return the crearCasosService
     */
    public CrearCasosService getCrearCasosService() {
        return crearCasosService;
    }

    /**
     * @param crearCasosService the crearCasosService to set
     */
    public void setCrearCasosService(CrearCasosService crearCasosService) {
        this.crearCasosService = crearCasosService;
    }

    /**
     * @return the json
     */
    public String getJson() {
        return json;
    }

    /**
     * @param json the json to set
     */
    public void setJson(String json) {
        this.json = json;
    }

    /**
     * @return the dibujarService
     */
    public DibujarService getDibujarService() {
        return dibujarService;
    }

    /**
     * @param dibujarService the dibujarService to set
     */
    public void setDibujarService(DibujarService dibujarService) {
        this.dibujarService = dibujarService;
    }
    
    
    /**
     * @return the casoRow
     */
    public List<CasoDeUsoRow> getCasoRow() {
        return casoRow;
    }

    /**
     * @param casoRow the casoRow to set
     */
    public void setCasoRow(List<CasoDeUsoRow> casoRow) {
        this.setCasoRow(casoRow);
    }

    /**
     * @return the actores
     */
    public List<Actor> getActores() {
        return actores;
    }

    /**
     * @param actores the actores to set
     */
    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }

    /**
     * @return the actorActual
     */
    public Actor getActorActual() {
        return actorActual;
    }

    /**
     * @param actorActual the actorActual to set
     */
    public void setActorActual(Actor actorActual) {
        this.actorActual = actorActual;
    }

    /**
     * @return the nombreNuevoActor
     */
    public String getNombreNuevoActor() {
        return nombreNuevoActor;
    }

    /**
     * @param nombreNuevoActor the nombreNuevoActor to set
     */
    public void setNombreNuevoActor(String nombreNuevoActor) {
        this.nombreNuevoActor = nombreNuevoActor;
    }

    /**
     * @return the relaciones
     */
    public List<Relacion> getRelaciones() {
        return relaciones;
    }

    /**
     * @param relaciones the relaciones to set
     */
    public void setRelaciones(List<Relacion> relaciones) {
        this.relaciones = relaciones;
    }

    /**
     * @return the cdus
     */
    public List<CasoDeUso> getCdus() {
        return cdus;
    }

    /**
     * @param cdus the cdus to set
     */
    public void setCdus(List<CasoDeUso> cdus) {
        this.cdus = cdus;
    }

    /**
     * @return the xPositionSelected
     */
    public int getThexPositionSelected() {
        return thexPositionSelected;
    }

    /**
     * @param xPositionSelected the xPositionSelected to set
     */
    public void setThexPositionSelected(int xPositionSelected) {
        this.thexPositionSelected = xPositionSelected;
    }

    /**
     * @return the yPositionSelected
     */
    public int getTheyPositionSelected() {
        return theyPositionSelected;
    }

    public class CasoDeUsoRow {

        private Actor act;
        private CasoDeUso cdu1;
        private Relacion rel1;
        private CasoDeUso cdu2;
        private Relacion rel2;
        private CasoDeUso cdu3;
        private Relacion rel3;
        private CasoDeUso cdu4;
        private Relacion rel4;
        private CasoDeUso cdu5;

        public CasoDeUsoRow() {

            act = null;
            cdu1 = null;
            rel1 = null;
            cdu2 = null;
            rel2 = null;
            cdu3 = null;
            rel3 = null;
            cdu4 = null;
            rel4 = null;
            cdu5 = null;
        }

        public CasoDeUsoRow(Actor act, CasoDeUso cdu1, Relacion rel1, CasoDeUso cdu2, Relacion rel2, CasoDeUso cdu3, Relacion rel3, CasoDeUso cdu4, Relacion rel4, CasoDeUso cdu5) {

            this.act = act;
            this.cdu1 = cdu1;
            this.rel1 = rel1;
            this.cdu2 = cdu2;
            this.rel2 = rel2;
            this.cdu3 = cdu3;
            this.rel3 = rel3;
            this.cdu4 = cdu4;
            this.rel4 = rel4;
            this.cdu5 = cdu5;
        }

        /**
         * @return the act
         */
        public Actor getAct() {
            return act;
        }

        /**
         * @param act the act to set
         */
        public void setAct(Actor act) {
            this.act = act;
        }

        /**
         * @return the cdu1
         */
        public CasoDeUso getCdu1() {
            return cdu1;
        }

        /**
         * @param cdu1 the cdu1 to set
         */
        public void setCdu1(CasoDeUso cdu1) {
            this.cdu1 = cdu1;
        }

        /**
         * @return the rel1
         */
        public Relacion getRel1() {
            return rel1;
        }

        /**
         * @param rel1 the rel1 to set
         */
        public void setRel1(Relacion rel1) {
            this.rel1 = rel1;
        }

        /**
         * @return the cdu2
         */
        public CasoDeUso getCdu2() {
            return cdu2;
        }

        /**
         * @param cdu2 the cdu2 to set
         */
        public void setCdu2(CasoDeUso cdu2) {
            this.cdu2 = cdu2;
        }

        /**
         * @return the rel2
         */
        public Relacion getRel2() {
            return rel2;
        }

        /**
         * @param rel2 the rel2 to set
         */
        public void setRel2(Relacion rel2) {
            this.rel2 = rel2;
        }

        /**
         * @return the cdu3
         */
        public CasoDeUso getCdu3() {
            return cdu3;
        }

        /**
         * @param cdu3 the cdu3 to set
         */
        public void setCdu3(CasoDeUso cdu3) {
            this.cdu3 = cdu3;
        }

        /**
         * @return the rel3
         */
        public Relacion getRel3() {
            return rel3;
        }

        /**
         * @param rel3 the rel3 to set
         */
        public void setRel3(Relacion rel3) {
            this.rel3 = rel3;
        }

        /**
         * @return the cdu4
         */
        public CasoDeUso getCdu4() {
            return cdu4;
        }

        /**
         * @param cdu4 the cdu4 to set
         */
        public void setCdu4(CasoDeUso cdu4) {
            this.cdu4 = cdu4;
        }

        /**
         * @return the rel4
         */
        public Relacion getRel4() {
            return rel4;
        }

        /**
         * @param rel4 the rel4 to set
         */
        public void setRel4(Relacion rel4) {
            this.rel4 = rel4;
        }

        /**
         * @return the cdu5
         */
        public CasoDeUso getCdu5() {
            return cdu5;
        }

        /**
         * @param cdu5 the cdu5 to set
         */
        public void setCdu5(CasoDeUso cdu5) {
            this.cdu5 = cdu5;
        }
    }
}
