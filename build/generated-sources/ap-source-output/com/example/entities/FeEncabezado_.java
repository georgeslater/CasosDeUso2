package com.example.entities;

import com.example.entities.CasoDeUso;
import com.example.entities.FeFlujonormal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-10-06T16:13:27")
@StaticMetamodel(FeEncabezado.class)
public class FeEncabezado_ { 

    public static volatile SingularAttribute<FeEncabezado, Integer> id;
    public static volatile SingularAttribute<FeEncabezado, CasoDeUso> casoDeUso;
    public static volatile CollectionAttribute<FeEncabezado, FeFlujonormal> feFlujonormalCollection;
    public static volatile SingularAttribute<FeEncabezado, String> postcondiciones;
    public static volatile SingularAttribute<FeEncabezado, String> precondiciones;
    public static volatile SingularAttribute<FeEncabezado, String> prioridad;

}