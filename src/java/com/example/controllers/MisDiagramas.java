/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.controllers.util.Messages;
import com.example.dao.ActorCasoDeUsoFacade;
import com.example.dao.ActorFacade;
import com.example.dao.CasoDeUsoFacade;
import com.example.dao.CasosDeUsoRelacionesFacade;
import com.example.dao.DiagramaFacade;
import com.example.dao.FeEncabezadoFacade;
import com.example.dao.FeFlujoAlternativoFacade;
import com.example.dao.FeFlujoAlternativoPasoFacade;
import com.example.dao.FeFlujoNormalFacade;
import com.example.dao.FilaFacade;
import com.example.dao.ImageFacade;
import com.example.dao.UsuarioTableFacade;
import com.example.entities.Actor;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.CasosDeUsoRelaciones;
import com.example.entities.Diagrama;
import com.example.entities.FeEncabezado;
import com.example.entities.FeFlujoalternativo;
import com.example.entities.FeFlujoalternativopaso;
import com.example.entities.FeFlujonormal;
import com.example.entities.Fila;
import com.example.entities.Image;
import com.example.entities.UsuarioTable;
import com.example.negocio.DiagramaService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import com.example.negocio.EncryptionService;
import java.util.ArrayList;

@Named(value = "misDiagramas")
@RequestScoped
public class MisDiagramas implements Serializable {

    private String nombreNuevoDiagrama;
    private Diagrama diagramaABorrar;
    private Map<Integer, String> diagramaImagenes = new HashMap<Integer, String>();
    private List<Diagrama> misDiagramas;
    @EJB
    private DiagramaFacade diagFacade;
    @EJB
    private UsuarioTableFacade utFacade;
    @EJB
    private FilaFacade filaFacade;
    @EJB
    private ActorCasoDeUsoFacade actorCasoDeUsoFacade;
    @EJB
    private ActorFacade actorFacade;
    @EJB
    private CasoDeUsoFacade cduFacade;
    @EJB
    private CasosDeUsoRelacionesFacade cduRelFacade;
    @EJB
    private ImageFacade imgFacade;
    @EJB
    private FeFlujoAlternativoFacade faFacade;
    @EJB
    private FeFlujoNormalFacade fnFacade;
    @EJB
    private FeEncabezadoFacade feFacade;
    @EJB
    private FeFlujoAlternativoPasoFacade fapFacade;
    private UsuarioTable usuarioLogueado;
    @EJB
    private DiagramaService diagramaService;
    @EJB
    private EncryptionService encryptService;

    /**
     * Creates a new instance of MisDiagramas
     */
    public MisDiagramas() {
    }

    public void editar(RowEditEvent event) {

        Diagrama diagramaAEditar = (Diagrama) event.getObject();
        getDiagFacade().edit(diagramaAEditar);
    }

    public void borrarDiagrama() {

        if (getDiagramaABorrar() != null) {

            Image i = getImgFacade().obtenerImagenPorDiagramaId(getDiagramaABorrar());

            if (i != null) {

                getImgFacade().remove(i);
            }

            List<Fila> filasDiagrama = getFilaFacade().obtenerFilasPorDiagramaID(getDiagramaABorrar().getId());

            for (Fila f : filasDiagrama) {
                getFilaFacade().remove(f);
            }

            List<ActorCasoDeUso> actCdus = getActorCasoDeUsoFacade().obtenerActorCdusPorDiagramaID(getDiagramaABorrar().getId());

            for (ActorCasoDeUso actCdu : actCdus) {

                getActorCasoDeUsoFacade().remove(actCdu);
            }

            List<CasosDeUsoRelaciones> cduRels = getCduRelFacade().obtenerCduRelsPorDiagramaID(getDiagramaABorrar().getId());

            for (CasosDeUsoRelaciones cduRel : cduRels) {

                getCduRelFacade().remove(cduRel);
            }

            List<Actor> acts = getActorFacade().obtenerActoresPorDiagramaID(getDiagramaABorrar().getId());

            for (Actor a : acts) {

                getActorFacade().remove(a);
            }

            List<CasoDeUso> cdus = getCduFacade().obtenerCdusPorDiagramaID(getDiagramaABorrar().getId());
            
            List<Integer> cduIds = new ArrayList<Integer>();
            
            for(CasoDeUso cdu: cdus){
                
                cduIds.add(cdu.getId());
            }
            
            List<FeEncabezado> fes = getFeFacade().obtenerEncabezadosPorCdus(cduIds);

            List<FeFlujonormal> fns = new ArrayList<FeFlujonormal>();

            List<FeFlujoalternativo> fas = new ArrayList<FeFlujoalternativo>();

            List<FeFlujoalternativopaso> faps = new ArrayList<FeFlujoalternativopaso>();

            for (FeEncabezado fe : fes) {

                List<FeFlujonormal> tempFns = getFnFacade().obtenerFlujoNormalPasosPorEncabezado(fe);
                fns.addAll(tempFns);
            }

            if (!fns.isEmpty()) {

                List<Integer> fnIds = new ArrayList<Integer>();

                for (FeFlujonormal fn : fns) {

                    fnIds.add(fn.getId());
                }

                fas = getFaFacade().obtenerFlujosAlternativosPorFlujosNormales(fnIds);

                if (!fas.isEmpty()) {
                    
                    List<Integer> faIds = new ArrayList<Integer>();
                    
                    for(FeFlujoalternativo fa: fas){
                        
                        faIds.add(fa.getId());
                    }
                    
                    faps = getFapFacade().obtenerFlujosAlternativoPasosPorFlujosAlternativos(faIds);
                }
            }
            
            if(!faps.isEmpty()){
                
                for(FeFlujoalternativopaso fap: faps){
                    
                    getFapFacade().remove(fap);
                }
            }
            
            if(!fas.isEmpty()){
                
                for(FeFlujoalternativo fa: fas){
                    
                    getFaFacade().remove(fa);
                }
            }
            
            if(!fns.isEmpty()){
                
                for(FeFlujonormal fn: fns){
                    
                    getFnFacade().remove(fn);
                }
            }
            
            if(!fes.isEmpty()){
                
                for(FeEncabezado fe: fes){
                    
                    getFeFacade().remove(fe);
                }
            }

            for (CasoDeUso cdu : cdus) {

                getCduFacade().remove(cdu);
            }

            getDiagFacade().remove(getDiagramaABorrar());
            misDiagramas.remove(diagramaABorrar);
        }
    }

