package edu.pe.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

        @RequestMapping("/")
        public String index(HttpSession session){
            session.setAttribute("pruebaTest","Este es el Home");
            return "index";
    }
}
