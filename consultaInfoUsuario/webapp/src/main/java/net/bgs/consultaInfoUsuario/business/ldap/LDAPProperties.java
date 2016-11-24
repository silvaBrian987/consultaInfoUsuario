package net.bgs.consultaInfoUsuario.business.ldap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class LDAPProperties {
	private static final Logger log = Logger.getLogger(LDAPProperties.class);

	private static final String FILEPATH = "/net/bgs/consultaInfoUsuario/ldap.properties";
	private static Properties ldapConfig;

	public LDAPProperties() {
		ldapConfig = new Properties();
		try {
			InputStream in = this.getClass().getResourceAsStream(FILEPATH);
			if (in == null) {
				in = this.getClass().getClassLoader().getResourceAsStream(FILEPATH);
				if (in == null) {
					throw new IOException("No se puede obtener \"" + FILEPATH + "\"");
				}
			}
			ldapConfig.load(in);
		} catch (IOException e) {
			log.error("Error grave", e);
		}
	}

	public String getDomain() {
		return ldapConfig.getProperty("ldap.domain");
	}

	public String getAdministratorUsername() {
		return ldapConfig.getProperty("ldap.administratorUsername");
	}

	public String getLDAPServer() {
		return ldapConfig.getProperty("ldap.server");
	}

	public String getAdministratorPassword() {
		return ldapConfig.getProperty("ldap.administratorPassword");
	}

	public String getOU() {
		return ldapConfig.getProperty("ldap.OU");
	}

	public String getDC() {
		return ldapConfig.getProperty("ldap.DC");
	}

	public String getGroupsCN() {
		return ldapConfig.getProperty("ldap.groups.CN");
	}
}
