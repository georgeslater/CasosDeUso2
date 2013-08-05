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
    @NamedQuery(name = "CasoDeUso.findByDiagramaId", query = "SELECT c FROM CasoDeUso c WHERE c.diagramid.id = :diagramaid"),
    @NamedQuery(name = "CasoDeUso.findByNameAndDiagramaId", query = "SELECT c FROM CasoDeUso c WHERE c.diagramid.id = :diagramaid AND c.text = :text")})
public class CasoDeUso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Expose
    @Size(max = 255)
    @Column(name = "TEXT")
    private String text;
    @JoinColumn(name = "DIAGRAMID", referencedColumnName = "ID")
    @ManyToOne
    private Diagrama diagramid;
    @OneToMany(mappedBy = "casoDeUso5ID")
    private Collection<Fila> filaCollection;
    @OneToMany(mappedBy = "casoDeUso4ID")
    private Collection<Fila> filaCollection1;
    @OneToMany(mappedBy = "casoDeUso3ID")
    private Collection<Fila> filaCollection2;
    @OneToMany(mappedBy = "casoDeUso2ID")
    private Collection<Fila> filaCollection3;
    @OneToMany(mappedBy = "casoDeUso1ID")
    private Collection<Fila> filaCollection4;
    @OneToMany(mappedBy = "casodeuso2id")
    private Collection<CasosDeUsoRelaciones> casosDeUsoRelacionesCollection;
    @OneToMany(mappedBy = "casodeuso1id")
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

    public Diagrama getDiagramid() {
        return diagramid;
    }

    public void setDiagramid(Diagrama diagramid) {
        this.diagramid = diagramid;
    }

    @XmlTransient
    public Collection<Fila> getFilaCollection() {
        return filaCollection;
    }

    public void setFilaCollection(Collection<Fila> filaCollection) {
        this.filaCollection = filaCollection;
    }

    @XmlTransient
    public Collection<Fila> getFilaCollection1() {
        return filaCollection1;
    }

    public void setFilaCollection1(Collection<Fila> filaCollection1) {
        this.filaCollection1 = filaCollection1;
    }

    @XmlTransient
    public Collection<Fila> getFilaCollection2() {
        return filaCollection2;
    }

    public void setFilaCollection2(Collection<Fila> filaCollection2) {
        this.filaCollection2 = filaCollection2;
    }

    @XmlTransient
    public Collection<Fila> getFilaCollection3() {
        return filaCollection3;
    }

    public void setFilaCollection3(Collection<Fila> filaCollection3) {
        this.filaCollection3 = filaCollection3;
    }

    @XmlTransient
    public Collection<Fila> getFilaCollection4() {
        return filaCollection4;
    }

    public void setFilaCollection4(Collection<Fila> filaCollection4) {
        this.filaCollection4 = filaCollection4;
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
        return "com.example.entities.CasoDeUso[ id=" + id + " ]";
    }
    
}
