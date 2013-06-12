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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

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
    @NamedQuery(name = "CasoDeUso.findByPositionX", query = "SELECT c FROM CasoDeUso c WHERE c.positionX = :positionX"),
    @NamedQuery(name = "CasoDeUso.findByPositionY", query = "SELECT c FROM CasoDeUso c WHERE c.positionY = :positionY")})
public class CasoDeUso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 45)
    @Column(name = "Text")
    private String text;
    @Size(max = 45)
    @Column(name = "PositionX")
    private String positionX;
    @Size(max = 45)
    @Column(name = "PositionY")
    private String positionY;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "casoDeUso")
    private CasosDeUsoRelaciones casosDeUsoRelaciones;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "casoDeUso")
    private ActorCasoDeUso actorCasoDeUso;

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

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    public CasosDeUsoRelaciones getCasosDeUsoRelaciones() {
        return casosDeUsoRelaciones;
    }

    public void setCasosDeUsoRelaciones(CasosDeUsoRelaciones casosDeUsoRelaciones) {
        this.casosDeUsoRelaciones = casosDeUsoRelaciones;
    }

    public ActorCasoDeUso getActorCasoDeUso() {
        return actorCasoDeUso;
    }

    public void setActorCasoDeUso(ActorCasoDeUso actorCasoDeUso) {
        this.actorCasoDeUso = actorCasoDeUso;
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
