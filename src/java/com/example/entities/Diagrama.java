/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "diagrama")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diagrama.findAll", query = "SELECT d FROM Diagrama d"),
    @NamedQuery(name = "Diagrama.findById", query = "SELECT d FROM Diagrama d WHERE d.id = :id"),
    @NamedQuery(name = "Diagrama.findByNombre", query = "SELECT d FROM Diagrama d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Diagrama.findByUserid", query = "SELECT d FROM Diagrama d WHERE d.userid = :userid")})
public class Diagrama implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "USERID")
    private Integer userid;
    @OneToMany(mappedBy = "diagramid")
    private Collection<CasoDeUso> casoDeUsoCollection;
    @OneToMany(mappedBy = "diagramid")
    private Collection<CasosDeUsoRelaciones> casosDeUsoRelacionesCollection;
    @OneToMany(mappedBy = "diagramid")
    private Collection<ActorCasoDeUso> actorCasoDeUsoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagramID")
    private Collection<Image> imageCollection;
    @OneToMany(mappedBy = "diagramid")
    private Collection<Actor> actorCollection;

    public Diagrama() {
    }

    public Diagrama(Integer id) {
        this.id = id;
    }

    public Diagrama(Integer id, String nombre) {
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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @XmlTransient
    public Collection<CasoDeUso> getCasoDeUsoCollection() {
        return casoDeUsoCollection;
    }

    public void setCasoDeUsoCollection(Collection<CasoDeUso> casoDeUsoCollection) {
        this.casoDeUsoCollection = casoDeUsoCollection;
    }

    @XmlTransient
    public Collection<CasosDeUsoRelaciones> getCasosDeUsoRelacionesCollection() {
        return casosDeUsoRelacionesCollection;
    }

    public void setCasosDeUsoRelacionesCollection(Collection<CasosDeUsoRelaciones> casosDeUsoRelacionesCollection) {
        this.casosDeUsoRelacionesCollection = casosDeUsoRelacionesCollection;
    }

    @XmlTransient
    public Collection<ActorCasoDeUso> getActorCasoDeUsoCollection() {
        return actorCasoDeUsoCollection;
    }

    public void setActorCasoDeUsoCollection(Collection<ActorCasoDeUso> actorCasoDeUsoCollection) {
        this.actorCasoDeUsoCollection = actorCasoDeUsoCollection;
    }

    @XmlTransient
    public Collection<Image> getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection) {
        this.imageCollection = imageCollection;
    }

    @XmlTransient
    public Collection<Actor> getActorCollection() {
        return actorCollection;
    }

    public void setActorCollection(Collection<Actor> actorCollection) {
        this.actorCollection = actorCollection;
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
        if (!(object instanceof Diagrama)) {
            return false;
        }
        Diagrama other = (Diagrama) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.controllers.Diagrama[ id=" + id + " ]";
    }
    
}
