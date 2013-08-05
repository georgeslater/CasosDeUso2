/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George
 */
@Entity
@Table(name = "fila")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fila.findAll", query = "SELECT f FROM Fila f"),
    @NamedQuery(name = "Fila.findById", query = "SELECT f FROM Fila f WHERE f.id = :id"),
    @NamedQuery(name = "Fila.findByDiagram", query = "SELECT f FROM Fila f WHERE f.diagramaID.id = :diagramaID")})
public class Fila implements Serializable {
    @JoinColumn(name = "DiagramaID", referencedColumnName = "ID")
    @ManyToOne
    private Diagrama diagramaID;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "CasoDeUso5ID", referencedColumnName = "ID")
    @ManyToOne
    private CasoDeUso casoDeUso5ID;
    @JoinColumn(name = "Relacion4ID", referencedColumnName = "ID")
    @ManyToOne
    private Relacion relacion4ID;
    @JoinColumn(name = "CasoDeUso4ID", referencedColumnName = "ID")
    @ManyToOne
    private CasoDeUso casoDeUso4ID;
    @JoinColumn(name = "Relacion3ID", referencedColumnName = "ID")
    @ManyToOne
    private Relacion relacion3ID;
    @JoinColumn(name = "CasoDeUso3ID", referencedColumnName = "ID")
    @ManyToOne
    private CasoDeUso casoDeUso3ID;
    @JoinColumn(name = "Relacion2ID", referencedColumnName = "ID")
    @ManyToOne
    private Relacion relacion2ID;
    @JoinColumn(name = "CasoDeUso2ID", referencedColumnName = "ID")
    @ManyToOne
    private CasoDeUso casoDeUso2ID;
    @JoinColumn(name = "Relacion1ID", referencedColumnName = "ID")
    @ManyToOne
    private Relacion relacion1ID;
    @JoinColumn(name = "ActorID", referencedColumnName = "ID")
    @ManyToOne
    private Actor actorID;
    @JoinColumn(name = "CasoDeUso1ID", referencedColumnName = "ID")
    @ManyToOne()
    private CasoDeUso casoDeUso1ID;

    public Fila() {
    }

    public Fila(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CasoDeUso getCasoDeUso5ID() {
        return casoDeUso5ID;
    }

    public void setCasoDeUso5ID(CasoDeUso casoDeUso5ID) {
        this.casoDeUso5ID = casoDeUso5ID;
    }

    public Relacion getRelacion4ID() {
        return relacion4ID;
    }

    public void setRelacion4ID(Relacion relacion4ID) {
        this.relacion4ID = relacion4ID;
    }

    public CasoDeUso getCasoDeUso4ID() {
        return casoDeUso4ID;
    }

    public void setCasoDeUso4ID(CasoDeUso casoDeUso4ID) {
        this.casoDeUso4ID = casoDeUso4ID;
    }

    public Relacion getRelacion3ID() {
        return relacion3ID;
    }

    public void setRelacion3ID(Relacion relacion3ID) {
        this.relacion3ID = relacion3ID;
    }

    public CasoDeUso getCasoDeUso3ID() {
        return casoDeUso3ID;
    }

    public void setCasoDeUso3ID(CasoDeUso casoDeUso3ID) {
        this.casoDeUso3ID = casoDeUso3ID;
    }

    public Relacion getRelacion2ID() {
        return relacion2ID;
    }

    public void setRelacion2ID(Relacion relacion2ID) {
        this.relacion2ID = relacion2ID;
    }

    public CasoDeUso getCasoDeUso2ID() {
        return casoDeUso2ID;
    }

    public void setCasoDeUso2ID(CasoDeUso casoDeUso2ID) {
        this.casoDeUso2ID = casoDeUso2ID;
    }

    public Relacion getRelacion1ID() {
        return relacion1ID;
    }

    public void setRelacion1ID(Relacion relacion1ID) {
        this.relacion1ID = relacion1ID;
    }

    public Actor getActorID() {
        return actorID;
    }

    public void setActorID(Actor actorID) {
        this.actorID = actorID;
    }

    public CasoDeUso getCasoDeUso1ID() {
        return casoDeUso1ID;
    }

    public void setCasoDeUso1ID(CasoDeUso casoDeUso1ID) {
        this.casoDeUso1ID = casoDeUso1ID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fila)) {
            return false;
        }
        Fila other = (Fila) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.entities.Fila[ id=" + id + " ]";
    }

    public Diagrama getDiagramaID() {
        return diagramaID;
    }

    public void setDiagramaID(Diagrama diagramaID) {
        this.diagramaID = diagramaID;
    }
    
}
