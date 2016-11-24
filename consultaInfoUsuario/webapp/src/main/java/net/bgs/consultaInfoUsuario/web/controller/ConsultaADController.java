package net.bgs.consultaInfoUsuario.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.bgs.consultaInfoUsuario.business.ldap.LDAPManager;
import net.bgs.consultaInfoUsuario.business.ldap.LDAPUtils;

@Controller
public class ConsultaADController {
	private LDAPUtils ldapUtils = new LDAPUtils();
	
	@RequestMapping(value = "buscarUsuarioGrupo", method = RequestMethod.GET)
	public ModelAndView buscarUsuarioGrupo(){
		ModelAndView view = new ModelAndView("buscarUsuarioGrupo");
		view.addObject("tipos", Tipos.values());
		return view;
	}

	@RequestMapping(value = "obtenerDatos")
	public ModelAndView obtenerDatos(@RequestParam String nombre, @RequestParam Tipos tipo, HttpServletRequest req) throws Exception {
		ModelAndView view = new ModelAndView();

		if (nombre.equals(""))
			throw new JspException("Falta nombre");

		LDAPManager ldap = (LDAPManager) req.getSession().getAttribute("LDAPManager");
		if(tipo.equals(Tipos.USUARIO)){
			view.setViewName("consultaUsuario");
			view.addObject("usuarioConsultado", ldap.obtenerUsuario(ldapUtils.normalizarNombreUsuario(nombre)));
		}else if(tipo.equals(Tipos.GRUPO)){
			view.setViewName("consultaGrupo");
			if(ldapUtils.isFQDN(nombre)){
				view.addObject("grupoConsultado", ldap.obtenerGrupo(nombre));
			}else{
				view.addObject("grupoConsultado", ldap.obtenerGrupo(ldapUtils.normalizarNombreGrupo(nombre)));
			}
		}
		
		return view;
	}
}
