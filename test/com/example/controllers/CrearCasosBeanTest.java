/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.entities.Actor;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.Diagrama;
import com.example.entities.Fila;
import com.example.entities.Image;
import com.example.entities.Relacion;
import com.example.entities.UsuarioTable;
import com.example.negocio.CrearCasosService;
import com.example.negocio.DibujarService;
import com.example.negocio.EncryptionService;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author George
 */
public class CrearCasosBeanTest {
    
    public CrearCasosBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of borrarFila method, of class CrearCasosBean.
     */
    @Test
    public void testBorrarFila() {
        System.out.println("borrarFila");
        CrearCasosBean instance = new CrearCasosBean();
        String expResult = "";
        String result = instance.borrarFila();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRow method, of class CrearCasosBean.
     */
    @Test
    public void testAddRow() {
        System.out.println("addRow");
        CrearCasosBean instance = new CrearCasosBean();
        String expResult = "";
        String result = instance.addRow();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargarValores method, of class CrearCasosBean.
     */
    @Test
    public void testCargarValores() {
        System.out.println("cargarValores");
        CrearCasosBean instance = new CrearCasosBean();
        instance.cargarValores();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardarImagen method, of class CrearCasosBean.
     */
    @Test
    public void testGuardarImagen() {
        System.out.println("guardarImagen");
        CrearCasosBean instance = new CrearCasosBean();
        instance.guardarImagen();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of evaluarDialogsEliminar method, of class CrearCasosBean.
     */
    @Test
    public void testEvaluarDialogsEliminar() {
        System.out.println("evaluarDialogsEliminar");
        CrearCasosBean instance = new CrearCasosBean();
        instance.evaluarDialogsEliminar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarActor method, of class CrearCasosBean.
     */
    @Test
    public void testAgregarActor() {
        System.out.println("agregarActor");
        CrearCasosBean instance = new CrearCasosBean();
        instance.agregarActor();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarCdu method, of class CrearCasosBean.
     */
    @Test
    public void testAgregarCdu() {
        System.out.println("agregarCdu");
        CrearCasosBean instance = new CrearCasosBean();
        instance.agregarCdu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generarDiagrama method, of class CrearCasosBean.
     */
    @Test
    public void testGenerarDiagrama() {
        System.out.println("generarDiagrama");
        CrearCasosBean instance = new CrearCasosBean();
        instance.generarDiagrama();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardarInformacionFilas method, of class CrearCasosBean.
     */
    @Test
    public void testGuardarInformacionFilas() {
        System.out.println("guardarInformacionFilas");
        CrearCasosBean instance = new CrearCasosBean();
        instance.guardarInformacionFilas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarRelacion method, of class CrearCasosBean.
     */
    @Test
    public void testAgregarRelacion() {
        System.out.println("agregarRelacion");
        CrearCasosBean instance = new CrearCasosBean();
        instance.agregarRelacion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of precargarFilas method, of class CrearCasosBean.
     */
    @Test
    public void testPrecargarFilas() {
        System.out.println("precargarFilas");
        CrearCasosBean instance = new CrearCasosBean();
        Set expResult = null;
        Set result = instance.precargarFilas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTheyPositionSelected method, of class CrearCasosBean.
     */
    @Test
    public void testSetTheyPositionSelected() {
        System.out.println("setTheyPositionSelected");
        int yPositionSelected = 0;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setTheyPositionSelected(yPositionSelected);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCduActual method, of class CrearCasosBean.
     */
    @Test
    public void testGetCduActual() {
        System.out.println("getCduActual");
        CrearCasosBean instance = new CrearCasosBean();
        CasoDeUso expResult = null;
        CasoDeUso result = instance.getCduActual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCduActual method, of class CrearCasosBean.
     */
    @Test
    public void testSetCduActual() {
        System.out.println("setCduActual");
        CasoDeUso cduActual = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setCduActual(cduActual);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreNuevoCdu method, of class CrearCasosBean.
     */
    @Test
    public void testGetNombreNuevoCdu() {
        System.out.println("getNombreNuevoCdu");
        CrearCasosBean instance = new CrearCasosBean();
        String expResult = "";
        String result = instance.getNombreNuevoCdu();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombreNuevoCdu method, of class CrearCasosBean.
     */
    @Test
    public void testSetNombreNuevoCdu() {
        System.out.println("setNombreNuevoCdu");
        String nombreNuevoCdu = "";
        CrearCasosBean instance = new CrearCasosBean();
        instance.setNombreNuevoCdu(nombreNuevoCdu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRelacion method, of class CrearCasosBean.
     */
    @Test
    public void testGetRelacion() {
        System.out.println("getRelacion");
        CrearCasosBean instance = new CrearCasosBean();
        String expResult = "";
        String result = instance.getRelacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRelacion method, of class CrearCasosBean.
     */
    @Test
    public void testSetRelacion() {
        System.out.println("setRelacion");
        String relacion = "";
        CrearCasosBean instance = new CrearCasosBean();
        instance.setRelacion(relacion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActCdu method, of class CrearCasosBean.
     */
    @Test
    public void testGetActCdu() {
        System.out.println("getActCdu");
        CrearCasosBean instance = new CrearCasosBean();
        List expResult = null;
        List result = instance.getActCdu();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActCdu method, of class CrearCasosBean.
     */
    @Test
    public void testSetActCdu() {
        System.out.println("setActCdu");
        List<ActorCasoDeUso> actCdu = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setActCdu(actCdu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFilas method, of class CrearCasosBean.
     */
    @Test
    public void testGetFilas() {
        System.out.println("getFilas");
        CrearCasosBean instance = new CrearCasosBean();
        List expResult = null;
        List result = instance.getFilas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFilas method, of class CrearCasosBean.
     */
    @Test
    public void testSetFilas() {
        System.out.println("setFilas");
        List<Fila> filas = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setFilas(filas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuarioLogueado method, of class CrearCasosBean.
     */
    @Test
    public void testGetUsuarioLogueado() {
        System.out.println("getUsuarioLogueado");
        CrearCasosBean instance = new CrearCasosBean();
        UsuarioTable expResult = null;
        UsuarioTable result = instance.getUsuarioLogueado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsuarioLogueado method, of class CrearCasosBean.
     */
    @Test
    public void testSetUsuarioLogueado() {
        System.out.println("setUsuarioLogueado");
        UsuarioTable usuarioLogueado = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setUsuarioLogueado(usuarioLogueado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiagramaID method, of class CrearCasosBean.
     */
    @Test
    public void testGetDiagramaID() {
        System.out.println("getDiagramaID");
        CrearCasosBean instance = new CrearCasosBean();
        String expResult = "";
        String result = instance.getDiagramaID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDiagramaID method, of class CrearCasosBean.
     */
    @Test
    public void testSetDiagramaID() {
        System.out.println("setDiagramaID");
        String diagramaID = "";
        CrearCasosBean instance = new CrearCasosBean();
        instance.setDiagramaID(diagramaID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCrearCasosService method, of class CrearCasosBean.
     */
    @Test
    public void testGetCrearCasosService() {
        System.out.println("getCrearCasosService");
        CrearCasosBean instance = new CrearCasosBean();
        CrearCasosService expResult = null;
        CrearCasosService result = instance.getCrearCasosService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCrearCasosService method, of class CrearCasosBean.
     */
    @Test
    public void testSetCrearCasosService() {
        System.out.println("setCrearCasosService");
        CrearCasosService crearCasosService = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setCrearCasosService(crearCasosService);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJson method, of class CrearCasosBean.
     */
    @Test
    public void testGetJson() {
        System.out.println("getJson");
        CrearCasosBean instance = new CrearCasosBean();
        String expResult = "";
        String result = instance.getJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setJson method, of class CrearCasosBean.
     */
    @Test
    public void testSetJson() {
        System.out.println("setJson");
        String json = "";
        CrearCasosBean instance = new CrearCasosBean();
        instance.setJson(json);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDibujarService method, of class CrearCasosBean.
     */
    @Test
    public void testGetDibujarService() {
        System.out.println("getDibujarService");
        CrearCasosBean instance = new CrearCasosBean();
        DibujarService expResult = null;
        DibujarService result = instance.getDibujarService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDibujarService method, of class CrearCasosBean.
     */
    @Test
    public void testSetDibujarService() {
        System.out.println("setDibujarService");
        DibujarService dibujarService = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setDibujarService(dibujarService);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActores method, of class CrearCasosBean.
     */
    @Test
    public void testGetActores() {
        System.out.println("getActores");
        CrearCasosBean instance = new CrearCasosBean();
        List expResult = null;
        List result = instance.getActores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActores method, of class CrearCasosBean.
     */
    @Test
    public void testSetActores() {
        System.out.println("setActores");
        List<Actor> actores = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setActores(actores);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActorActual method, of class CrearCasosBean.
     */
    @Test
    public void testGetActorActual() {
        System.out.println("getActorActual");
        CrearCasosBean instance = new CrearCasosBean();
        Actor expResult = null;
        Actor result = instance.getActorActual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActorActual method, of class CrearCasosBean.
     */
    @Test
    public void testSetActorActual() {
        System.out.println("setActorActual");
        Actor actorActual = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setActorActual(actorActual);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreNuevoActor method, of class CrearCasosBean.
     */
    @Test
    public void testGetNombreNuevoActor() {
        System.out.println("getNombreNuevoActor");
        CrearCasosBean instance = new CrearCasosBean();
        String expResult = "";
        String result = instance.getNombreNuevoActor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombreNuevoActor method, of class CrearCasosBean.
     */
    @Test
    public void testSetNombreNuevoActor() {
        System.out.println("setNombreNuevoActor");
        String nombreNuevoActor = "";
        CrearCasosBean instance = new CrearCasosBean();
        instance.setNombreNuevoActor(nombreNuevoActor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRelaciones method, of class CrearCasosBean.
     */
    @Test
    public void testGetRelaciones() {
        System.out.println("getRelaciones");
        CrearCasosBean instance = new CrearCasosBean();
        List expResult = null;
        List result = instance.getRelaciones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRelaciones method, of class CrearCasosBean.
     */
    @Test
    public void testSetRelaciones() {
        System.out.println("setRelaciones");
        List<Relacion> relaciones = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setRelaciones(relaciones);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCdus method, of class CrearCasosBean.
     */
    @Test
    public void testGetCdus() {
        System.out.println("getCdus");
        CrearCasosBean instance = new CrearCasosBean();
        List expResult = null;
        List result = instance.getCdus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCdus method, of class CrearCasosBean.
     */
    @Test
    public void testSetCdus() {
        System.out.println("setCdus");
        List<CasoDeUso> cdus = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setCdus(cdus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getThexPositionSelected method, of class CrearCasosBean.
     */
    @Test
    public void testGetThexPositionSelected() {
        System.out.println("getThexPositionSelected");
        CrearCasosBean instance = new CrearCasosBean();
        int expResult = 0;
        int result = instance.getThexPositionSelected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setThexPositionSelected method, of class CrearCasosBean.
     */
    @Test
    public void testSetThexPositionSelected() {
        System.out.println("setThexPositionSelected");
        int xPositionSelected = 0;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setThexPositionSelected(xPositionSelected);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTheyPositionSelected method, of class CrearCasosBean.
     */
    @Test
    public void testGetTheyPositionSelected() {
        System.out.println("getTheyPositionSelected");
        CrearCasosBean instance = new CrearCasosBean();
        int expResult = 0;
        int result = instance.getTheyPositionSelected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFilaABorrar method, of class CrearCasosBean.
     */
    @Test
    public void testGetFilaABorrar() {
        System.out.println("getFilaABorrar");
        CrearCasosBean instance = new CrearCasosBean();
        int expResult = 0;
        int result = instance.getFilaABorrar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFilaABorrar method, of class CrearCasosBean.
     */
    @Test
    public void testSetFilaABorrar() {
        System.out.println("setFilaABorrar");
        int filaABorrar = 0;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setFilaABorrar(filaABorrar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCasoTabla method, of class CrearCasosBean.
     */
    @Test
    public void testGetCasoTabla() {
        System.out.println("getCasoTabla");
        CrearCasosBean instance = new CrearCasosBean();
        DataTable expResult = null;
        DataTable result = instance.getCasoTabla();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCasoTabla method, of class CrearCasosBean.
     */
    @Test
    public void testSetCasoTabla() {
        System.out.println("setCasoTabla");
        DataTable casoTabla = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setCasoTabla(casoTabla);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFilasActores method, of class CrearCasosBean.
     */
    @Test
    public void testGetFilasActores() {
        System.out.println("getFilasActores");
        CrearCasosBean instance = new CrearCasosBean();
        Set expResult = null;
        Set result = instance.getFilasActores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFilasCdus method, of class CrearCasosBean.
     */
    @Test
    public void testGetFilasCdus() {
        System.out.println("getFilasCdus");
        CrearCasosBean instance = new CrearCasosBean();
        List expResult = null;
        List result = instance.getFilasCdus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFilasCdus method, of class CrearCasosBean.
     */
    @Test
    public void testSetFilasCdus() {
        System.out.println("setFilasCdus");
        List<CasoDeUso> filasCdus = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setFilasCdus(filasCdus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFilasActores method, of class CrearCasosBean.
     */
    @Test
    public void testSetFilasActores() {
        System.out.println("setFilasActores");
        Set<Actor> filasActores = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setFilasActores(filasActores);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataURL method, of class CrearCasosBean.
     */
    @Test
    public void testGetDataURL() {
        System.out.println("getDataURL");
        CrearCasosBean instance = new CrearCasosBean();
        String expResult = "";
        String result = instance.getDataURL();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataURL method, of class CrearCasosBean.
     */
    @Test
    public void testSetDataURL() {
        System.out.println("setDataURL");
        String dataURL = "";
        CrearCasosBean instance = new CrearCasosBean();
        instance.setDataURL(dataURL);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiagramaActual method, of class CrearCasosBean.
     */
    @Test
    public void testGetDiagramaActual() {
        System.out.println("getDiagramaActual");
        CrearCasosBean instance = new CrearCasosBean();
        Diagrama expResult = null;
        Diagrama result = instance.getDiagramaActual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDiagramaActual method, of class CrearCasosBean.
     */
    @Test
    public void testSetDiagramaActual() {
        System.out.println("setDiagramaActual");
        Diagrama diagramaActual = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setDiagramaActual(diagramaActual);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEncryptService method, of class CrearCasosBean.
     */
    @Test
    public void testGetEncryptService() {
        System.out.println("getEncryptService");
        CrearCasosBean instance = new CrearCasosBean();
        EncryptionService expResult = null;
        EncryptionService result = instance.getEncryptService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEncryptService method, of class CrearCasosBean.
     */
    @Test
    public void testSetEncryptService() {
        System.out.println("setEncryptService");
        EncryptionService encryptService = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setEncryptService(encryptService);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMostrarImagenEstatico method, of class CrearCasosBean.
     */
    @Test
    public void testGetMostrarImagenEstatico() {
        System.out.println("getMostrarImagenEstatico");
        CrearCasosBean instance = new CrearCasosBean();
        Boolean expResult = null;
        Boolean result = instance.getMostrarImagenEstatico();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMostrarImagenEstatico method, of class CrearCasosBean.
     */
    @Test
    public void testSetMostrarImagenEstatico() {
        System.out.println("setMostrarImagenEstatico");
        Boolean mostrarImagenEstatico = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setMostrarImagenEstatico(mostrarImagenEstatico);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiagramaImagen method, of class CrearCasosBean.
     */
    @Test
    public void testGetDiagramaImagen() {
        System.out.println("getDiagramaImagen");
        CrearCasosBean instance = new CrearCasosBean();
        Image expResult = null;
        Image result = instance.getDiagramaImagen();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDiagramaImagen method, of class CrearCasosBean.
     */
    @Test
    public void testSetDiagramaImagen() {
        System.out.println("setDiagramaImagen");
        Image diagramaImagen = null;
        CrearCasosBean instance = new CrearCasosBean();
        instance.setDiagramaImagen(diagramaImagen);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
