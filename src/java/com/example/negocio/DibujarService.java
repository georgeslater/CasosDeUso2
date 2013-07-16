package com.example.negocio;

import com.example.dao.FilaFacade;
import com.example.dao.ImageFacade;
import com.example.entities.Actor;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.CasosDeUsoRelaciones;
import com.example.entities.Diagrama;
import com.example.entities.Fila;
import com.example.entities.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class DibujarService {

    private Object[][] diagramTabla;
    private HashMap<Integer, Posiciones> posicionesActores;
    private HashMap<Integer, Posiciones> posicionesCasosDeUso;
    
    @EJB
    private ImageFacade imageFacade;
    @EJB
    private FilaFacade filaFacade;
    
    public DibujarService() {
       
        posicionesActores = new HashMap<Integer, Posiciones>();
        posicionesCasosDeUso = new HashMap<Integer, Posiciones>();
    }
    
    public Image obtenerImagenPorDiagramaId(Diagrama diagrama){
        
        return getImageFacade().obtenerImagenPorDiagramaId(diagrama);
    }
    
    public Object[][] generarDiagrama(List<Actor> actores, List<ActorCasoDeUso> actCdus, List<CasoDeUso> cdus, List<CasosDeUsoRelaciones> cduRel) {
        
        diagramTabla = new Object[50][8];
        HashMap<Integer, ArrayList<CasoDeUso>> actoresCasosDeUso = new HashMap<Integer, ArrayList<CasoDeUso>>();
        HashMap<Integer, ArrayList<CasoDeUso>> casoDeUsoRelaciones = new HashMap<Integer, ArrayList<CasoDeUso>>();
        Set<Integer> cduIdsEnDiagrama = new HashSet<Integer>();

        for (ActorCasoDeUso actCdu : actCdus) {

            if (!actoresCasosDeUso.containsKey(actCdu.getActorid().getId())) {

                actoresCasosDeUso.put(actCdu.getActorid().getId(), new ArrayList<CasoDeUso>());
            }

            actoresCasosDeUso.get(actCdu.getActorid().getId()).add(actCdu.getCasodeusoid());
        }
        
        if(cduRel != null && cduRel.size() > 0){
            
            for (CasosDeUsoRelaciones cduR : cduRel) {

                if (!casoDeUsoRelaciones.containsKey(cduR.getCasodeuso1id().getId())) {
                    casoDeUsoRelaciones.put(cduR.getCasodeuso1id().getId(), new ArrayList<CasoDeUso>());
                }
                casoDeUsoRelaciones.get(cduR.getCasodeuso1id().getId()).add(cduR.getCasodeuso2id());
            }
        }


        for (Actor a : actores) {

            for (int i = 0; i < 100; i++) {

                if (getDiagramTabla()[i][0] == null) {

                    getDiagramTabla()[i][0] = a;
                    posicionesActores.put(a.getId(), new Posiciones(i, 0));
                    break;
                }
            }

            ArrayList<CasoDeUso> cduEnlazadosAActor = actoresCasosDeUso.get(a.getId());

            if (cduEnlazadosAActor != null && cduEnlazadosAActor.size() > 0) {

                for (CasoDeUso cdu : cduEnlazadosAActor) {

                    //verificar que el caso de uso no esta en el diagrama
                    if (!cduIdsEnDiagrama.contains(cdu.getId())) {

                        for (int i = posicionesActores.get(a.getId()).x; i < 100; i++) {
                            
                            if(getDiagramTabla()[i][1] == null){
                                
                                getDiagramTabla()[i][1] = cdu;
                                cduIdsEnDiagrama.add(cdu.getId());
                                posicionesCasosDeUso.put(cdu.getId(), new Posiciones(i, 1));
                                break;
                            }
                        }
                    }
                }                
            }
        }



        return diagramTabla;
    }

    /**
     * @return the diagramTabla
     */
    public Object[][] getDiagramTabla() {
        return diagramTabla;
    }

    /**
     * @param diagramTabla the diagramTabla to set
     */
    public void setDiagramTabla(Object[][] diagramTabla) {
        this.diagramTabla = diagramTabla;
    }

    /**
     * @return the posicionesActores
     */
    public HashMap<Integer, Posiciones> getPosicionesActores() {
        return posicionesActores;
    }

    /**
     * @param posicionesActores the posicionesActores to set
     */
    public void setPosicionesActores(HashMap<Integer, Posiciones> posicionesActores) {
        this.posicionesActores = posicionesActores;
    }

    /**
     * @return the posicionesCasosDeUso
     */
    public HashMap<Integer, Posiciones> getPosicionesCasosDeUso() {
        return posicionesCasosDeUso;
    }

    /**
     * @param posicionesCasosDeUso the posicionesCasosDeUso to set
     */
    public void setPosicionesCasosDeUso(HashMap<Integer, Posiciones> posicionesCasosDeUso) {
        this.posicionesCasosDeUso = posicionesCasosDeUso;
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

    public class Posiciones {

        private int x;
        private int y;

        public Posiciones(int x, int y) {

            this.x = x;
            this.y = y;
        }

        /**
         * @return the x
         */
        public int getX() {
            return x;
        }

        /**
         * @param x the x to set
         */
        public void setX(int x) {
            this.x = x;
        }

        /**
         * @return the y
         */
        public int getY() {
            return y;
        }

        /**
         * @param y the y to set
         */
        public void setY(int y) {
            this.y = y;
        }
    }
}
