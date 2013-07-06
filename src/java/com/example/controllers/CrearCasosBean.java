/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.dao.ActorCasoDeUsoFacade;
import com.example.dao.ActorFacade;
import com.example.dao.CasoDeUsoFacade;
import com.example.dao.RelacionFacade;
import com.example.entities.Actor;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.Relacion;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author George
 */
@ManagedBean(name = "crearCasosBean")
@ViewScoped
public class CrearCasosBean {

    private ArrayList<CasoDeUsoRow> casoRow;
    private ArrayList<Actor> actores;
    private ArrayList<Relacion> relaciones;
    private ArrayList<CasoDeUso> cdus;
    private Actor actorActual;
    private String nombreNuevoActor;
    private CasoDeUso cduActual;
    private String nombreNuevoCdu;
    private String relacion;
    private int thexPositionSelected;
    private int theyPositionSelected;
    @EJB
    private ActorFacade actorFacade;
    @EJB 
    private ActorCasoDeUsoFacade actorCasoDeUsoFacade;
    @EJB
    private CasoDeUsoFacade cduFacade;
    @EJB
    private RelacionFacade relFacade;

    /**
     * Creates a new instance of CrearCasosBean
     */
    public CrearCasosBean() {

        casoRow = new ArrayList<CasoDeUsoRow>();

        //if casoRow is null
        CasoDeUsoRow cduRow = new CasoDeUsoRow();
        casoRow.add(cduRow);
    }

    public String addRow() {

        CasoDeUsoRow newCduRow = new CasoDeUsoRow();
        getCasoRow().add(newCduRow);
        return null;
    }

    @PostConstruct
    public void cargarValores() {

        //TODO filtrar por user id
        actores = new ArrayList<Actor>(getActorFacade().findAll());
        relaciones = new ArrayList<Relacion>(getRelFacade().findAll());
        cdus = new ArrayList<CasoDeUso>(getCduFacade().findAll());
    }

    /**
     * @return the casoRow
     */
    public ArrayList<CasoDeUsoRow> getCasoRow() {
        return casoRow;
    }

    /**
     * @param casoRow the casoRow to set
     */
    public void setCasoRow(ArrayList<CasoDeUsoRow> casoRow) {
        this.setCasoRow(casoRow);
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

    /**
     * @return the actores
     */
    public ArrayList<Actor> getActores() {
        return actores;
    }

    /**
     * @param actores the actores to set
     */
    public void setActores(ArrayList<Actor> actores) {
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
    public ArrayList<Relacion> getRelaciones() {
        return relaciones;
    }

    /**
     * @param relaciones the relaciones to set
     */
    public void setRelaciones(ArrayList<Relacion> relaciones) {
        this.relaciones = relaciones;
    }

    /**
     * @return the cdus
     */
    public ArrayList<CasoDeUso> getCdus() {
        return cdus;
    }

    /**
     * @param cdus the cdus to set
     */
    public void setCdus(ArrayList<CasoDeUso> cdus) {
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

    public void guardar() {

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
               
            if(actoresAInsertar.size() > 0){
             
                for(Actor actor: actoresAInsertar){
                    getActorFacade().create(actor);
                }
            }
            
            if(cdusAInsertar.size() > 0){
                
                for(CasoDeUso cas: cdusAInsertar){
                    getCduFacade().create(cas);
                }
            }
            
            if(a != null && cdu1 != null){
                
                ActorCasoDeUso actCdu = new ActorCasoDeUso();
                actCdu.setActorid(a.getId());
                actCdu.setCasodeusoid(cdu1);
                getActorCasoDeUsoFacade().create(actCdu);
            }
        }
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
     * @return the actorCasoDeUsoFacade
     */
    public ActorCasoDeUsoFacade getActorCasoDeUsoFacade() {
        return actorCasoDeUsoFacade;
    }

    /**
     * @param actorCasoDeUsoFacade the actorCasoDeUsoFacade to set
     */
    public void setActorCasoDeUsoFacade(ActorCasoDeUsoFacade actorCasoDeUsoFacade) {
        this.actorCasoDeUsoFacade = actorCasoDeUsoFacade;
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
