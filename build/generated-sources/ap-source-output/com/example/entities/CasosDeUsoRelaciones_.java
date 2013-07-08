package com.example.entities;

import com.example.entities.CasoDeUso;
import com.example.entities.Diagrama;
import com.example.entities.Relacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-07-07T16:38:08")
@StaticMetamodel(CasosDeUsoRelaciones.class)
public class CasosDeUsoRelaciones_ { 

    public static volatile SingularAttribute<CasosDeUsoRelaciones, Integer> id;
    public static volatile SingularAttribute<CasosDeUsoRelaciones, Diagrama> diagramid;
    public static volatile SingularAttribute<CasosDeUsoRelaciones, CasoDeUso> casodeuso2id;
    public static volatile SingularAttribute<CasosDeUsoRelaciones, Relacion> relacionid;
    public static volatile SingularAttribute<CasosDeUsoRelaciones, CasoDeUso> casodeuso1id;

}