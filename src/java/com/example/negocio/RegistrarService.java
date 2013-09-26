/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.negocio;

import com.example.dao.GrupoFacade;
import com.example.dao.UsuarioTableFacade;
import com.example.dao.UsuariosGruposFacade;
import com.example.entities.Grupo;
import com.example.entities.UsuarioTable;
import com.example.entities.UsuariosGrupos;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class RegistrarService {
    
    @EJB
    private GrupoFacade gf;
    @EJB
    private UsuariosGruposFacade ugf;
    @EJB
    private UsuarioTableFacade utf;
    
    public Boolean usuarioNoExiste(String usuario){
        
        return utf.obtenerIDPorNombre(usuario) == null? true: false; 
    }
    
    public Boolean asignarGrupoAUsuario(UsuarioTable user) {
        
        Grupo userGrupo = gf.obtenerUsuarioGrupo();
        
        if(userGrupo != null){
            
            UsuariosGrupos ug = new UsuariosGrupos();
            ug.setGrupoid(userGrupo);
            ug.setUsuarioid(user);
            ugf.create(ug);
            
            return true;
            
        }else{
            
            return false;
        }
    }

    /**
     * @return the gf
     */
    public GrupoFacade getGf() {
        return gf;
    }

    /**
     * @param gf the gf to set
     */
    public void setGf(GrupoFacade gf) {
        this.gf = gf;
    }

    /**
     * @return the ugf
     */
    public UsuariosGruposFacade getUgf() {
        return ugf;
    }

    /**
     * @param ugf the ugf to set
     */
    public void setUgf(UsuariosGruposFacade ugf) {
        this.ugf = ugf;
    }

    /**
     * @return the utf
     */
    public UsuarioTableFacade getUtf() {
        return utf;
    }

    /**
     * @param utf the utf to set
     */
    public void setUtf(UsuarioTableFacade utf) {
        this.utf = utf;
    }
}
