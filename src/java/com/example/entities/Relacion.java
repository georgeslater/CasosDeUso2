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
@Table(name = "relacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relacion.findAll", query = "SELECT r FROM Relacion r"),
    @NamedQuery(name = "Relacion.findById", query = "SELECT r FROM Relacion r WHERE r.id = :id"),
    @NamedQuery(name = "Relacion.findByNombre", query = "SELECT r FROM Relacion r WHERE r.nombre = :nombre")})
public class Relacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(mappedBy = "relacion4ID")
    private Collection<Fila> filaCollection;
    @OneToMany(mappedBy = "relacion3ID")
    private Collection<Fila> filaCollection1;
    @OneToMany(mappedBy = "relacion2ID")
    private Collection<Fila> filaCollection2;
    @OneToMany(mappedBy = "relacion1ID")
    private Collection<Fila> filaCollection3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relacionid")
    private Collection<CasosDeUsoRelaciones> casosDeUsoRelacionesCollection;

    public Relacion() {
    }

    public Relacion(Integer id) {
        this.id = id;
    }

    public Relacion(Integer id, String nombre) {
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
    public Collection<CasosDeUsoRelaciones> getCasosDeUsoRelacionesCollection() {
        return casosDeUsoRelacionesCollection;
    }

    public void setCasosDeUsoRelacionesCollection(Collection<CasosDeUsoRelaciones> casosDeUsoRelacionesCollection) {
        this.casosDeUsoRelacionesCollection = casosDeUsoRelacionesCollection;
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
        if (!(object instanceof Relacion)) {
            return false;
        }
        Relacion other = (Relacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.entities.Relacion[ id=" + id + " ]";
    }
    
}
