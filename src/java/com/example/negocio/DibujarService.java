package com.example.negocio;

import com.example.dao.FilaFacade;
import com.example.dao.ImageFacade;
import com.example.entities.Actor;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.CasosDeUsoRelaciones;
import com.example.entities.Diagrama;
import com.example.entities.Image;
import com.example.entities.Relacion;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    public Image obtenerImagenPorDiagramaId(Diagrama diagrama) {

        return getImageFacade().obtenerImagenPorDiagramaId(diagrama);
    }

    public Object[][] generarDiagrama(List<Actor> actores, List<ActorCasoDeUso> actCdus, List<CasoDeUso> cdus, List<CasosDeUsoRelaciones> cduRel) {

        //Maximo de 50 filas, Una fila contiene 11 posibles valores (1 actor + 5 relaciones + 5 casos de uso)
        diagramTabla = new Object[50][11];

        Map<Integer, ArrayList<CasoDeUso>> actoresCasosDeUso = new HashMap<Integer, ArrayList<CasoDeUso>>();
        Map<Integer, ArrayList<CasoDeUso>> casoDeUsoRelaciones = new HashMap<Integer, ArrayList<CasoDeUso>>();
        Map<String, Relacion> casoDeUsoRelacionesRelation = new HashMap<String, Relacion>();
        Set<Integer> cduIdsEnDiagrama = new HashSet<Integer>();

        for (ActorCasoDeUso actCdu : actCdus) {

            if (!actoresCasosDeUso.containsKey(actCdu.getActorid().getId())) {

                actoresCasosDeUso.put(actCdu.getActorid().getId(), new ArrayList<CasoDeUso>());
            }

            actoresCasosDeUso.get(actCdu.getActorid().getId()).add(actCdu.getCasodeusoid());
        }

        if (cduRel != null && cduRel.size() > 0) {

            for (CasosDeUsoRelaciones cduR : cduRel) {

                if (!casoDeUsoRelaciones.containsKey(cduR.getCasodeuso1id().getId())) {
                    casoDeUsoRelaciones.put(cduR.getCasodeuso1id().getId(), new ArrayList<CasoDeUso>());
                }
                casoDeUsoRelaciones.get(cduR.getCasodeuso1id().getId()).add(cduR.getCasodeuso2id());

                if (!casoDeUsoRelacionesRelation.containsKey(cduR.getCasodeuso1id().getId() + "-" + cduR.getCasodeuso2id().getId())) {
                    casoDeUsoRelacionesRelation.put(cduR.getCasodeuso1id().getId() + "-" + cduR.getCasodeuso2id().getId(), cduR.getRelacionid());
                }
            }
        }


        for (Actor a : actores) {

            //para saber la profundidad del actor
            int counterCdu = 1;

            ArrayList<CasoDeUso> cduEnlazadosAActor = actoresCasosDeUso.get(a.getId());

            if (cduEnlazadosAActor != null && cduEnlazadosAActor.size() > 0) {

                counterCdu += cduEnlazadosAActor.size() - 1;

                for (CasoDeUso cdu : cduEnlazadosAActor) {

                    if (casoDeUsoRelaciones.get(cdu.getId()) != null && casoDeUsoRelaciones.get(cdu.getId()).size() > 0) {

                        counterCdu += casoDeUsoRelaciones.get(cdu.getId()).size() - 1;
                    }
                }
            }

            //Aca seteamos tantos actores como hay niveles
            for (int i = 0; i < counterCdu; i++) {

                for (int j = 0; j < 100; j++) {

                    if (getDiagramTabla()[j][0] == null) {

                        getDiagramTabla()[j][0] = a;

                        // solo la posicion del primero importa, porque todos apuntan a aquel
                        if (!posicionesActores.containsKey(a.getId())) {
                            posicionesActores.put(a.getId(), new Posiciones(j, 0));
                        }
                        break;
                    }
                }
            }

            if (cduEnlazadosAActor != null && cduEnlazadosAActor.size() > 0) {

                for (CasoDeUso cdu : cduEnlazadosAActor) {

                    //verificar que el caso de uso no esta en el diagrama
                    int counter = 1;

                    if (casoDeUsoRelaciones.get(cdu.getId()) != null && casoDeUsoRelaciones.get(cdu.getId()).size() > 0) {

                        counter += casoDeUsoRelaciones.get(cdu.getId()).size() - 1;
                    }

                    for (int i = 0; i < counter; i++) {

                        for (int j = posicionesActores.get(a.getId()).x; j < 100; j++) {

                            if (getDiagramTabla()[j][2] == null) {

                                getDiagramTabla()[j][2] = cdu;
                                cduIdsEnDiagrama.add(cdu.getId());
                                if (!posicionesCasosDeUso.containsKey(cdu.getId())) {
                                    posicionesCasosDeUso.put(cdu.getId(), new Posiciones(j, 2));
                                }
                                break;
                            }
                        }
                    }
                    //}

                    if (casoDeUsoRelaciones.get(cdu.getId()) != null && casoDeUsoRelaciones.get(cdu.getId()).size() > 0) {

                        for (CasoDeUso cduEnlazadoACdu1 : casoDeUsoRelaciones.get(cdu.getId())) {

                            for (int i = posicionesCasosDeUso.get(cdu.getId()).x; i < 100; i++) {

                                if (getDiagramTabla()[i][4] == null) {

                                    getDiagramTabla()[i][4] = cduEnlazadoACdu1;
                                    cduIdsEnDiagrama.add(cduEnlazadoACdu1.getId());
                                    Relacion r = casoDeUsoRelacionesRelation.get(cdu.getId() + "-" + cduEnlazadoACdu1.getId());
                                    posicionesCasosDeUso.put(cduEnlazadoACdu1.getId(), new Posiciones(i, 4, r.getNombre()));
                                    break;
                                }
                            }
                            //}

                            if (casoDeUsoRelaciones.get(cduEnlazadoACdu1.getId()) != null && casoDeUsoRelaciones.get(cduEnlazadoACdu1.getId()).size() > 0) {

                                for (CasoDeUso cduEnlazadoACdu2 : casoDeUsoRelaciones.get(cduEnlazadoACdu1.getId())) {

                                    for (int i = posicionesCasosDeUso.get(cduEnlazadoACdu1.getId()).x; i < 100; i++) {

                                        if (getDiagramTabla()[i][6] == null) {

                                            getDiagramTabla()[i][6] = cduEnlazadoACdu2;
                                            cduIdsEnDiagrama.add(cduEnlazadoACdu2.getId());
                                            Relacion r = casoDeUsoRelacionesRelation.get(cduEnlazadoACdu1.getId() + "-" + cduEnlazadoACdu2.getId());
                                            posicionesCasosDeUso.put(cduEnlazadoACdu2.getId(), new Posiciones(i, 6, r.getNombre()));
                                            break;
                                        }
                                    }


                                    if (casoDeUsoRelaciones.get(cduEnlazadoACdu2.getId()) != null && casoDeUsoRelaciones.get(cduEnlazadoACdu2.getId()).size() > 0) {

                                        for (CasoDeUso cduEnlazadoACdu3 : casoDeUsoRelaciones.get(cduEnlazadoACdu2.getId())) {

                                            for (int i = posicionesCasosDeUso.get(cduEnlazadoACdu2.getId()).x; i < 100; i++) {

                                                if (getDiagramTabla()[i][8] == null) {

                                                    getDiagramTabla()[i][8] = cduEnlazadoACdu3;
                                                    Relacion r = casoDeUsoRelacionesRelation.get(cduEnlazadoACdu2.getId() + "-" + cduEnlazadoACdu3.getId());
                                                    posicionesCasosDeUso.put(cduEnlazadoACdu3.getId(), new Posiciones(i, 8, r.getNombre()));
                                                    break;
                                                }

                                            }

                                            if (casoDeUsoRelaciones.get(cduEnlazadoACdu3.getId()) != null && casoDeUsoRelaciones.get(cduEnlazadoACdu3.getId()).size() > 0) {

                                                for (CasoDeUso cduEnlazadoACdu4 : casoDeUsoRelaciones.get(cduEnlazadoACdu3.getId())) {

                                                    for (int i = posicionesCasosDeUso.get(cduEnlazadoACdu3.getId()).x; i < 100; i++) {

                                                        if (getDiagramTabla()[i][10] == null) {

                                                            getDiagramTabla()[i][10] = cduEnlazadoACdu4;
                                                            cduIdsEnDiagrama.add(cduEnlazadoACdu4.getId());
                                                            Relacion r = casoDeUsoRelacionesRelation.get(cduEnlazadoACdu3.getId() + "-" + cduEnlazadoACdu4.getId());
                                                            if (!posicionesCasosDeUso.containsKey(cduEnlazadoACdu4.getId())) {
                                                                posicionesCasosDeUso.put(cduEnlazadoACdu4.getId(), new Posiciones(i, 10, r.getNombre()));
                                                            }
                                                            break;
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //posicionar actor/caso de uso flechas
        for (Integer actorId : actoresCasosDeUso.keySet()) {

            int contador = 0;

            if (actoresCasosDeUso.get(actorId) != null && actoresCasosDeUso.get(actorId).size() > 0) {

                for (CasoDeUso cdu : actoresCasosDeUso.get(actorId)) {

                    getDiagramTabla()[(posicionesActores.get(actorId).getX()) + contador][1] = posicionesCasosDeUso.get(cdu.getId());
                    contador++;
                }
            }
        }

        //posicionar caso de uso/caso de uso flechas
        for (Integer cduId : casoDeUsoRelaciones.keySet()) {

            int contador = 0;

            if (casoDeUsoRelaciones.get(cduId) != null && casoDeUsoRelaciones.get(cduId).size() > 0) {

                for (CasoDeUso cdu : casoDeUsoRelaciones.get(cduId)) {

                    getDiagramTabla()[(posicionesCasosDeUso.get(cduId).getX()) + contador][(posicionesCasosDeUso.get(cduId).getY()) + 1] = posicionesCasosDeUso.get(cdu.getId());
                    contador++;
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

    //Esta clase tiene que ser estatica para que GSON lo pueda deserializar
    //https://sites.google.com/site/gson/gson-user-guide#TOC-Nested-Classes-including-Inner-Classes-
    public static class Posiciones {

        @Expose
        private int x;
        @Expose
        private int y;
        @Expose
        private String relacion;

        public Posiciones(int x, int y) {

            this.x = x;
            this.y = y;
        }

        public Posiciones(int x, int y, String relacion) {

            this.x = x;
            this.y = y;
            this.relacion = relacion;
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
    }
}
