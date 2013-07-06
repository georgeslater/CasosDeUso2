package com.example.entities;

import com.example.entities.Diagrama;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-07-03T21:37:44")
@StaticMetamodel(Actor.class)
public class Actor_ { 

    public static volatile SingularAttribute<Actor, Integer> id;
    public static volatile SingularAttribute<Actor, String> nombre;
    public static volatile SingularAttribute<Actor, Diagrama> diagramid;
    public static volatile SingularAttribute<Actor, Integer> positionx;
    public static volatile SingularAttribute<Actor, Integer> positiony;

}