    public String agregarDiagrama() {

        if (nombreNuevoDiagrama != null && !nombreNuevoDiagrama.equals("")) {

            if (!diagramaService.nombreDiagramaExiste(nombreNuevoDiagrama, usuarioLogueado)) {

                Diagrama d = new Diagrama();
                d.setNombre(nombreNuevoDiagrama);
                d.setUsuario(usuarioLogueado);
                getDiagFacade().create(d);
                return "CrearCasosDeUso.xhtml?faces-redirect=true&id=" + d.getId();

            } else {

                Messages.addError("Un diagrama ya existe con ese nombre.  Intente de nuevo.");
            }
        }

        return null;
    }

    @PostConstruct
    public void init() {

        //obtener id del usuario
        String usuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        if (usuario != null) {

            setUsuarioLogueado(getUtFacade().obtenerIDPorNombre(usuario));

            if (getUsuarioLogueado() != null && getUsuarioLogueado().getIduser() != null) {

                misDiagramas = getDiagFacade().obtenerDiagramaPorUserID(usuarioLogueado.getIduser());
            }
        }
    }

    /**
     * @return the misDiagramas
     */
    public List<Diagrama> getMisDiagramas() {
        return misDiagramas;
    }

    /**
     * @param misDiagramas the misDiagramas to set
     */
    public void setMisDiagramas(List<Diagrama> misDiagramas) {
        this.misDiagramas = misDiagramas;
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
     * @return the nombreNuevoDiagrama
     */
    public String getNombreNuevoDiagrama() {
        return nombreNuevoDiagrama;
    }

    /**
     * @param nombreNuevoDiagrama the nombreNuevoDiagrama to set
     */
    public void setNombreNuevoDiagrama(String nombreNuevoDiagrama) {
        this.nombreNuevoDiagrama = nombreNuevoDiagrama;
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
     * @return the diagramaABorrar
     */
    public Diagrama getDiagramaABorrar() {
        return diagramaABorrar;
    }

    /**
     * @param diagramaABorrar the diagramaABorrar to set
     */
    public void setDiagramaABorrar(Diagrama diagramaABorrar) {
        this.diagramaABorrar = diagramaABorrar;
    }

    /**
     * @return the diagramaImagenes
     */
    public Map getDiagramaImagenes() {
        return diagramaImagenes;
    }

    /**
     * @param diagramaImagenes the diagramaImagenes to set
     */
    public void setDiagramaImagenes(Map diagramaImagenes) {
        this.diagramaImagenes = diagramaImagenes;
    }

    /**
     * @return the encryptService
     */
    public EncryptionService getEncryptService() {
        return encryptService;
    }

    /**
     * @return the diagramaService
     */
    public DiagramaService getDiagramaService() {
        return diagramaService;
    }

    /**
     * @param diagramaService the diagramaService to set
     */
    public void setDiagramaService(DiagramaService diagramaService) {
        this.diagramaService = diagramaService;
    }

    /**
     * @return the faFacade
     */
    public FeFlujoAlternativoFacade getFaFacade() {
        return faFacade;
    }

    /**
     * @param faFacade the faFacade to set
     */
    public void setFaFacade(FeFlujoAlternativoFacade faFacade) {
        this.faFacade = faFacade;
    }

    /**
     * @return the fnFacade
     */
    public FeFlujoNormalFacade getFnFacade() {
        return fnFacade;
    }

    /**
     * @param fnFacade the fnFacade to set
     */
    public void setFnFacade(FeFlujoNormalFacade fnFacade) {
        this.fnFacade = fnFacade;
    }

    /**
     * @return the feFacade
     */
    public FeEncabezadoFacade getFeFacade() {
        return feFacade;
    }

    /**
     * @param feFacade the feFacade to set
     */
    public void setFeFacade(FeEncabezadoFacade feFacade) {
        this.feFacade = feFacade;
    }

    /**
     * @return the fapFacade
     */
    public FeFlujoAlternativoPasoFacade getFapFacade() {
        return fapFacade;
    }

    /**
     * @param fapFacade the fapFacade to set
     */
    public void setFapFacade(FeFlujoAlternativoPasoFacade fapFacade) {
        this.fapFacade = fapFacade;
    }
}
