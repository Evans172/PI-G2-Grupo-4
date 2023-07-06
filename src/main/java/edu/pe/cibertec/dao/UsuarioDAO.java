package edu.pe.cibertec.dao;


import edu.pe.cibertec.model.UsuarioDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDAO {

    private List<UsuarioDTO> listaUsuario;

    public UsuarioDAO(){

        this.listaUsuario=new ArrayList<UsuarioDTO>();
        getListaUsuario().add(new UsuarioDTO("Puerto", "1", "PENDIENTE"));
        getListaUsuario().add(new UsuarioDTO("La Era", "2", "APROBADO"));
        getListaUsuario().add(new UsuarioDTO("El Olivar", "2", "APROBADO"));
        getListaUsuario().add(new UsuarioDTO("Mirador", "1", "PENDIENTE"));
    }


    public void insertarUsuario(UsuarioDTO usuario){
        getListaUsuario().add(usuario);
    }

    public void eliminarUsuario(UsuarioDTO usuario){

        listaUsuario.remove(usuario);
    }
    public void editarUsuario(UsuarioDTO usuario){

            getListaUsuario().set(0, usuario);
    }
    public void actualizaUsuario ( UsuarioDTO usuario)
    {
        int found =-1;
        for (int i=0; i< getListaUsuario().size(); i++)
        {
            if (usuario.getUsuario().equals( getListaUsuario().get(i).getUsuario() ))
                found = i;
        }
        //


        if (found>-1)
            getListaUsuario().set(found, usuario);
    }

    public UsuarioDTO validarlogin(UsuarioDTO usuario){

        if (usuario.getUsuario().equals("user")&&usuario.getClave().equals("12345"))
            usuario.setNombreCompleto("Nombre completo del usuario");
        else usuario=null;

        return usuario;

    }
    public UsuarioDTO getUsuario ( String usuario)
    {
		/*
		for (UsuarioDTO us :getListaUsuarios())
		{
			if (us.getUsuario().equals(usuario))
				return us;
		}

		return null;
		*/
        // manejo de busquedas usando streams mediante lambda.

        return getListaUsuario().stream().filter(u -> usuario.equals(u.getUsuario())).findAny().orElse(null);
    }


    public List<UsuarioDTO> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<UsuarioDTO> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
}
