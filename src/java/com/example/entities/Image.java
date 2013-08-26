/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George
 */
@Entity
@Table(name = "image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i"),
    @NamedQuery(name = "Image.findById", query = "SELECT i FROM Image i WHERE i.id = :id"),
    @NamedQuery(name = "Image.findByTitle", query = "SELECT i FROM Image i WHERE i.title = :title"),
    @NamedQuery(name = "Image.findByDiagram", query = "SELECT i FROM Image i WHERE i.diagramID = :diagramID"),
    @NamedQuery(name = "Image.countByUser", query = "SELECT COUNT(i) FROM Image i WHERE i.usuario = :userid"),
    @NamedQuery(name = "Image.findAllAfterLastSync", query = "SELECT i FROM Image i WHERE i.fechaGuardado > :ultimoSync")})
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 255)
    @Column(name = "Title")
    private String title;
    @JoinColumn(name = "DiagramID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Diagrama diagramID;
    @JoinColumn(name = "UserID", referencedColumnName = "IDUSER")
    @ManyToOne(optional = false)
    private UsuarioTable usuario;
    @Column(name = "FechaGuardado")
    @Temporal(value= TemporalType.TIMESTAMP)
    private Date fechaGuardado;
    @Basic(fetch = FetchType.EAGER)
    @Lob 
    @Column(name = "Body")
    private byte[] body;
    
    public Image() {
    }

    public Image(Integer id) {
        this.id = id;
    }

    public Image(UsuarioTable usuario, Diagrama diag, String titulo, byte[] body, Date fechaGuardado) {
        this.usuario = usuario;
        this.diagramID = diag;
        this.title = titulo;
        this.body = body;
        this.fechaGuardado = fechaGuardado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Diagrama getDiagramID() {
        return diagramID;
    }

    public void setDiagramID(Diagrama diagramID) {
        this.diagramID = diagramID;
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
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.entities.Image[ id=" + id + " ]";
    }

    /**
     * @return the usuario
     */
    public UsuarioTable getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioTable usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the fechaGuardado
     */
    public Date getFechaGuardado() {
        return fechaGuardado;
    }

    /**
     * @param fechaGuardado the fechaGuardado to set
     */
    public void setFechaGuardado(Date fechaGuardado) {
        this.fechaGuardado = fechaGuardado;
    }

    /**
     * @return the body
     */
    public byte[] getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(byte[] body) {
        this.body = body;
    }
    
}
