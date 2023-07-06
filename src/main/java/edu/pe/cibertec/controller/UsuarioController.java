package edu.pe.cibertec.controller;

import edu.pe.cibertec.model.UsuarioDTO;
import edu.pe.cibertec.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("loginMostrar")
    public String loginMostar(){
        return "login";
    }
    @RequestMapping("registrarA")
    public String registrarA(){
        return "RegistrarA";
    }
    @RequestMapping("alumnoRegistro")
    public String alumnoRegistro(){
        return "alumnoRegistro";
    }
    @RequestMapping("aprobar")
    public String aprobar(){
        return "aprobar";
    }

    @RequestMapping("loginAccion")
    public ModelAndView loginAccion(UsuarioDTO usuario){
        ModelAndView view=null;
        UsuarioDTO user=usuarioService.validarLogin(usuario);
        if (user==null)
            view=new ModelAndView("login","msgError","usuario o clave incorrecto");
        else view=new ModelAndView("usuarioMostrar","lista", getUsuarioService().getListaUsuario());

        return view;
    }
    @RequestMapping("proyectos")
    public ModelAndView proyecto(){
        ModelAndView view=null;
        return view=new ModelAndView("usuarioMostrar","lista", getUsuarioService().getListaUsuario());
    }

    @RequestMapping("index")
    public ModelAndView index(UsuarioDTO usuario){
        ModelAndView modelAndView=null;
        modelAndView=new ModelAndView("usuarioMostrar","lista",getUsuarioService().getListaUsuario());
        return modelAndView;
    }

    @RequestMapping("/ingresarUsuario")
    public ModelAndView ingresarUsuario(UsuarioDTO usuario){
        return new ModelAndView("registroUsuario","usuarioBean",usuario);
    }

    @RequestMapping("/usuarioGrabar")
    public ModelAndView usuarioGrabar(@Valid @ModelAttribute("usuarioBean")UsuarioDTO usuario, BindingResult result,UsuarioDTO eliminar){
        ModelAndView modelAndView=null;
        if (result.hasErrors()){
            modelAndView=new ModelAndView("registroUsuario","usuarioBean",usuario);
        } else
            getUsuarioService().insertarUsuario(usuario);
            modelAndView=new ModelAndView("usuarioMostrar","lista",getUsuarioService().getListaUsuario());

        return modelAndView;

    }

    @RequestMapping("/eliminar")
    public ModelAndView eliminar(HttpServletRequest request){
        ModelAndView modelAndView=null;
        String codigoUsuario = request.getParameter("codigoUsuario");
        UsuarioDTO usuario = getUsuarioService().getUsuario(codigoUsuario);
        getUsuarioService().eliminarUsuario(usuario);
        modelAndView=new ModelAndView("usuarioMostrar","lista",getUsuarioService().getListaUsuario());

        return modelAndView;
    }
    @RequestMapping("editarMostar")
    public String editarMosta(){
        //ModelAndView modelAndView=null;

        //modelAndView=new ModelAndView("editarUsuario","usuarioBean",usuario);
        return "registrar";
    }
    @RequestMapping("reporte")
    public String reporte(){
        //ModelAndView modelAndView=null;

        //modelAndView=new ModelAndView("editarUsuario","usuarioBean",usuario);
        return "reporte";
    }

    @RequestMapping("/editar")
    public ModelAndView editar(@Valid @ModelAttribute("usuarioBean")UsuarioDTO usuario){
            ModelAndView modelAndView=null;
            getUsuarioService().editarUsuario(usuario);
            modelAndView=new ModelAndView("usuarioMostrar","lista",getUsuarioService().getListaUsuario());

        return modelAndView;
    }
    @RequestMapping ("usuarioFotoMostrar")
    public ModelAndView mostrarFoto (HttpServletRequest request, Model modelo)
    {
        String codigoUsuario = request.getParameter("codigoUsuario");
        UsuarioDTO usuario = getUsuarioService().getUsuario(codigoUsuario);

        modelo.addAttribute("usuario", usuario);

        return new ModelAndView("usuarioFoto");
    }

    @RequestMapping ("usuarioFotoGrabar")
    public ModelAndView grabarFotoUsuario ( @RequestParam("foto") CommonsMultipartFile foto,
                                            @RequestParam("codigoUsuario") String codigoUsuario)
    {
        UsuarioDTO usuario =  getUsuarioService ().getUsuario(codigoUsuario);

        usuario.setFoto(foto.getBytes());

        getUsuarioService ().actualizaUsuario(usuario);

        return new ModelAndView("usuarioMostrar", "lista", getUsuarioService().getListaUsuario());
    }



    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
