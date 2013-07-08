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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George
 */
@Entity
@Table(name = "usuario_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioTable.findAll", query = "SELECT u FROM UsuarioTable u"),
    @NamedQuery(name = "UsuarioTable.findByIduser", query = "SELECT u FROM UsuarioTable u WHERE u.iduser = :iduser"),
    @NamedQuery(name = "UsuarioTable.findByUsernameusuario", query = "SELECT u FROM UsuarioTable u WHERE u.usernameusuario = :usernameusuario"),
    @NamedQuery(name = "UsuarioTable.findByContraseniausuario", query = "SELECT u FROM UsuarioTable u WHERE u.contraseniausuario = :contraseniausuario"),
    @NamedQuery(name = "UsuarioTable.findByNombre", query = "SELECT u FROM UsuarioTable u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "UsuarioTable.findByApellido", query = "SELECT u FROM UsuarioTable u WHERE u.apellido = :apellido")})
public class UsuarioTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUSER")
    private Integer iduser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USERNAMEUSUARIO")
    private String usernameusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CONTRASENIAUSUARIO")
    private String contraseniausuario;
    @Size(max = 255)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 255)
    @Column(name = "APELLIDO")
    private String apellido;

    public UsuarioTable() {
    }

    public UsuarioTable(Integer iduser) {
        this.iduser = iduser;
    }

    public UsuarioTable(Integer iduser, String usernameusuario, String contraseniausuario) {
        this.iduser = iduser;
        this.usernameusuario = usernameusuario;
        this.contraseniausuario = contraseniausuario;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getUsernameusuario() {
        return usernameusuario;
    }

    public void setUsernameusuario(String usernameusuario) {
        this.usernameusuario = usernameusuario;
    }

    public String getContraseniausuario() {
        return contraseniausuario;
    }

    public void setContraseniausuario(String contraseniausuario) {
        this.contraseniausuario = contraseniausuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduser != null ? iduser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioTable)) {
            return false;
        }
        UsuarioTable other = (UsuarioTable) object;
        if ((this.iduser == null && other.iduser != null) || (this.iduser != null && !this.iduser.equals(other.iduser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.entities.UsuarioTable[ iduser=" + iduser + " ]";
    }
    
}
