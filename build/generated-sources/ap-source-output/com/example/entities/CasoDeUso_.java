package com.example.entities;

import com.example.entities.ActorCasoDeUso;
import com.example.entities.CasosDeUsoRelaciones;
import com.example.entities.Diagrama;
import com.example.entities.Fila;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-09-29T17:24:19")
@StaticMetamodel(CasoDeUso.class)
public class CasoDeUso_ { 

    public static volatile SingularAttribute<CasoDeUso, Integer> id;
    public static volatile CollectionAttribute<CasoDeUso, CasosDeUsoRelaciones> casosDeUsoRelacionesCollection;
    public static volatile CollectionAttribute<CasoDeUso, ActorCasoDeUso> actorCasoDeUsoCollection;
    public static volatile SingularAttribute<CasoDeUso, String> text;
    public static volatile SingularAttribute<CasoDeUso, Diagrama> diagramid;
    public static volatile CollectionAttribute<CasoDeUso, CasosDeUsoRelaciones> casosDeUsoRelacionesCollection1;
    public static volatile CollectionAttribute<CasoDeUso, Fila> filaCollection1;
    public static volatile CollectionAttribute<CasoDeUso, Fila> filaCollection;
    public static volatile CollectionAttribute<CasoDeUso, Fila> filaCollection4;
    public static volatile CollectionAttribute<CasoDeUso, Fila> filaCollection2;
    public static volatile CollectionAttribute<CasoDeUso, Fila> filaCollection3;

}