package com.example.entities;

import com.example.entities.FeFlujoalternativopaso;
import com.example.entities.FeFlujonormal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-10-12T17:49:42")
@StaticMetamodel(FeFlujoalternativo.class)
public class FeFlujoalternativo_ { 

    public static volatile SingularAttribute<FeFlujoalternativo, Integer> id;
    public static volatile SingularAttribute<FeFlujoalternativo, Integer> orden;
    public static volatile SingularAttribute<FeFlujoalternativo, FeFlujonormal> fEFlujoNormalID;
    public static volatile CollectionAttribute<FeFlujoalternativo, FeFlujoalternativopaso> feFlujoalternativopasoCollection;
    public static volatile SingularAttribute<FeFlujoalternativo, String> descripcion;

}