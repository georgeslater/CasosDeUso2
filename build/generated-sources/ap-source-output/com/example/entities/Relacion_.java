package com.example.entities;

import com.example.entities.CasosDeUsoRelaciones;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-07-03T21:37:44")
@StaticMetamodel(Relacion.class)
public class Relacion_ { 

    public static volatile SingularAttribute<Relacion, Integer> id;
    public static volatile SingularAttribute<Relacion, String> nombre;
    public static volatile CollectionAttribute<Relacion, CasosDeUsoRelaciones> casosDeUsoRelacionesCollection;

}