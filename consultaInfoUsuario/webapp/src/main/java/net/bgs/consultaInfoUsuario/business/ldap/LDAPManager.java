package net.bgs.consultaInfoUsuario.business.ldap;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import net.bgs.consultaInfoUsuario.business.bean.Grupo;
import net.bgs.consultaInfoUsuario.business.bean.Usuario;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class LDAPManager {
	//private static final String userReturnedAtts[] = { "givenName", "sn", "sAMAccountName", "name", "scriptPath", "memberOf", "distinguishedName", "description" };
	private String username;
	private String password;
	
	private LDAPUtils utils = new LDAPUtils();

	public LDAPManager(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public NamingEnumeration<SearchResult> getUserByFullname(String fullUsername, String userSearchFilter, int searchScope) throws Exception {
		LdapContext ctx = null;
		//DirContext ctx = null;
		try {
			ctx = createLdapContext("");
			//ctx = createDirContext();

			// Create the search controls
			SearchControls userSearchCtls = new SearchControls();

			// Specify the search scope
			userSearchCtls.setSearchScope(searchScope);

			// paceholder for an LDAP filter that will store SIDs of the groups
			//userSearchCtls.setReturningAttributes(userReturnedAtts);
			userSearchCtls.setReturningAttributes(utils.obtenerAttributesFields());

			// Search for objects using the filter
			return ctx.search(fullUsername, userSearchFilter, userSearchCtls);
		} catch (NameNotFoundException e) {
			throw new Exception("No existe el usuario " + utils.obtenerCN(fullUsername) + " en el registro de Active Directory", e);
		} catch (Exception e) {
			throw new Exception(this.getClass().getName(), e);
		} finally {
			if (ctx != null)
				ctx.close();
		}
	}

	public Grupo obtenerGrupo(String fullName) throws Exception {
		Grupo grupo = null;
		try {
			NamingEnumeration<SearchResult> userAnswer = getUserByFullname(fullName, "(objectClass=group)", SearchControls.OBJECT_SCOPE);
			// Loop through the search results
			if (userAnswer.hasMoreElements()) {
				SearchResult sr = (SearchResult) userAnswer.next();
				Attributes attrs = sr.getAttributes();
				if (attrs != null) {
					grupo = utils.completarGrupo(attrs);
					grupo.setUsuarios(obtenerUsuarios(grupo.getNombreCompleto()));
				}
			}
		} catch (Exception e) {
			throw new Exception("Ocurrio un error al buscar al grupo " + fullName, e);
		}
		return grupo;
	}

	private List<Usuario> obtenerUsuarios(String fullName) throws Exception {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		NamingEnumeration<SearchResult> userAnswer = getUserByFullname(utils.getGroupsSearchBase(), "(&(objectClass=user)(memberOf=" + fullName + "))", SearchControls.SUBTREE_SCOPE);
		while (userAnswer.hasMoreElements()) {
			SearchResult sr = (SearchResult) userAnswer.next();
			Attributes attrs = sr.getAttributes();
			if (attrs != null) {
				usuarios.add(utils.completarUsuario(attrs));
			}
		}
		return usuarios;
	}

	public Usuario obtenerUsuario(String fullName) throws Exception {
		Usuario usuario = null;
		try {
			NamingEnumeration<SearchResult> userAnswer = getUserByFullname(fullName, "(objectClass=user)", SearchControls.OBJECT_SCOPE);
			// Loop through the search results
			if (userAnswer.hasMoreElements()) {
				SearchResult sr = (SearchResult) userAnswer.next();
				Attributes attrs = sr.getAttributes();
				if (attrs != null) {
					usuario = utils.completarUsuario(attrs);
				}
			}
		} catch (Exception e) {
			throw new Exception("Ocurrio un error al buscar al usuario " + fullName, e);
		}
		return usuario;
	}
	
	public DirContext createDirContext(String INITIAL_CONTEXT_FACTORY, String SECURITY_AUTHENTICATION, String PROVIDER_URL) throws Exception {
		Hashtable<Object, Object> env = new Hashtable<Object, Object>();
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);

		// set security credentials, note using simple cleartext authentication
		env.put(Context.SECURITY_AUTHENTICATION, SECURITY_AUTHENTICATION);
		// connect to my domain controller
		env.put(Context.PROVIDER_URL, PROVIDER_URL);

		try {
			// Create the initial directory context
			return new InitialDirContext(env);
		} catch (NamingException e) {
			throw new Exception("No se pudo conectar al LDAP", e);
		}
	}
	
	public LdapContext createAnonymousLdapContext() throws Exception {
		Hashtable<Object, Object> env = new Hashtable<Object, Object>();
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

		// set security credentials, note using simple cleartext authentication
		env.put(Context.SECURITY_AUTHENTICATION, "none");
		// connect to my domain controller
		env.put(Context.PROVIDER_URL, utils.getLdapProperties().getLDAPServer());

		try {
			// Create the initial directory context
			return new InitialLdapContext(env, null);
		} catch (NamingException e) {
			throw new Exception("No se pudo conectar al LDAP", e);
		}
	}

	public LdapContext createLdapContext(Object attr) throws Exception {
		Hashtable<Object, Object> env = new Hashtable<Object, Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

		// set security credentials, note using simple cleartext authentication
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		// set administrator user
		env.put(Context.SECURITY_PRINCIPAL, this.username);
		// set administrator password
		env.put(Context.SECURITY_CREDENTIALS, this.password);
		// connect to my domain controller
		env.put(Context.PROVIDER_URL, utils.getLdapProperties().getLDAPServer());
		// specify attributes to be returned in binary format
		env.put("java.naming.ldap.attributes.binary", attr);

		try {
			// Create the initial directory context
			return new InitialLdapContext(env, null);
		} catch (NamingException e) {
			throw new Exception("No se pudo conectar al LDAP", e);
		}
	}
	
	public LdapContext conectar() throws Exception{
		return createLdapContext("");
	}
}
