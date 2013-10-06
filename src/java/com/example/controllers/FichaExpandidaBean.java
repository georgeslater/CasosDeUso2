/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.controllers.util.Messages;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.CasosDeUsoRelaciones;
import com.example.entities.FeEncabezado;
import com.example.entities.FeFlujoalternativo;
import com.example.entities.FeFlujoalternativopaso;
import com.example.entities.FeFlujonormal;
import com.example.negocio.FichaExpandidaService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author George
 */
@Named(value = "fichaExpandidaBean")
@ConversationScoped
public class FichaExpandidaBean implements Serializable {

    @Inject
    private Conversation conversation;
    private FeEncabezado encabezado;
    private int cduId;
    private CasoDeUso cduActual;
    @EJB
    private FichaExpandidaService feService;
    private String actores;
    private String extiendeA;
    private String incluyeA;
    private String puntosDeInclusion;
    private String puntosDeExtension;
    private List<ActorCasoDeUso> actoresRelacionadosACdu;
    private List<CasosDeUsoRelaciones> cduRelRelacionadosACdu;
    private List<FeFlujonormal> feFlujoNormalList;
    private List<FeFlujoalternativo> feFlujoAlternativoList;
    private List<FeFlujoalternativopaso> feFlujoAlternativoPasoList;
    private Map<Integer, List<FeFlujoalternativo>> fnFaMap;
    private Map<Integer, List<FeFlujoalternativopaso>> faFapMap;
    private UIData fapTable;

    public FichaExpandidaBean() {

        actoresRelacionadosACdu = new ArrayList<ActorCasoDeUso>();
        cduRelRelacionadosACdu = new ArrayList<CasosDeUsoRelaciones>();
        feFlujoNormalList = new ArrayList<FeFlujonormal>();
        feFlujoAlternativoList = new ArrayList<FeFlujoalternativo>();
        feFlujoAlternativoPasoList = new ArrayList<FeFlujoalternativopaso>();
        fnFaMap = new HashMap<Integer, List<FeFlujoalternativo>>();
        faFapMap = new HashMap<Integer, List<FeFlujoalternativopaso>>();
    }

    @PostConstruct
    public void init() {

        conversation.begin();

        cduId = 0;

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cduId") != null) {
            cduId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cduId"));
        }

