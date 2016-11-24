package net.bgs.consultaInfoUsuario.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.bgs.consultaInfoUsuario.business.utils.Utiles;

public class MyHandlerInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = Logger.getLogger(MyHandlerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.debug("request.getRequestURL() = " + request.getRequestURL());
		log.debug("request.getServletPath() = " + request.getServletPath());
		
		//String path = request.getServletPath();
		String[] urlElements = request.getRequestURL().toString().split("/");
		String actualUrl = "";
		for(int i = 1; i < urlElements.length - 1; i++) actualUrl += "/" + urlElements[i];
		String lastComponent = urlElements[urlElements.length - 1];
		log.debug("intercepted url = " + lastComponent);
		
//		String redirectPath = request.getRequestURL().toString();
//		redirectPath = StringUtils.replace(redirectPath, path, "");
//		request.getContextPath();
//		redirectPath += "/login";
//		log.info("redirect url = " + redirectPath);
		
		if (!lastComponent.equals("login")) {
			if (!Utiles.userIsLogged(request)) {
				response.sendRedirect(getRootUrl(request) + "/login");
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}
	
	private String getRootUrl(HttpServletRequest request){
		String rootUrl = "http://";
		String server = request.getServerName();
		String port = String.valueOf(request.getServerPort());
		String context = request.getContextPath();
		return rootUrl + server + ":" + port + context;
	}

}
