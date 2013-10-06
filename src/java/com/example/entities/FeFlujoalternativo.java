/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "fe_flujoalternativo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeFlujoalternativo.findAll", query = "SELECT f FROM FeFlujoalternativo f"),
    @NamedQuery(name = "FeFlujoalternativo.findById", query = "SELECT f FROM FeFlujoalternativo f WHERE f.id = :id"),
    @NamedQuery(name = "FeFlujoalternativo.findByOrden", query = "SELECT f FROM FeFlujoalternativo f WHERE f.orden = :orden"),
    @NamedQuery(name = "FeFlujoalternativo.findByFlujosNormales", query = "SELECT f FROM FeFlujoalternativo f WHERE f.fEFlujoNormalID.id IN :fnList")})
public class FeFlujoalternativo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Orden")
    private Integer orden;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Descripcion")
    private String descripcion;
    @JoinColumn(name = "FEFlujoNormalID", referencedColumnName = "ID")
    @ManyToOne
    private FeFlujonormal fEFlujoNormalID;
    @OneToMany(mappedBy = "fEFlujoAlternativoID")
    private Collection<FeFlujoalternativopaso> feFlujoalternativopasoCollection;

    public FeFlujoalternativo() {
    }

    public FeFlujoalternativo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public FeFlujonormal getFEFlujoNormalID() {
        return fEFlujoNormalID;
    }

    public void setFEFlujoNormalID(FeFlujonormal fEFlujoNormalID) {
        this.fEFlujoNormalID = fEFlujoNormalID;
    }

    @XmlTransient
    public Collection<FeFlujoalternativopaso> getFeFlujoalternativopasoCollection() {
        return feFlujoalternativopasoCollection;
    }

    public void setFeFlujoalternativopasoCollection(Collection<FeFlujoalternativopaso> feFlujoalternativopasoCollection) {
        this.feFlujoalternativopasoCollection = feFlujoalternativopasoCollection;
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
        if (!(object instanceof FeFlujoalternativo)) {
            return false;
        }
        FeFlujoalternativo other = (FeFlujoalternativo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.entities.FeFlujoalternativo[ id=" + id + " ]";
    }
    
}
