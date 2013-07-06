/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "casos_de_uso_relaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasosDeUsoRelaciones.findAll", query = "SELECT c FROM CasosDeUsoRelaciones c"),
    @NamedQuery(name = "CasosDeUsoRelaciones.findById", query = "SELECT c FROM CasosDeUsoRelaciones c WHERE c.id = :id")})
public class CasosDeUsoRelaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "DIAGRAMID", referencedColumnName = "ID")
    @ManyToOne
    private Diagrama diagramid;
    @JoinColumn(name = "RELACIONID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Relacion relacionid;
    @JoinColumn(name = "CASODEUSO2ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CasoDeUso casodeuso2id;
    @JoinColumn(name = "CASODEUSO1ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CasoDeUso casodeuso1id;

    public CasosDeUsoRelaciones() {
    }

    public CasosDeUsoRelaciones(Integer id) {
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

    public Relacion getRelacionid() {
        return relacionid;
    }

    public void setRelacionid(Relacion relacionid) {
        this.relacionid = relacionid;
    }

    public CasoDeUso getCasodeuso2id() {
        return casodeuso2id;
    }

    public void setCasodeuso2id(CasoDeUso casodeuso2id) {
        this.casodeuso2id = casodeuso2id;
    }

    public CasoDeUso getCasodeuso1id() {
        return casodeuso1id;
    }

    public void setCasodeuso1id(CasoDeUso casodeuso1id) {
        this.casodeuso1id = casodeuso1id;
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
        if (!(object instanceof CasosDeUsoRelaciones)) {
            return false;
        }
        CasosDeUsoRelaciones other = (CasosDeUsoRelaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.controllers.CasosDeUsoRelaciones[ id=" + id + " ]";
    }
    
}
