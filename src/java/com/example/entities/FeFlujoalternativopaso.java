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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George
 */
@Entity
@Table(name = "fe_flujoalternativopaso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeFlujoalternativopaso.findAll", query = "SELECT f FROM FeFlujoalternativopaso f"),
    @NamedQuery(name = "FeFlujoalternativopaso.findById", query = "SELECT f FROM FeFlujoalternativopaso f WHERE f.id = :id"),
    @NamedQuery(name = "FeFlujoalternativopaso.findByOrden", query = "SELECT f FROM FeFlujoalternativopaso f WHERE f.orden = :orden"),
    @NamedQuery(name = "FeFlujoalternativopaso.findByFlujosAlternativos", query = "SELECT f FROM FeFlujoalternativopaso f WHERE f.fEFlujoAlternativoID.id IN :faList")})
public class FeFlujoalternativopaso implements Serializable, Comparable {
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
    @JoinColumn(name = "FEFlujoAlternativoID", referencedColumnName = "ID")
    @ManyToOne
    private FeFlujoalternativo fEFlujoAlternativoID;

    public FeFlujoalternativopaso() {
    }

    public FeFlujoalternativopaso(Integer id) {
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

    public FeFlujoalternativo getFEFlujoAlternativoID() {
        return fEFlujoAlternativoID;
    }

    public void setFEFlujoAlternativoID(FeFlujoalternativo fEFlujoAlternativoID) {
        this.fEFlujoAlternativoID = fEFlujoAlternativoID;
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
        if (!(object instanceof FeFlujoalternativopaso)) {
            return false;
        }
        FeFlujoalternativopaso other = (FeFlujoalternativopaso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.entities.FeFlujoalternativopaso[ id=" + id + " ]";
    }
    
    //Ordenamos por orden del abuelo, despues por orden del padre
    @Override
    public int compareTo(Object o){
        
        FeFlujoalternativopaso other = (FeFlujoalternativopaso)o;
        
        if(this.fEFlujoAlternativoID.getFEFlujoNormalID().getOrden() < other.fEFlujoAlternativoID.getFEFlujoNormalID().getOrden()){
            
            return -1;
        
        }else if(this.fEFlujoAlternativoID.getFEFlujoNormalID().getOrden() > other.fEFlujoAlternativoID.getFEFlujoNormalID().getOrden()){
            
            return 1;
        
        }else{
            
            if(this.fEFlujoAlternativoID.getOrden() < other.fEFlujoAlternativoID.getOrden()){
                
                return -1;
            
            }else if(this.fEFlujoAlternativoID.getOrden() > other.fEFlujoAlternativoID.getOrden()){
                
                return 1;
            }
            
            if(this.getOrden() < other.getOrden()){
                
                return -1;
            
            }else if(this.getOrden() > other.getOrden()){
                
                return 1;
            }
        }
        
        return 0;
    }
    
}
