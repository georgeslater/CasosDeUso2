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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author George
 */
@Entity
@Table(name = "fe_encabezado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeEncabezado.findAll", query = "SELECT f FROM FeEncabezado f"),
    @NamedQuery(name = "FeEncabezado.findById", query = "SELECT f FROM FeEncabezado f WHERE f.id = :id"),
    @NamedQuery(name = "FeEncabezado.findByPrioridad", query = "SELECT f FROM FeEncabezado f WHERE f.prioridad = :prioridad"),
    @NamedQuery(name = "FeEncabezado.findByCasoDeUso", query = "SELECT f FROM FeEncabezado f WHERE f.casoDeUso = :cdu")})
    
public class FeEncabezado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 300)
    @Column(name = "Prioridad")
    private String prioridad;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Precondiciones")
    private String precondiciones;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Postcondiciones")
    private String postcondiciones;
    @JoinColumn(name = "CasoDeUso", referencedColumnName = "ID")
    @ManyToOne
    private CasoDeUso casoDeUso;
    @OneToMany(mappedBy = "fEEncabezadoID")
    private Collection<FeFlujonormal> feFlujonormalCollection;

    public FeEncabezado() {
    }

    public FeEncabezado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getPrecondiciones() {
        return precondiciones;
    }

    public void setPrecondiciones(String precondiciones) {
        this.precondiciones = precondiciones;
    }

    public String getPostcondiciones() {
        return postcondiciones;
    }

    public void setPostcondiciones(String postcondiciones) {
        this.postcondiciones = postcondiciones;
    }

    @XmlTransient
    public Collection<FeFlujonormal> getFeFlujonormalCollection() {
        return feFlujonormalCollection;
    }

    public void setFeFlujonormalCollection(Collection<FeFlujonormal> feFlujonormalCollection) {
        this.feFlujonormalCollection = feFlujonormalCollection;
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
        if (!(object instanceof FeEncabezado)) {
            return false;
        }
        FeEncabezado other = (FeEncabezado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.entities.FeEncabezado[ id=" + id + " ]";
    }

    /**
     * @return the casoDeUso
     */
    public CasoDeUso getCasoDeUso() {
        return casoDeUso;
    }

    /**
     * @param casoDeUso the casoDeUso to set
     */
    public void setCasoDeUso(CasoDeUso casoDeUso) {
        this.casoDeUso = casoDeUso;
    }
    
}
