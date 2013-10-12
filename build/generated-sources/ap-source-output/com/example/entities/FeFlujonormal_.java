package com.example.entities;

import com.example.entities.FeEncabezado;
import com.example.entities.FeFlujoalternativo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-10-12T17:49:42")
@StaticMetamodel(FeFlujonormal.class)
public class FeFlujonormal_ { 

    public static volatile SingularAttribute<FeFlujonormal, Integer> id;
    public static volatile SingularAttribute<FeFlujonormal, Integer> orden;
    public static volatile CollectionAttribute<FeFlujonormal, FeFlujoalternativo> feFlujoalternativoCollection;
    public static volatile SingularAttribute<FeFlujonormal, FeEncabezado> fEEncabezadoID;
    public static volatile SingularAttribute<FeFlujonormal, String> descripcion;

}