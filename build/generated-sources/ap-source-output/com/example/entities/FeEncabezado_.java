package com.example.entities;

import com.example.entities.CasoDeUso;
import com.example.entities.FeActor;
import com.example.entities.FeCasoDeUsoExtiende;
import com.example.entities.FeCasoDeUsoIncluye;
import com.example.entities.FeCasoDeUsoPdext;
import com.example.entities.FeCasoDeUsoPdinc;
import com.example.entities.FeFlujonormal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-09-29T17:24:19")
@StaticMetamodel(FeEncabezado.class)
public class FeEncabezado_ { 

    public static volatile SingularAttribute<FeEncabezado, Integer> id;
    public static volatile CollectionAttribute<FeEncabezado, FeCasoDeUsoIncluye> feCasoDeUsoIncluyeCollection;
    public static volatile SingularAttribute<FeEncabezado, CasoDeUso> casoDeUso;
    public static volatile CollectionAttribute<FeEncabezado, FeCasoDeUsoPdext> feCasoDeUsoPdextCollection;
    public static volatile CollectionAttribute<FeEncabezado, FeFlujonormal> feFlujonormalCollection;
    public static volatile SingularAttribute<FeEncabezado, String> postcondiciones;
    public static volatile SingularAttribute<FeEncabezado, String> precondiciones;
    public static volatile CollectionAttribute<FeEncabezado, FeCasoDeUsoExtiende> feCasoDeUsoExtiendeCollection;
    public static volatile SingularAttribute<FeEncabezado, String> prioridad;
    public static volatile CollectionAttribute<FeEncabezado, FeActor> feActorCollection;
    public static volatile CollectionAttribute<FeEncabezado, FeCasoDeUsoPdinc> feCasoDeUsoPdincCollection;

}