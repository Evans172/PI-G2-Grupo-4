package edu.pe.cibertec.service;

import edu.pe.cibertec.dao.UsuarioDAO;
import edu.pe.cibertec.model.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;


    public void insertarUsuario(UsuarioDTO usuario){
        usuarioDAO.insertarUsuario(usuario);
    }

    public void eliminarUsuario(UsuarioDTO usuario){
        usuarioDAO.eliminarUsuario(usuario);
    }

    public void editarUsuario(UsuarioDTO usuario){usuarioDAO.editarUsuario(usuario);}

    public List<UsuarioDTO> getListaUsuario(){
        return usuarioDAO.getListaUsuario();
    }

    public void actualizaUsuario ( UsuarioDTO usuario)
    {
        String base64foto = Base64.getEncoder().encodeToString(usuario.getFoto());

        usuario.setFotoBase64(base64foto);

        usuarioDAO.actualizaUsuario(usuario);
    }

    public UsuarioDTO validarLogin(UsuarioDTO usuario){
        return usuarioDAO.validarlogin(usuario);
    }
    public UsuarioDTO getUsuario ( String usuario)
    {
        return usuarioDAO.getUsuario(usuario);
    }



    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
}
