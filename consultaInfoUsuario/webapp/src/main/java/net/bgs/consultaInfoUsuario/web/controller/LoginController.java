package net.bgs.consultaInfoUsuario.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.bgs.consultaInfoUsuario.business.ldap.LDAPManager;
import net.bgs.consultaInfoUsuario.business.ldap.LDAPUtils;
import net.bgs.consultaInfoUsuario.business.utils.Utiles;
import net.bgs.consultaInfoUsuario.web.exception.LoginException;

@Controller
public class LoginController {
	private LDAPUtils ldapUtils = new LDAPUtils();

	@RequestMapping(value={"/", "/inicio"})
	public ModelAndView inicio(HttpServletRequest req) {
		ModelAndView view = null;
		if (Utiles.userIsLogged(req)) {
			view = new ModelAndView("inicio");
		} else {
			Utiles.userNotLogged(req);
			view = new ModelAndView("login");
		}
		return view;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam String user, @RequestParam String pass, HttpServletRequest req) throws Exception {
		//if (user.equals("") || pass.equals(""))
		if (user.equals(""))
			throw new LoginException("No se envio usuario y/o contrasenia");

		ModelAndView view = new ModelAndView("inicio");
		try {
			LDAPManager ldap = new LDAPManager(ldapUtils.normalizarNombreUsuario(user), pass);
			//view.addObject("LDAPManager", ldap);
			//view.addObject("usuario", ldap.obtenerUsuario(ldapUtils.normalizarNombreUsuario(user)));
			req.getSession().setAttribute("LDAPManager", ldap);
			req.getSession().setAttribute("usuario", ldap.obtenerUsuario(ldapUtils.normalizarNombreUsuario(user)));
		} catch (Exception e) {
			throw new LoginException("Error", e);
		}
		Utiles.userLogged(req);
		return view;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest req) {
		Utiles.userNotLogged(req);
		return new ModelAndView("login");
	}
}
