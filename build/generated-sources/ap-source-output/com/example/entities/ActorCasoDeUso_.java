package com.example.entities;

import com.example.entities.Actor;
import com.example.entities.CasoDeUso;
import com.example.entities.Diagrama;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-10-06T16:13:27")
@StaticMetamodel(ActorCasoDeUso.class)
public class ActorCasoDeUso_ { 

    public static volatile SingularAttribute<ActorCasoDeUso, Integer> id;
    public static volatile SingularAttribute<ActorCasoDeUso, Diagrama> diagramid;
    public static volatile SingularAttribute<ActorCasoDeUso, CasoDeUso> casodeusoid;
    public static volatile SingularAttribute<ActorCasoDeUso, Actor> actorid;

}