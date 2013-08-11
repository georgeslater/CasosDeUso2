/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.controllers.exceptions.NoAccessException;
import com.example.controllers.exceptions.UserNotRecognizedException;
import com.example.controllers.util.JsfUtil;
import com.example.controllers.util.Messages;
import com.example.entities.Actor;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.CasosDeUsoRelaciones;
import com.example.entities.Diagrama;
import com.example.entities.Fila;
import com.example.entities.Image;
import com.example.entities.Relacion;
import com.example.entities.UsuarioTable;
import com.example.negocio.CrearCasosService;
import com.example.negocio.DibujarService;
import com.example.negocio.EncryptionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Message;
import javax.servlet.ServletContext;
import sun.misc.BASE64Decoder;

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
    private List<Actor> actores;
    private List<Relacion> relaciones;
    private List<CasoDeUso> cdus;
    private List<ActorCasoDeUso> actCdu;
    private List<CasosDeUsoRelaciones> cduRel;
    private List<Fila> filas;
    private String diagramaID;
    private Diagrama diagramaActual;
    private UsuarioTable usuarioLogueado;
    private Actor actorActual;
    private String nombreNuevoActor;
    private CasoDeUso cduActual;
    private String nombreNuevoCdu;
    private String relacion;
    private int filaABorrar;
    private int thexPositionSelected;
    private int theyPositionSelected;
    private Set<Actor> filasActores;
    private String json;
    private String dataURL;
    private Boolean mostrarImagenEstatico;
    private Boolean isError;
    private Boolean esInvalido;
    private Image diagramaImagen;
    private List<CasoDeUso> filasCdus;
    private org.primefaces.component.datatable.DataTable casoTabla;
    @EJB
    private CrearCasosService crearCasosService;
    @EJB
    private DibujarService dibujarService;
    @EJB
    private EncryptionService encryptService;

    /**
     * Creates a new instance of CrearCasosBean
     */
    public CrearCasosBean() {

        actores = new ArrayList<Actor>();
        actCdu = new ArrayList<ActorCasoDeUso>();
        cdus = new ArrayList<CasoDeUso>();
        cduRel = new ArrayList<CasosDeUsoRelaciones>();
        filas = new ArrayList<Fila>();
        filasActores = new HashSet<Actor>();
    }

    public String borrarFila() {

        String rowStr = JsfUtil.getRequestParameter("filaABorrar");

        try {

            int rowInt = Integer.valueOf(rowStr);

            if (filas.get(rowInt) != null) {
                filas.remove(rowInt);
            }

        } catch (NumberFormatException e) {

            return null;
        }

        return null;
    }

    public String addRow() {

        Fila newCduRow = new Fila();
        filas.add(newCduRow);
        return null;
    }

    public void cargarValores() {

        if (!FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest() && !FacesContext.getCurrentInstance().isPostback()
                && conversation.isTransient()) {
            
            conversation.begin();

            esInvalido = false;
            usuarioLogueado = getCrearCasosService().getUsuarioLogueado();

            if (usuarioLogueado != null && usuarioLogueado.getIduser() != null) {

                if (diagramaID != null) {

                    try {

                        int diagInt = Integer.parseInt(diagramaID);
                        diagramaActual = crearCasosService.obtenerDiagramaPorId(diagInt);

                        diagramaImagen = dibujarService.obtenerImagenPorDiagramaId(diagramaActual);
                        
                        //checkeo de seguridad
                        if(diagramaActual != null && diagramaActual.getUsuario() != null && diagramaActual.getUsuario().equals(usuarioLogueado)){
                            filas = crearCasosService.getFilasPrecargadas(diagInt);
                        }else{
                            //NoAccessException e = new NoAccessException("No tiene permiso de ver este diagrama!");
                            //JsfUtil.addErrorMessage(e, "No tiene permiso de ver este diagrama!");
                            Messages.addError("No tiene permiso de ver este diagrama!");
                            esInvalido = true;
                        }

                    } catch (NumberFormatException e) {
                        Messages.addError("Error al cargar el diagrama.  Contacte un administrador.");
                        esInvalido = true;
                    }

                    if(!getEsInvalido()){
                    
                        if (filas.size() > 0) {

                            filasActores = precargarFilas();

                        } else {

                            //si no hay filas
                            Fila cduRow = new Fila();
                            filas.add(cduRow);
                        }
                    }

                } else {
                    //if casoRow is null
                    Fila cduRow = new Fila();
                    filas.add(cduRow);
                }

            } else {

                UserNotRecognizedException e = new UserNotRecognizedException("Ha producido un error de autentificacion.");
                JsfUtil.addErrorMessage(e, "Ha producido un error de autentificacion.");
            }
        }
    }

    public void guardarImagen() {

        if (dataURL != null && !dataURL.equals("")) {

            try {

                ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
                ServletContext servletContext = (ServletContext) external.getContext();
                String md5path = encryptService.encryptar(usuarioLogueado.getUsernameusuario() + "_" + diagramaID);
                String relativeFilename = "/resources/imagenes/" + md5path;
                String filename = servletContext.getRealPath(relativeFilename + ".png");
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(dataURL.split("^data:image/(png|jpg);base64,")[1]);
                BufferedImage imag = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                ImageIO.write(imag, "png", new File(filename));
                Image i = crearCasosService.guardarNuevoImagen(diagramaImagen, usuarioLogueado, diagramaActual, "test", relativeFilename);
                Messages.addInfo("Su imagen ha sido guardado.");
                diagramaImagen = i;
                mostrarImagenEstatico = true;

            } catch (IOException e) {

                Messages.addError("Error al guardar el imagen.");
            }
        }



    }

    public void evaluarDialogsEliminar() {

        Fila selectedCduRow = filas.get(thexPositionSelected);

        switch (theyPositionSelected) {

            case 1:

                if (selectedCduRow.getActorID() != null && filasActores.contains(selectedCduRow.getActorID())) {
                    filasActores.remove(selectedCduRow.getActorID());
                }
                selectedCduRow.setActorID(null);
                break;

            case 2:
                selectedCduRow.setCasoDeUso1ID(null);
                break;

            case 3:
                selectedCduRow.setRelacion1ID(null);
                break;

            case 4:
                selectedCduRow.setCasoDeUso2ID(null);
                break;

            case 5:
                selectedCduRow.setRelacion2ID(null);
                break;

            case 6:
                selectedCduRow.setCasoDeUso3ID(null);
                break;

            case 7:
                selectedCduRow.setRelacion3ID(null);
                break;

            case 8:
                selectedCduRow.setCasoDeUso4ID(null);
                break;

            case 9:
                selectedCduRow.setRelacion4ID(null);
                break;

            case 10:
                selectedCduRow.setCasoDeUso5ID(null);
                break;
        }
    }

    public void agregarActor() {

        Fila selectedCduRow = filas.get(thexPositionSelected);

        Actor act = null;

        if (actorActual != null) {

            act = actorActual;

        } else if (nombreNuevoActor != null) {

            Map<String, Actor> nombresActores = new HashMap<String, Actor>();

            //checkeamos que no esta entre las filas
            for (Fila fila : filas) {

                if (fila.getActorID() != null && fila.getActorID().getNombre() != null) {
                    nombresActores.put(fila.getActorID().getNombre(), fila.getActorID());
                }
            }

            if (nombresActores.get(nombreNuevoActor) != null) {

                act = nombresActores.get(nombreNuevoActor);
            } else {
                act = new Actor();
                act.setNombre(nombreNuevoActor);
            }
            actores.add(act);
        }

        if (act != null) {

            if (selectedCduRow.getActorID() != null) {

                if (filasActores.contains(selectedCduRow.getActorID())) {
                    filasActores.remove(selectedCduRow.getActorID());
                }
            }
            selectedCduRow.setActorID(act);

            if (act.getId() != null) {
                filasActores.add(act);
            }


        }

        nombreNuevoActor = null;
    }

    public void agregarCdu() {

        Fila selectedCduRow = filas.get(thexPositionSelected);

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

                selectedCduRow.setCasoDeUso1ID(cdu);

            } else if (theyPositionSelected == 4) {

                selectedCduRow.setCasoDeUso2ID(cdu);

            } else if (theyPositionSelected == 6) {

                selectedCduRow.setCasoDeUso3ID(cdu);

            } else if (theyPositionSelected == 8) {

                selectedCduRow.setCasoDeUso4ID(cdu);

            } else if (theyPositionSelected == 10) {

                selectedCduRow.setCasoDeUso5ID(cdu);
            }
        }

        nombreNuevoCdu = null;
    }

    public void generarDiagrama() {

        actores = null;
        cdus = null;
        actCdu = null;
        cduRel = null;
        json = null;

        int diagramaidInt = Integer.parseInt(diagramaID);
        actores = crearCasosService.obtenerActoresPorDiagramaID(diagramaidInt);
        cdus = crearCasosService.obtenerCasosDeUsoPorDiagramaID(diagramaidInt);
        actCdu = crearCasosService.obtenerActorCasoDeUsosPorDiagramaID(diagramaidInt);
        cduRel = crearCasosService.obtenerCasosDeUsoRelacionesPorDiagramaID(diagramaidInt);

        Object[][] diagrama = dibujarService.generarDiagrama(actores, actCdu, cdus, cduRel);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        json = gson.toJson(diagrama);
        mostrarImagenEstatico = false;
    }

    public void guardarInformacionFilas() {
        
        List<String> errorMsgs = getCrearCasosService().validar(filas);

        if (errorMsgs.isEmpty()) {

            isError = false;

            if (diagramaActual == null) {

                diagramaActual = getCrearCasosService().guardarDiagrama(usuarioLogueado);

            }

            getCrearCasosService().guardarFilas(filas, diagramaActual, usuarioLogueado);

            generarDiagrama();

        } else {

            isError = true;
            JsfUtil.addErrorMessages(errorMsgs);
            errorMsgs = null;
        }
    }

    public void agregarRelacion() {

        if (relacion != null) {

            Fila selectedCduRow = filas.get(thexPositionSelected);

            Relacion rel = new Relacion();
            rel.setNombre(relacion);

            if (theyPositionSelected == 3) {

                selectedCduRow.setRelacion1ID(rel);

            } else if (theyPositionSelected == 5) {

                selectedCduRow.setRelacion2ID(rel);

            } else if (theyPositionSelected == 7) {

                selectedCduRow.setRelacion3ID(rel);

            } else if (theyPositionSelected == 9) {

                selectedCduRow.setRelacion4ID(rel);
            }
        }

        relacion = null;
    }

    public Set<Actor> precargarFilas() {

        Set<Actor> actoresTemp = new HashSet<Actor>();

        for (Fila fila : filas) {

            if (fila.getActorID() != null) {

                actoresTemp.add(fila.getActorID());
            }
        }

        return actoresTemp;

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

    /**
     * @return the filaABorrar
     */
    public int getFilaABorrar() {
        return filaABorrar;
    }

    /**
     * @param filaABorrar the filaABorrar to set
     */
    public void setFilaABorrar(int filaABorrar) {
        this.filaABorrar = filaABorrar;
    }

    /**
     * @return the casoTabla
     */
    public org.primefaces.component.datatable.DataTable getCasoTabla() {
        return casoTabla;
    }

    /**
     * @param casoTabla the casoTabla to set
     */
    public void setCasoTabla(org.primefaces.component.datatable.DataTable casoTabla) {
        this.casoTabla = casoTabla;
    }

    /**
     * @return the filasActores
     */
    public Set<Actor> getFilasActores() {

        return filasActores;
    }

    /**
     * @return the filasCdus
     */
    public List<CasoDeUso> getFilasCdus() {
        return filasCdus;
    }

    /**
     * @param filasCdus the filasCdus to set
     */
    public void setFilasCdus(List<CasoDeUso> filasCdus) {
        this.filasCdus = filasCdus;
    }

    /**
     * @param filasActores the filasActores to set
     */
    public void setFilasActores(Set<Actor> filasActores) {
        this.filasActores = filasActores;
    }

    /**
     * @return the dataURL
     */
    public String getDataURL() {
        return dataURL;
    }

    /**
     * @param dataURL the dataURL to set
     */
    public void setDataURL(String dataURL) {
        this.dataURL = dataURL;
    }

    /**
     * @return the diagramaActual
     */
    public Diagrama getDiagramaActual() {
        return diagramaActual;
    }

    /**
     * @param diagramaActual the diagramaActual to set
     */
    public void setDiagramaActual(Diagrama diagramaActual) {
        this.diagramaActual = diagramaActual;
    }

    /**
     * @return the encryptService
     */
    public EncryptionService getEncryptService() {
        return encryptService;
    }

    /**
     * @param encryptService the encryptService to set
     */
    public void setEncryptService(EncryptionService encryptService) {
        this.encryptService = encryptService;
    }

    /**
     * @return the mostrarImagenEstatico
     */
    public Boolean getMostrarImagenEstatico() {
        return mostrarImagenEstatico;
    }

    /**
     * @param mostrarImagenEstatico the mostrarImagenEstatico to set
     */
    public void setMostrarImagenEstatico(Boolean mostrarImagenEstatico) {
        this.mostrarImagenEstatico = mostrarImagenEstatico;
    }

    /**
     * @return the imagenPath
     */
    public Image getDiagramaImagen() {
        return diagramaImagen;
    }

    /**
     * @param imagenPath the imagenPath to set
     */
    public void setDiagramaImagen(Image diagramaImagen) {
        this.diagramaImagen = diagramaImagen;
    }

    /**
     * @return the isError
     */
    public Boolean getIsError() {
        return isError;
    }

    /**
     * @param isError the isError to set
     */
    public void setIsError(Boolean isError) {
        this.isError = isError;
    }

    /**
     * @return the cduRel
     */
    public List<CasosDeUsoRelaciones> getCduRel() {
        return cduRel;
    }

    /**
     * @param cduRel the cduRel to set
     */
    public void setCduRel(List<CasosDeUsoRelaciones> cduRel) {
        this.cduRel = cduRel;
    }

    /**
     * @return the esInvalido
     */
    public Boolean getEsInvalido() {
        return esInvalido;
    }

    /**
     * @param esInvalido the esInvalido to set
     */
    public void setEsInvalido(Boolean esInvalido) {
        this.esInvalido = esInvalido;
    }
}
