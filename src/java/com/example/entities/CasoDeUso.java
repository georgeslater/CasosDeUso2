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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author George
 */
@Entity
@Table(name = "caso_de_uso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasoDeUso.findAll", query = "SELECT c FROM CasoDeUso c"),
    @NamedQuery(name = "CasoDeUso.findById", query = "SELECT c FROM CasoDeUso c WHERE c.id = :id"),
    @NamedQuery(name = "CasoDeUso.findByText", query = "SELECT c FROM CasoDeUso c WHERE c.text = :text"),
    @NamedQuery(name = "CasoDeUso.findByPositionx", query = "SELECT c FROM CasoDeUso c WHERE c.positionx = :positionx"),
    @NamedQuery(name = "CasoDeUso.findByPositiony", query = "SELECT c FROM CasoDeUso c WHERE c.positiony = :positiony")})
public class CasoDeUso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 255)
    @Column(name = "TEXT")
    private String text;
    @Column(name = "POSITIONX")
    private Integer positionx;
    @Column(name = "POSITIONY")
    private Integer positiony;
    @JoinColumn(name = "DIAGRAMID", referencedColumnName = "ID")
    @ManyToOne
    private Diagrama diagramid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "casodeuso2id")
    private Collection<CasosDeUsoRelaciones> casosDeUsoRelacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "casodeuso1id")
    private Collection<CasosDeUsoRelaciones> casosDeUsoRelacionesCollection1;
    @OneToMany(mappedBy = "casodeusoid")
    private Collection<ActorCasoDeUso> actorCasoDeUsoCollection;

    public CasoDeUso() {
    }

    public CasoDeUso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPositionx() {
        return positionx;
    }

    public void setPositionx(Integer positionx) {
        this.positionx = positionx;
    }

    public Integer getPositiony() {
        return positiony;
    }

    public void setPositiony(Integer positiony) {
        this.positiony = positiony;
    }

    public Diagrama getDiagramid() {
        return diagramid;
    }

    public void setDiagramid(Diagrama diagramid) {
        this.diagramid = diagramid;
    }

    @XmlTransient
    public Collection<CasosDeUsoRelaciones> getCasosDeUsoRelacionesCollection() {
        return casosDeUsoRelacionesCollection;
    }

    public void setCasosDeUsoRelacionesCollection(Collection<CasosDeUsoRelaciones> casosDeUsoRelacionesCollection) {
        this.casosDeUsoRelacionesCollection = casosDeUsoRelacionesCollection;
    }

    @XmlTransient
    public Collection<CasosDeUsoRelaciones> getCasosDeUsoRelacionesCollection1() {
        return casosDeUsoRelacionesCollection1;
    }

    public void setCasosDeUsoRelacionesCollection1(Collection<CasosDeUsoRelaciones> casosDeUsoRelacionesCollection1) {
        this.casosDeUsoRelacionesCollection1 = casosDeUsoRelacionesCollection1;
    }

    @XmlTransient
    public Collection<ActorCasoDeUso> getActorCasoDeUsoCollection() {
        return actorCasoDeUsoCollection;
    }

    public void setActorCasoDeUsoCollection(Collection<ActorCasoDeUso> actorCasoDeUsoCollection) {
        this.actorCasoDeUsoCollection = actorCasoDeUsoCollection;
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
        if (!(object instanceof CasoDeUso)) {
            return false;
        }
        CasoDeUso other = (CasoDeUso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.controllers.CasoDeUso[ id=" + id + " ]";
    }
    
}
