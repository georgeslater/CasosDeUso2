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
@Table(name = "fe_flujonormal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeFlujonormal.findAll", query = "SELECT f FROM FeFlujonormal f"),
    @NamedQuery(name = "FeFlujonormal.findById", query = "SELECT f FROM FeFlujonormal f WHERE f.id = :id"),
    @NamedQuery(name = "FeFlujonormal.findByOrden", query = "SELECT f FROM FeFlujonormal f WHERE f.orden = :orden"),
    @NamedQuery(name = "FeFlujonormal.findByFeEncabezado", query = "SELECT f FROM FeFlujonormal f WHERE f.fEEncabezadoID = :feEnc ORDER BY f.orden asc")})
public class FeFlujonormal implements Serializable {
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
    @JoinColumn(name = "FEEncabezadoID", referencedColumnName = "ID")
    @ManyToOne
    private FeEncabezado fEEncabezadoID;
    @OneToMany(mappedBy = "fEFlujoNormalID")
    private Collection<FeFlujoalternativo> feFlujoalternativoCollection;

    public FeFlujonormal() {
    }

    public FeFlujonormal(Integer id) {
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

    public FeEncabezado getFEEncabezadoID() {
        return fEEncabezadoID;
    }

    public void setFEEncabezadoID(FeEncabezado fEEncabezadoID) {
        this.fEEncabezadoID = fEEncabezadoID;
    }

    @XmlTransient
    public Collection<FeFlujoalternativo> getFeFlujoalternativoCollection() {
        return feFlujoalternativoCollection;
    }

    public void setFeFlujoalternativoCollection(Collection<FeFlujoalternativo> feFlujoalternativoCollection) {
        this.feFlujoalternativoCollection = feFlujoalternativoCollection;
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
        if (!(object instanceof FeFlujonormal)) {
            return false;
        }
        FeFlujonormal other = (FeFlujonormal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.entities.FeFlujonormal[ id=" + id + " ]";
    }
    
}
