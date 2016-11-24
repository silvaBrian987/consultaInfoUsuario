package net.bgs.consultaInfoUsuario.web.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.request.Request;

/**
 * @author Bhimu
 *
 *         <code>Apache tiles configuration class. Implements DefinitionsFactory to provide programmatic configuration for Apache tiles.</code>
 *
 */
public final class TilesDefinitionsConfig implements DefinitionsFactory {

	private static final Map<String, Definition> tilesDefinitions = new HashMap<String, Definition>();
	private static final Attribute BASE_TEMPLATE = new Attribute("/WEB-INF/views/baseLayout.jsp");

	@Override
	public Definition getDefinition(String name, Request tilesContext) {
		return tilesDefinitions.get(name);
	}

	/**
	 * @param name
	 *            <code>Name of the view</code>
	 * @param title
	 *            <code>Page title</code>
	 * @param body
	 *            <code>Body JSP file path</code>
	 *
	 *            <code>Adds default layout definitions</code>
	 */
	private static void addDefaultLayoutDef(String name, String title, String body) {
		Map<String, Attribute> attributes = new HashMap<String, Attribute>();

		/*
		 * <definition name="baseLayout" template="/views/pages/baseLayout.jsp">
		 * <put-attribute name="header" value="/views/pages/header.jsp" />
		 * <put-attribute name="menu" value="/views/pages/menu.jsp" />
		 * <put-attribute name="body" value="" /> <put-attribute name="footer"
		 * value="/views/pages/footer.jsp" /> </definition>
		 */

		attributes.put("title", new Attribute("ConsultaActiveDirectory - " + title));
		attributes.put("header", new Attribute("/WEB-INF/views/header.jsp"));
		attributes.put("menu", new Attribute("/WEB-INF/views/menu.jsp"));
		attributes.put("body", new Attribute(body));
		attributes.put("footer", new Attribute("/WEB-INF/views/footer.jsp"));

		tilesDefinitions.put(name, new Definition(name, BASE_TEMPLATE, attributes));
	}

	/**
	 * <code>Add Apache tiles definitions</code>
	 */
	public static void addDefinitions() {
		addDefaultLayoutDef("errorPage", "ERROR", "/WEB-INF/views/errorPage.jsp");
		
		addDefaultLayoutDef("inicio", "INICIO", "");
		addDefaultLayoutDef("login", "LOGIN", "/WEB-INF/views/login.jsp");
		
		addDefaultLayoutDef("buscarUsuarioGrupo", "BUSCAR", "/WEB-INF/views/buscarUsuarioGrupo.jsp");
		
		addDefaultLayoutDef("consultaUsuario", "CONSULTA", "/WEB-INF/views/consultaUsuario.jsp");
		addDefaultLayoutDef("consultaGrupo", "CONSULTA", "/WEB-INF/views/consultaGrupo.jsp");
		
		addDefaultLayoutDef("analisisPermisosWAS", "ANALISIS WAS", "/WEB-INF/views/analisisPermisosWAS.jsp");
	}
}
