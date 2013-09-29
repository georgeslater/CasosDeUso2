package com.example.entities;

import com.example.entities.Actor;
import com.example.entities.FeEncabezado;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-09-29T17:24:19")
@StaticMetamodel(FeActor.class)
public class FeActor_ { 

    public static volatile SingularAttribute<FeActor, Integer> id;
    public static volatile SingularAttribute<FeActor, FeEncabezado> feEncabezado;
    public static volatile SingularAttribute<FeActor, Actor> feActor;

}