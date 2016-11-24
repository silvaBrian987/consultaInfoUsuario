package com.ibm.was.security.util;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.ibm.was.security.util.ApplicationBinding.AuthorizationTable.Authorizations;
import com.ibm.was.security.util.ApplicationBinding.AuthorizationTable.Authorizations.Groups;
import com.ibm.was.security.util.ApplicationBinding.AuthorizationTable.Authorizations.Users;

public class XMIReader {
	private ApplicationBinding appBiding;
	private String appName;
	
	public XMIReader(Reader xml, String appName) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(ApplicationBinding.class);
		Unmarshaller unMarshaller = context.createUnmarshaller();
		this.appBiding = (ApplicationBinding) unMarshaller.unmarshal(xml);
		this.appName = appName;
	}

	public List<String> obtenerRolesPorGrupo(String grupo) throws JAXBException {
		List<String> roles = new ArrayList<String>();
		List<Authorizations> auths = appBiding.getAuthorizationTable().getAuthorizations();
		for (Authorizations auth : auths) {
			for (Groups group : auth.getGroups()) {
				String nombre = group.getName().split(",")[0].split("=")[1];
				if (nombre.equals(grupo)) {
					//roles.add(auth.getRole().getHref().split("#")[1].substring("VLAR_".length()));
					roles.add(auth.getRole().getHref().split("#")[1].substring((appName + "_").length()) + " - " + nombre);
				}
			}
		}
		return roles;
	}

	public List<String> obtenerRolesPorLegajo(String legajo) throws JAXBException {
		List<String> roles = new ArrayList<String>();
		List<Authorizations> auths = appBiding.getAuthorizationTable().getAuthorizations();
		for (Authorizations auth : auths) {
			for (Users user : auth.getUsers()) {
				String nombre = user.getName().split(",")[0].split("=")[1];
				if (nombre.equals(legajo)) {
					//roles.add(auth.getRole().getHref().split("#")[1].substring("VLAR_".length()));
					roles.add(auth.getRole().getHref().split("#")[1].substring((appName + "_").length()));
				}
			}
		}
		return roles;
	}
}
