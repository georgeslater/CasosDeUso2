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
@Table(name = "actor_caso_de_uso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActorCasoDeUso.findAll", query = "SELECT a FROM ActorCasoDeUso a"),
    @NamedQuery(name = "ActorCasoDeUso.findById", query = "SELECT a FROM ActorCasoDeUso a WHERE a.id = :id"),
    @NamedQuery(name = "ActorCasoDeUso.findByDiagramaId", query = "SELECT a FROM ActorCasoDeUso a WHERE a.diagramid.id = :diagramaid")})
public class ActorCasoDeUso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "DIAGRAMID", referencedColumnName = "ID")
    @ManyToOne
    private Diagrama diagramid;
    @JoinColumn(name = "CASODEUSOID", referencedColumnName = "ID")
    @ManyToOne
    private CasoDeUso casodeusoid;
    @JoinColumn(name = "ACTORID", referencedColumnName = "ID")
    @ManyToOne
    private Actor actorid;

    public ActorCasoDeUso() {
    }

    public ActorCasoDeUso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Diagrama getDiagramid() {
        return diagramid;
    }

    public void setDiagramid(Diagrama diagramid) {
        this.diagramid = diagramid;
    }

    public CasoDeUso getCasodeusoid() {
        return casodeusoid;
    }

    public void setCasodeusoid(CasoDeUso casodeusoid) {
        this.casodeusoid = casodeusoid;
    }

    public Actor getActorid() {
        return actorid;
    }

    public void setActorid(Actor actorid) {
        this.actorid = actorid;
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
        if (!(object instanceof ActorCasoDeUso)) {
            return false;
        }
        ActorCasoDeUso other = (ActorCasoDeUso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.entities.ActorCasoDeUso[ id=" + id + " ]";
    }
    
}
