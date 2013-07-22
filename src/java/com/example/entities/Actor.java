/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author George
 */
@Entity
@Table(name = "actor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a"),
    @NamedQuery(name = "Actor.findById", query = "SELECT a FROM Actor a WHERE a.id = :id"),
    @NamedQuery(name = "Actor.findByNombre", query = "SELECT a FROM Actor a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Actor.findByDiagramaId", query = "SELECT a FROM Actor a WHERE a.diagramid.id = :diagramaid")})
public class Actor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Expose
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "actorID")
    private Collection<Fila> filaCollection;
    @OneToMany(mappedBy = "actorid")
    private Collection<ActorCasoDeUso> actorCasoDeUsoCollection;
    @JoinColumn(name = "DIAGRAMID", referencedColumnName = "ID")
    @ManyToOne
    private Diagrama diagramid;

    public Actor() {
    }

    public Actor(Integer id) {
        this.id = id;
    }

    public Actor(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Fila> getFilaCollection() {
        return filaCollection;
    }

    public void setFilaCollection(Collection<Fila> filaCollection) {
        this.filaCollection = filaCollection;
    }

    @XmlTransient
    public Collection<ActorCasoDeUso> getActorCasoDeUsoCollection() {
        return actorCasoDeUsoCollection;
    }

    public void setActorCasoDeUsoCollection(Collection<ActorCasoDeUso> actorCasoDeUsoCollection) {
        this.actorCasoDeUsoCollection = actorCasoDeUsoCollection;
    }

    public Diagrama getDiagramid() {
        return diagramid;
    }

    public void setDiagramid(Diagrama diagramid) {
        this.diagramid = diagramid;
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
        if (!(object instanceof Actor)) {
            return false;
        }
        Actor other = (Actor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.entities.Actor[ id=" + id + " ]";
    }
    
}
