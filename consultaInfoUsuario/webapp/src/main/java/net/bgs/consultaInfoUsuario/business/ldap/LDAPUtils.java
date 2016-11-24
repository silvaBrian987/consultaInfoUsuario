package net.bgs.consultaInfoUsuario.business.ldap;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import net.bgs.consultaInfoUsuario.business.bean.Grupo;
import net.bgs.consultaInfoUsuario.business.bean.Usuario;

public class LDAPUtils {
	private LDAPProperties ldapProperties = new LDAPProperties();

	public String normalizarNombreUsuario(String CN) {

		// EJEMPLO:
		// "CN=L0624462,OU=UsuariosCorp,DC=bgcmz,dc=bancogalicia,dc=com,dc=ar";
		StringBuilder fullName = new StringBuilder();
		fullName.append("CN=" + CN);
		fullName.append(",");
		fullName.append("OU=" + ldapProperties.getOU());
		fullName.append(",");
		fullName.append("DC=" + ldapProperties.getDC());

		String domain = ldapProperties.getDomain();
		if (domain.contains(".")) {
			for (String s : domain.split("\\.")) {
				fullName.append(",");
				fullName.append("dc=" + s);
			}
		}

		return fullName.toString();
	}

	public String normalizarNombreGrupo(String CN) {

		// EJEMPLO:
		// "CN=GACSigalSupervisor,CN=Users,DC=bgcmz,DC=bancogalicia,DC=com,DC=ar";
		StringBuilder fullName = new StringBuilder();
		fullName.append("CN=" + CN);
		fullName.append(",");
		fullName.append("CN=" + ldapProperties.getGroupsCN());
		fullName.append(",");
		fullName.append("DC=" + ldapProperties.getDC());

		String domain = ldapProperties.getDomain();
		if (domain.contains(".")) {
			for (String s : domain.split("\\.")) {
				fullName.append(",");
				fullName.append("dc=" + s);
			}
		}

		return fullName.toString();
	}

	public String getGroupsSearchBase() {
		StringBuilder groupsSearchBase = new StringBuilder();
		groupsSearchBase.append("DC=" + ldapProperties.getDC());
		String domain = ldapProperties.getDomain();

		if (domain.contains(".")) {
			for (String s : domain.split("\\.")) {
				groupsSearchBase.append(",");
				groupsSearchBase.append("dc=" + s);
			}
		}

		return groupsSearchBase.toString();
	}

	public String obtenerCN(String fullname) {
		String[] segments = fullname.split(",");
		return segments[0].substring(segments[0].toUpperCase().indexOf("CN=") + 3, segments[0].length());
	}

	public boolean isFQDN(String name) {
		String val = name.toUpperCase();
		if (val.contains("CN") && val.contains("DC")) {
			return true;
		}
		return false;
	}

	public LDAPProperties getLdapProperties() {
		return ldapProperties;
	}
	
	public Usuario completarUsuario(Attributes attrs) throws NamingException{
		Usuario usuario = new Usuario();
		
		if (attrs.get("givenName") != null) {
			usuario.setNombre(obtenerValor(attrs, "givenName"));
			usuario.setApellido(obtenerValor(attrs, "sn"));
			usuario.setIniciales(obtenerValor(attrs, "initials"));
			usuario.setDescripcion(obtenerValor(attrs, "description"));
			usuario.setDisplayName(obtenerValor(attrs, "displayName"));
			usuario.setOficina(obtenerValor(attrs, "physicalDeliveryOfficeName"));
			
			usuario.setUsuario(obtenerValor(attrs, "sAMAccountName"));
			usuario.setUsuarioCompleto(obtenerValor(attrs, "name"));
			usuario.setScriptInicio(obtenerValor(attrs, "scriptPath"));
			
			usuario.setCalle(obtenerValor(attrs, "streetAddress"));
			
			usuario.setTitulo(obtenerValor(attrs, "title"));
			usuario.setDepartamento(obtenerValor(attrs, "department"));
			usuario.setCompania(obtenerValor(attrs, "company"));
			usuario.setNombreJefe(obtenerValor(attrs, "manager"));
			usuario.setReportaA(obtenerValor(attrs, "directReports"));
			
			usuario.setGrupos(this.obtenerGrupos(attrs.get("memberof")));
		} else {
			usuario.setNombre("-");
		}
		
		return usuario;
	}
	
	public Grupo completarGrupo(Attributes attrs) throws NamingException{
		Grupo grupo = new Grupo();
		
		grupo.setNombre(obtenerValor(attrs, "sAMAccountName"));
		grupo.setNombreCompleto(obtenerValor(attrs, "distinguishedName"));
		grupo.setComentario(obtenerValor(attrs, "description"));
		
		return grupo;
	}
	
	public List<Grupo> obtenerGrupos(Attribute memberof) throws NamingException {
		List<Grupo> grupos = new ArrayList<Grupo>();
		for (NamingEnumeration<? extends String> ae = (NamingEnumeration<? extends String>) memberof.getAll(); ae.hasMore();) {
			String fullname = ae.next();
			Grupo grupo = new Grupo();
			grupo.setNombre(this.obtenerCN(fullname));
			grupo.setNombreCompleto(fullname);
			grupos.add(grupo);
		}
		return grupos;
	}
	
	public String[] obtenerAttributesFields(){
		List<String> attrs = new ArrayList<String>();
		
		//General
		attrs.add("givenName");
		attrs.add("sn");
		attrs.add("displayName");
		attrs.add("initials");
		attrs.add("description");
		attrs.add("telephoneNumber");
		attrs.add("physicalDeliveryOfficeName");
		
		//Address
		attrs.add("streetAddress");
		
		//Account
		attrs.add("sAMAccountName");
		attrs.add("name");
		attrs.add("memberOf");
		attrs.add("scriptPath");
		
		//Organization
		attrs.add("title");
		attrs.add("department");
		attrs.add("company");
		attrs.add("manager");
		attrs.add("directReports");
		
		attrs.add("info");
		
		//Groups
		attrs.add("distinguishedName");
		
		return attrs.toArray(new String[0]);
	}
	
	public String obtenerValor(Attributes attrs, String element) throws NamingException{
		Attribute attr = attrs.get(element);
		String ret = null;
		
		if(attr != null){
			Object elem = attr.get();
			if(elem != null){
				if(elem.toString().trim() != ""){
					ret = elem.toString();
				}
			}
		}
		
		return ret;
	}
}
