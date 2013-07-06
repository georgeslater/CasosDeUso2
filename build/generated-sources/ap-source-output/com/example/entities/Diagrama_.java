package com.example.entities;

import com.example.entities.Actor;
import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasoDeUso;
import com.example.entities.CasosDeUsoRelaciones;
import com.example.entities.Image;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-07-03T21:37:44")
@StaticMetamodel(Diagrama.class)
public class Diagrama_ { 

    public static volatile SingularAttribute<Diagrama, Integer> id;
    public static volatile SingularAttribute<Diagrama, String> nombre;
    public static volatile CollectionAttribute<Diagrama, CasosDeUsoRelaciones> casosDeUsoRelacionesCollection;
    public static volatile CollectionAttribute<Diagrama, ActorCasoDeUso> actorCasoDeUsoCollection;
    public static volatile CollectionAttribute<Diagrama, CasoDeUso> casoDeUsoCollection;
    public static volatile SingularAttribute<Diagrama, Integer> userid;
    public static volatile CollectionAttribute<Diagrama, Actor> actorCollection;
    public static volatile CollectionAttribute<Diagrama, Image> imageCollection;

}