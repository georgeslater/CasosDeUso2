package com.example.entities;

import com.example.entities.Diagrama;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-07-07T16:38:08")
@StaticMetamodel(Image.class)
public class Image_ { 

    public static volatile SingularAttribute<Image, Integer> id;
    public static volatile SingularAttribute<Image, byte[]> body;
    public static volatile SingularAttribute<Image, String> title;
    public static volatile SingularAttribute<Image, Diagrama> diagramID;

}