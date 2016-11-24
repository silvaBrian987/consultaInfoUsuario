package net.bgs.consultaInfoUsuario.web.controller;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InvalidNameException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CharSequenceReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.was.security.util.XMIReader;

import net.bgs.consultaInfoUsuario.business.bean.Grupo;
import net.bgs.consultaInfoUsuario.business.bean.Usuario;
import net.bgs.consultaInfoUsuario.business.ldap.LDAPManager;
import net.bgs.consultaInfoUsuario.business.ldap.LDAPUtils;

@Controller
public class AnalisisPermisosWASController {
	private LDAPUtils ldapUtils = new LDAPUtils();

	@RequestMapping(value = "analizarPermisosWAS", method = RequestMethod.GET)
	public ModelAndView analizarPermisosWAS() {
		ModelAndView view = new ModelAndView("analisisPermisosWAS");
		view.addObject("tipos", Tipos.values());
		return view;
	}

	@RequestMapping(value = "analizarPermisosWAS", method = RequestMethod.POST, consumes = "multipart/form-data")
	public ModelAndView analizarPermisosWAS(@RequestParam String nombre, @RequestParam Tipos tipo, @RequestParam String appName, @RequestParam MultipartFile archivoXMI, HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("analisisPermisosWAS");

		LDAPManager ldap = (LDAPManager) request.getSession().getAttribute("LDAPManager");

		XMIReader xmiReader = null;

		try {
			String fileName = archivoXMI.getOriginalFilename();
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			if(!ext.toLowerCase().equals("xmi")) throw new InvalidNameException("Extension incorrecta");
				
			byte[] buffer = IOUtils.toByteArray(archivoXMI.getInputStream());
			Reader targetReader = new CharSequenceReader(new String(buffer));
			xmiReader = new XMIReader(targetReader, appName);
			targetReader.close();
		} catch (Exception e) {
			throw new Exception("Ocurrio un problema al querer procesar el archivo", e);
		}
		
		List<String> rolesPorGrupo = new ArrayList<String>();
		List<String> rolesPorUsuario = new ArrayList<String>();

		if (tipo.equals(Tipos.USUARIO)) {
			Usuario user = ldap.obtenerUsuario(ldapUtils.normalizarNombreUsuario(nombre));
			rolesPorUsuario = xmiReader.obtenerRolesPorLegajo(user.getUsuario());
			if (user.getGrupos() != null) {
				for (Grupo grupo : user.getGrupos()) {
					rolesPorGrupo.addAll(xmiReader.obtenerRolesPorGrupo(grupo.getNombre()));
				}
			}
		} else if (tipo.equals(Tipos.GRUPO)) {
			Grupo group = ldap.obtenerGrupo(ldapUtils.normalizarNombreGrupo(nombre));
			rolesPorGrupo = xmiReader.obtenerRolesPorGrupo(group.getNombre());
		} else {
			throw new Exception("Tipo de usuario indefinido");
		}

		view.addObject("tipos", Tipos.values());
		view.addObject("rolesPorUsuario", rolesPorUsuario);
		view.addObject("rolesPorGrupo", rolesPorGrupo);

		// Esto es para ocultar el formulario
		view.addObject("hideForm", true);

		return view;
	}
}
