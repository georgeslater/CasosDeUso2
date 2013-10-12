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
import org.primefaces.event.RowEditEvent;

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

    public String getConversationId() {

        return conversation.getId();
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

                        FeFlujonormal fn = new FeFlujonormal();
                        fn.setFEEncabezadoID(encabezado);
                        fn.setOrden(1);
                        feService.getFeFnFacade().create(fn);
                        feFlujoNormalList.add(fn);

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

                        feFlujoAlternativoList = feService.obtenerFlujosAlternativosPorFlujosNormales(feFlujoNormalList);

                        for (FeFlujoalternativo fa : feFlujoAlternativoList) {

                            if (!fnFaMap.containsKey(fa.getFEFlujoNormalID().getId())) {

                                fnFaMap.put(fa.getFEFlujoNormalID().getId(), new ArrayList<FeFlujoalternativo>());
                            }

                            fnFaMap.get(fa.getFEFlujoNormalID().getId()).add(fa);
                        }

                        if (!feFlujoAlternativoList.isEmpty()) {

                            feFlujoAlternativoPasoList = feService.obtenerFlujosAlternativoPasosPorFlujosAlternativos(feFlujoAlternativoList);

                            for (FeFlujoalternativopaso fap : feFlujoAlternativoPasoList) {

                                if (!faFapMap.containsKey(fap.getFEFlujoAlternativoID().getId())) {

                                    faFapMap.put(fap.getFEFlujoAlternativoID().getId(), new ArrayList<FeFlujoalternativopaso>());
                                }

                                faFapMap.get(fap.getFEFlujoAlternativoID().getId()).add(fap);
                            }

                            Collections.sort(feFlujoAlternativoPasoList);
                        }
                    } else {

                        FeFlujonormal fn = new FeFlujonormal();
                        fn.setFEEncabezadoID(encabezado);
                        fn.setOrden(1);
                        feService.getFeFnFacade().create(fn);
                        feFlujoNormalList.add(fn);
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

                            } else if (cduRel.getRelacionid().getNombre().equals("INCLUDES")) {

                                incluyeA += cduRel.getCasodeuso2id().getText() + ", ";
                            }
                        }

                        if (cduRel.getCasodeuso2id().equals(cduActual)) {

                            if (cduRel.getRelacionid().getNombre().equals("EXTENDS")) {

                                puntosDeExtension += cduRel.getCasodeuso1id().getText() + ", ";

                            } else if (cduRel.getRelacionid().getNombre().equals("INCLUDES")) {

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
        boolean esExitoFAP = feService.guardarFeFlujoAlternativoPaso(feFlujoAlternativoPasoList);
        
        if (!esExito || !esExitoFN || !esExitoFAP) {

            Messages.addFatal("Se ha producido un error.  Intente mas tarde.");

        } else {

            Messages.addInfo("Sus datos han sido guardados.");
        }
    }

    public String borrarFilaFlujoAlternativoPaso() {

        int row = fapTable.getRowIndex();

        FeFlujoalternativopaso fapABorrar = feFlujoAlternativoPasoList.get(row);
        boolean esUltimoFap;

        if (faFapMap.containsKey(fapABorrar.getFEFlujoAlternativoID().getId())) {

            List<FeFlujoalternativopaso> faps = faFapMap.get(fapABorrar.getFEFlujoAlternativoID().getId());

            esUltimoFap = faps.size() == 1;

            int index = -1;

            for (int i = 0; i < faps.size(); i++) {

                if (fapABorrar.equals(faps.get(i))) {

                    index = i;
                    break;
                }
            }

            if (index != -1) {

                faps.remove(index);

                for (int j = faps.size() - 1; j > index - 1; j--) {

                    faps.get(j).setOrden(faps.get(j).getOrden() - 1);
                    feService.getFapFacade().edit(faps.get(j));
                }
            }

            feFlujoAlternativoPasoList.remove(fapABorrar);
            feService.getFapFacade().remove(fapABorrar);

            if (esUltimoFap) {

                fnFaMap.get(fapABorrar.getFEFlujoAlternativoID().getFEFlujoNormalID().getId()).remove(fapABorrar.getFEFlujoAlternativoID());

                for (FeFlujoalternativo fa : fnFaMap.get(fapABorrar.getFEFlujoAlternativoID().getFEFlujoNormalID().getId())) {

                    if (fa.getOrden() > row) {

                        fa.setOrden(fa.getOrden() - 1);
                        getFeService().getFaFacade().edit(fa);
                    }
                }

                feService.getFaFacade().remove(fapABorrar.getFEFlujoAlternativoID());
            }
        }

        Collections.sort(feFlujoAlternativoPasoList);

        return null;
    }

    public String agregarFilaFlujoAlternativoPaso() {

        int row = fapTable.getRowIndex();
        FeFlujoalternativopaso fap = feFlujoAlternativoPasoList.get(row);
        int ordenActual = fap.getOrden();
        int ordenNuevo = ordenActual + 1;

        FeFlujoalternativopaso newFap = new FeFlujoalternativopaso();

        newFap.setOrden(ordenNuevo);
        newFap.setFEFlujoAlternativoID(fap.getFEFlujoAlternativoID());

        feFlujoAlternativoPasoList.add(newFap);

        if (!faFapMap.containsKey(newFap.getFEFlujoAlternativoID().getId())) {

            faFapMap.put(newFap.getFEFlujoAlternativoID().getId(), new ArrayList<FeFlujoalternativopaso>());
        }

        faFapMap.get(newFap.getFEFlujoAlternativoID().getId()).add(newFap);

        Collections.sort(feFlujoAlternativoPasoList);

        for (int i = row + 2; i < feFlujoAlternativoPasoList.size(); i++) {

            FeFlujoalternativopaso fapTemp = feFlujoAlternativoPasoList.get(i);

            //No debemos tocar el orden de 3.2.4 si se agrega un paso al 3.1.1
            if (fap.getFEFlujoAlternativoID().getId() != fapTemp.getFEFlujoAlternativoID().getId()) {

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

    public String borrarFilaFlujoNormal(int row) {

        FeFlujonormal fnABorrar = feFlujoNormalList.get(row);

        for (int i = row + 1; i < feFlujoNormalList.size(); i++) {

            FeFlujonormal feTemp = feFlujoNormalList.get(i);
            feTemp.setOrden(feTemp.getOrden() - 1);
            getFeService().getFeFnFacade().edit(feTemp);
        }

        if (fnFaMap.containsKey(fnABorrar.getId()) && fnFaMap.get(fnABorrar.getId()) != null && fnFaMap.get(fnABorrar.getId()).size() > 0) {

            List<FeFlujoalternativo> faList = fnFaMap.get(fnABorrar.getId());

            for (FeFlujoalternativo fa : faList) {

                if (faFapMap.containsKey(fa.getId()) && faFapMap.get(fa.getId()) != null && faFapMap.get(fa.getId()).size() > 0) {

                    List<FeFlujoalternativopaso> fapList = faFapMap.get(fa.getId());

                    for (FeFlujoalternativopaso fap : fapList) {

                        getFeFlujoAlternativoPasoList().remove(fap);
                        getFeService().getFapFacade().remove(fap);
                    }

                    fapList.removeAll(fapList);
                }

                feFlujoAlternativoList.remove(fa);
                getFeService().getFaFacade().remove(fa);
            }

            faList.removeAll(faList);
        }

        feFlujoNormalList.remove(fnABorrar);
        getFeService().getFeFnFacade().remove(fnABorrar);

        if (feFlujoNormalList.isEmpty()) {

            FeFlujonormal fn = new FeFlujonormal();
            fn.setFEEncabezadoID(encabezado);
            fn.setOrden(1);
            getFeService().getFeFnFacade().create(fn);
            feFlujoNormalList.add(fn);
        }

        //Collections.sort(feFlujoAlternativoPasoList);
        refreshFapTable();

        return null;
    }

    public void refreshFapTable() {

        if (!feFlujoAlternativoList.isEmpty()) {
            
            feFlujoAlternativoPasoList = new ArrayList<FeFlujoalternativopaso>();
            
            feFlujoAlternativoPasoList = feService.obtenerFlujosAlternativoPasosPorFlujosAlternativos(feFlujoAlternativoList);

            for (FeFlujoalternativopaso fap : feFlujoAlternativoPasoList) {

                if (!faFapMap.containsKey(fap.getFEFlujoAlternativoID().getId())) {

                    faFapMap.put(fap.getFEFlujoAlternativoID().getId(), new ArrayList<FeFlujoalternativopaso>());
                }

                faFapMap.get(fap.getFEFlujoAlternativoID().getId()).add(fap);
            }

            Collections.sort(feFlujoAlternativoPasoList);
        }
    }

    public String agregarFilaFlujoNormal(int row) {

        FeFlujonormal fn = new FeFlujonormal();
        fn.setOrden(row + 2);
        fn.setFEEncabezadoID(encabezado);

        getFeService().getFeFnFacade().create(fn);

        feFlujoNormalList.add(row + 1, fn);

        for (int i = row + 2; i < feFlujoNormalList.size(); i++) {

            FeFlujonormal feTemp = feFlujoNormalList.get(i);
            feTemp.setOrden(feTemp.getOrden() + 1);
            getFeService().getFeFnFacade().edit(feTemp);
        }

        //si no refrescamos, el nuevo orden no se releja en la datatable de pasos alternativos.
        //no deberia ser necesario si usamos ajax="false" pero lo es.
        refreshFapTable();
        
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
