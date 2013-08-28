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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George
 */
@Entity
@Table(name = "usuarios_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuariosGrupos.findAll", query = "SELECT u FROM UsuariosGrupos u"),
    @NamedQuery(name = "UsuariosGrupos.findByUsuarioid", query = "SELECT u FROM UsuariosGrupos u WHERE u.usuarioid = :usuarioid"),
    @NamedQuery(name = "UsuariosGrupos.findByGrupoid", query = "SELECT u FROM UsuariosGrupos u WHERE u.grupoid = :grupoid"),
    @NamedQuery(name = "UsuariosGrupos.findById", query = "SELECT u FROM UsuariosGrupos u WHERE u.id = :id")})
public class UsuariosGrupos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "USUARIOID", referencedColumnName = "IDUSER")
    @ManyToOne(optional = false)
    @NotNull
    private UsuarioTable usuarioid;
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "GRUPOID", referencedColumnName="IDGROUP")
    private Grupo grupoid;

    public UsuariosGrupos() {
    }

    public UsuariosGrupos(Integer id) {
        this.id = id;
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof UsuariosGrupos)) {
            return false;
        }
        UsuariosGrupos other = (UsuariosGrupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.entities.UsuariosGrupos[ id=" + id + " ]";
    }

    /**
     * @return the usuarioid
     */
    public UsuarioTable getUsuarioid() {
        return usuarioid;
    }

    /**
     * @param usuarioid the usuarioid to set
     */
    public void setUsuarioid(UsuarioTable usuarioid) {
        this.usuarioid = usuarioid;
    }

    /**
     * @return the grupoid
     */
    public Grupo getGrupoid() {
        return grupoid;
    }

    /**
     * @param grupoid the grupoid to set
     */
    public void setGrupoid(Grupo grupoid) {
        this.grupoid = grupoid;
    }
    
}
