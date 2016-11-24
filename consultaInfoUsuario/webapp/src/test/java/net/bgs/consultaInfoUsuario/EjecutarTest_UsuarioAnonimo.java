package net.bgs.consultaInfoUsuario;

import javax.naming.NamingEnumeration;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;

import net.bgs.consultaInfoUsuario.business.ldap.LDAPManager;

public class EjecutarTest_UsuarioAnonimo {

	public static void main(String[] args) {
		Loader.getResource("log4j.properties");
		Logger log = Logger.getLogger(EjecutarTest_UsuarioAnonimo.class);

		LDAPManager ldapManager = new LDAPManager("L0624462", "");
		try {
			LdapContext ctx = ldapManager.createAnonymousLdapContext();
			
			// Create the search controls
			SearchControls userSearchCtls = new SearchControls();

			// Specify the search scope
			userSearchCtls.setSearchScope(0);

			// paceholder for an LDAP filter that will store SIDs of the groups
			userSearchCtls.setReturningAttributes(new String[]{"memberOf"});

			// Search for objects using the filter
			NamingEnumeration<SearchResult> search = ctx.search("L0624462", "(objectClass=user)", userSearchCtls);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