        if (cduId != 0) {

            cduActual = feService.obtenerCasoDeUsoPorId(cduId);
            if (getCduActual() != null) {

                encabezado = feService.obtenerFeEncabezadoPorCdu(cduActual);

                if (encabezado == null) {

                    encabezado = new FeEncabezado();
                    encabezado.setCasoDeUso(cduActual);
                    try {
                        feService.guardarEncabezado(encabezado, true);
                    } catch (EJBTransactionRolledbackException e) {

                        Throwable t = e.getCause();
                        while (!(t instanceof ConstraintViolationException)) {
                            t = t.getCause();
                        }
                        if (t instanceof ConstraintViolationException) {
                            for (ConstraintViolation cv : ((ConstraintViolationException) t).getConstraintViolations()) {

                                String s = cv.getMessage();
                            }
                        }
                    }
                } else {

                    feFlujoNormalList = feService.obtenerFlujoNormalPasosPorEncabezado(encabezado);

                    if (!feFlujoNormalList.isEmpty()) {

                        if (!feFlujoAlternativoList.isEmpty()) {
                        }
                    }
                }

                setActoresRelacionadosACdu(feService.obtenerFeActoresPorCdu(cduActual));

                if (getActoresRelacionadosACdu() != null && !actoresRelacionadosACdu.isEmpty()) {

                    Set<Integer> actorIds = new HashSet<Integer>();

                    actores = "";

                    for (ActorCasoDeUso feA : getActoresRelacionadosACdu()) {

                        if (!actorIds.contains(feA.getActorid().getId())) {
                            actores += feA.getActorid().getNombre() + ", ";
                            actorIds.add(feA.getActorid().getId());
                        }
                    }

                    actores = actores.replaceAll(", $", "");
                }

                cduRelRelacionadosACdu = feService.obtenerCduRelacionesPorCdu(cduActual);

                if (cduRelRelacionadosACdu != null && !cduRelRelacionadosACdu.isEmpty()) {

                    extiendeA = "";
                    incluyeA = "";
                    puntosDeExtension = "";
                    puntosDeInclusion = "";

                    for (CasosDeUsoRelaciones cduRel : cduRelRelacionadosACdu) {

                        if (cduRel.getCasodeuso1id().equals(cduActual)) {

                            if (cduRel.getRelacionid().getNombre().equals("EXTENDS")) {

                                extiendeA += cduRel.getCasodeuso2id().getText() + ", ";

                            } else if (cduRel.getRelacionid().getNombre().equals("EXTENDS")) {

                                incluyeA += cduRel.getCasodeuso2id().getText() + ", ";
                            }
                        }

                        if (cduRel.getCasodeuso2id().equals(cduActual)) {

                            if (cduRel.getRelacionid().getNombre().equals("EXTENDS")) {

                                puntosDeExtension += cduRel.getCasodeuso1id().getText() + ", ";

                            } else if (cduRel.getRelacionid().getNombre().equals("EXTENDS")) {

                                puntosDeInclusion += cduRel.getCasodeuso1id().getText() + ", ";
                            }
                        }
                    }

                    extiendeA = extiendeA.replaceAll(", $", "");
                    incluyeA = incluyeA.replaceAll(", $", "");
                    puntosDeExtension = puntosDeExtension.replaceAll(", $", "");
                    puntosDeInclusion = puntosDeInclusion.replaceAll(", $", "");
                }


            } else {

                Messages.addFatal("No existe un caso de uso con este ID.");

            }
        }
    }

    public void guardarFichaExpandida() {

        boolean esExito = feService.guardarEncabezado(encabezado, false);
        boolean esExitoFN = feService.guardarFeFlujoNormal(feFlujoNormalList);

        if (!esExito || !esExitoFN) {

            Messages.addFatal("Se ha producido un error.  Intente mas tarde.");

        } else {

            Messages.addInfo("Sus datos han sido guardados.");
        }
    }

    public String agregarFilaFlujoAlternativoPaso() {
        
        int row = fapTable.getRowIndex();
        FeFlujoalternativopaso fap = feFlujoAlternativoPasoList.get(row);

        FeFlujoalternativopaso newFap = new FeFlujoalternativopaso();
        newFap.setOrden(row + 2);
        newFap.setFEFlujoAlternativoID(fap.getFEFlujoAlternativoID());
        getFeService().crearFlujoAlternativoPaso(newFap);
        
        feFlujoAlternativoPasoList.add(newFap);
        Collections.sort(feFlujoAlternativoPasoList);
        
        for (int i = row + 2; i < feFlujoAlternativoPasoList.size(); i++) {
                        
            FeFlujoalternativopaso fapTemp = feFlujoAlternativoPasoList.get(i);
            
            //No debemos tocar el orden de 3.2.4 si se agrega un paso al 3.1.1
            if(fap.getFEFlujoAlternativoID().getId() != fapTemp.getFEFlujoAlternativoID().getId()){
                
                break; 
            }
            
            fapTemp.setOrden(fapTemp.getOrden() + 1);
            getFeService().editarFlujoAlternativoPaso(fapTemp);
        }

        return null;
    }

    public String agregarFilaFlujoAlternativo(int row) {

        if (!feFlujoNormalList.isEmpty() && feFlujoNormalList.get(row) != null) {

            FeFlujonormal fn = feFlujoNormalList.get(row);

            FeFlujoalternativo fa = new FeFlujoalternativo();
            fa.setFEFlujoNormalID(fn);

            FeFlujoalternativopaso fap = new FeFlujoalternativopaso();
            fap.setFEFlujoAlternativoID(fa);

            if (fnFaMap != null && fnFaMap.get(fn.getId()) != null && fnFaMap.get(fn.getId()).size() > 0) {

                fa.setOrden(fnFaMap.get(fn.getId()).size() + 1);

                if (faFapMap != null && faFapMap.get(fa.getId()) != null && faFapMap.get(fa.getId()).size() > 0) {

                    fap.setOrden(faFapMap.get(fa.getId()).size() + 1);

                } else {

                    fap.setOrden(1);
                }

            } else {

                fa.setOrden(1);
                fap.setOrden(1);
            }

            getFeService().crearFlujoAlternativo(fa);
            feFlujoAlternativoList.add(fa);

            if (!fnFaMap.containsKey(fn.getId())) {

                fnFaMap.put(fn.getId(), new ArrayList<FeFlujoalternativo>());
            }

            fnFaMap.get(fn.getId()).add(fa);

            fap.setOrden(1);

            getFeService().crearFlujoAlternativoPaso(fap);

            feFlujoAlternativoPasoList.add(fap);

            if (!faFapMap.containsKey(fa.getId())) {

                faFapMap.put(fa.getId(), new ArrayList<FeFlujoalternativopaso>());
            }

            faFapMap.get(fa.getId()).add(fap);

            Collections.sort(feFlujoAlternativoPasoList);
        }

        return null;
    }

    public String agregarFilaFlujoNormal(int row) {

        FeFlujonormal fn = new FeFlujonormal();
        fn.setOrden(row + 2);
        feFlujoNormalList.add(row + 1, fn);

        for (int i = row + 2; i < feFlujoNormalList.size(); i++) {

            FeFlujonormal feTemp = feFlujoNormalList.get(i);
            feTemp.setOrden(feTemp.getOrden() + 1);
        }

        return null;
    }

    /**
     * @return the cduId
     */
    public int getCduId() {
        return cduId;
    }

    /**
     * @param cduId the cduId to set
     */
    public void setCduId(int cduId) {
        this.cduId = cduId;
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
     * @return the feService
     */
    public FichaExpandidaService getFeService() {
        return feService;
    }

    /**
     * @param feService the feService to set
     */
    public void setFeService(FichaExpandidaService feService) {
        this.feService = feService;
    }

    /**
     * @return the encabezado
     */
    public FeEncabezado getEncabezado() {
        return encabezado;
    }

    /**
     * @param encabezado the encabezado to set
     */
    public void setEncabezado(FeEncabezado encabezado) {
        this.encabezado = encabezado;
    }

    /**
     * @return the actores
     */
    public String getActores() {
        return actores;
    }

    /**
     * @param actores the actores to set
     */
    public void setActores(String actores) {
        this.actores = actores;
    }

    /**
     * @return the extiendeA
     */
    public String getExtiendeA() {
        return extiendeA;
    }

    /**
     * @param extiendeA the extiendeA to set
     */
    public void setExtiendeA(String extiendeA) {
        this.extiendeA = extiendeA;
    }

    /**
     * @return the incluyeA
     */
    public String getIncluyeA() {
        return incluyeA;
    }

    /**
     * @param incluyeA the incluyeA to set
     */
    public void setIncluyeA(String incluyeA) {
        this.incluyeA = incluyeA;
    }

    /**
     * @return the puntosDeInclusion
     */
    public String getPuntosDeInclusion() {
        return puntosDeInclusion;
    }

    /**
     * @param puntosDeInclusion the puntosDeInclusion to set
     */
    public void setPuntosDeInclusion(String puntosDeInclusion) {
        this.puntosDeInclusion = puntosDeInclusion;
    }

    /**
     * @return the puntosDeExtension
     */
    public String getPuntosDeExtension() {
        return puntosDeExtension;
    }

    /**
     * @param puntosDeExtension the puntosDeExtension to set
     */
    public void setPuntosDeExtension(String puntosDeExtension) {
        this.puntosDeExtension = puntosDeExtension;
    }

    /**
     * @return the conversation
     */
    public Conversation getConversation() {
        return conversation;
    }

    /**
     * @param conversation the conversation to set
     */
    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    /**
     * @return the actoresRelacionadosACdu
     */
    public List<ActorCasoDeUso> getActoresRelacionadosACdu() {
        return actoresRelacionadosACdu;
    }

    /**
     * @param actoresRelacionadosACdu the actoresRelacionadosACdu to set
     */
    public void setActoresRelacionadosACdu(List<ActorCasoDeUso> actoresRelacionadosACdu) {
        this.actoresRelacionadosACdu = actoresRelacionadosACdu;
    }

    /**
     * @return the cduRelRelacionadosACdu
     */
    public List<CasosDeUsoRelaciones> getCduRelRelacionadosACdu() {
        return cduRelRelacionadosACdu;
    }

    /**
     * @param cduRelRelacionadosACdu the cduRelRelacionadosACdu to set
     */
    public void setCduRelRelacionadosACdu(List<CasosDeUsoRelaciones> cduRelRelacionadosACdu) {
        this.cduRelRelacionadosACdu = cduRelRelacionadosACdu;
    }

    /**
     * @return the feFlujoNormalList
     */
    public List<FeFlujonormal> getFeFlujoNormalList() {
        return feFlujoNormalList;
    }

    /**
     * @param feFlujoNormalList the feFlujoNormalList to set
     */
    public void setFeFlujoNormalList(List<FeFlujonormal> feFlujoNormalList) {
        this.feFlujoNormalList = feFlujoNormalList;
    }

    /**
     * @return the feFlujoAlternativoList
     */
    public List<FeFlujoalternativo> getFeFlujoAlternativoList() {
        return feFlujoAlternativoList;
    }

    /**
     * @param feFlujoAlternativoList the feFlujoAlternativoList to set
     */
    public void setFeFlujoAlternativoList(List<FeFlujoalternativo> feFlujoAlternativoList) {
        this.feFlujoAlternativoList = feFlujoAlternativoList;
    }

    /**
     * @return the feFlujoAlternativoPasoList
     */
    public List<FeFlujoalternativopaso> getFeFlujoAlternativoPasoList() {
        return feFlujoAlternativoPasoList;
    }

    /**
     * @param feFlujoAlternativoPasoList the feFlujoAlternativoPasoList to set
     */
    public void setFeFlujoAlternativoPasoList(List<FeFlujoalternativopaso> feFlujoAlternativoPasoList) {
        this.feFlujoAlternativoPasoList = feFlujoAlternativoPasoList;
    }

    /**
     * @return the fnFaMap
     */
    public Map<Integer, List<FeFlujoalternativo>> getFnFaMap() {
        return fnFaMap;
    }

    /**
     * @param fnFaMap the fnFaMap to set
     */
    public void setFnFaMap(Map<Integer, List<FeFlujoalternativo>> fnFaMap) {
        this.fnFaMap = fnFaMap;
    }

    /**
     * @return the faFapMap
     */
    public Map<Integer, List<FeFlujoalternativopaso>> getFaFapMap() {
        return faFapMap;
    }

    /**
     * @param faFapMap the faFapMap to set
     */
    public void setFaFapMap(Map<Integer, List<FeFlujoalternativopaso>> faFapMap) {
        this.faFapMap = faFapMap;
    }

    /**
     * @return the fapTable
     */
    public UIData getFapTable() {
        return fapTable;
    }

    /**
     * @param fapTable the fapTable to set
     */
    public void setFapTable(UIData fapTable) {
        this.fapTable = fapTable;
    }
}
