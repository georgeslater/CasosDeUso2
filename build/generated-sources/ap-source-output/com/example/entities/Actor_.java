package com.example.entities;

import com.example.entities.ActorCasoDeUso;
import com.example.entities.Diagrama;
import com.example.entities.Fila;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-09-17T20:52:00")
@StaticMetamodel(Actor.class)
public class Actor_ { 

    public static volatile SingularAttribute<Actor, Integer> id;
    public static volatile SingularAttribute<Actor, String> nombre;
    public static volatile CollectionAttribute<Actor, ActorCasoDeUso> actorCasoDeUsoCollection;
    public static volatile SingularAttribute<Actor, Diagrama> diagramid;
    public static volatile CollectionAttribute<Actor, Fila> filaCollection;

}