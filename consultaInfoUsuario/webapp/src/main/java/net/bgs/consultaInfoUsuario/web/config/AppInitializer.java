
package net.bgs.consultaInfoUsuario.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import net.bgs.consultaInfoUsuario.web.listener.MyHttpSessionListener;


//public class AppInitializer implements WebApplicationInitializer {
public class AppInitializer {
	private static final Logger log = Logger.getLogger(AppInitializer.class);

	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext context = getContext();
		
		servletContext.addListener(new ContextLoaderListener(context));
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("ConsultaActiveDirectory", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("net.bgs.consultaActiveDirectory.web.config");
		return context;
	}

}
