package net.bgs.consultaInfoUsuario.business.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utiles {
	public static void redirigirPagina(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String finalURL = "/pages/main.jsp?url=" + url;
//		if (isFromFilter()) {
//			response.sendRedirect(finalURL);
//		} else {
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher(finalURL);
//			requestDispatcher.forward(request, response);
//		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(finalURL);
		requestDispatcher.forward(request, response);
	}

	public static void redirigirPaginaError(Throwable e, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("errores", getExceptionList(e));
		redirigirPagina("/pages/main.jsp?url=/error.jsp", request, response);
	}

	public static List<Throwable> getExceptionList(Throwable ex) {
		List<Throwable> causeList = new ArrayList<Throwable>();
		causeList.add(ex);
		if (ex.getCause() != null) {
			Throwable cause = ex.getCause();
			do {
				causeList.add(cause);
			} while ((cause != null) && (cause = cause.getCause()) != null);
		}
		return causeList;
	}

	public static boolean sesionLogeada(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean ret = true;
		if (!userIsLogged(request)) {
			if (!request.getRequestURI().contains("login")) {
				redirigirPagina("/login.jsp", request, response);
				ret = false;
			}
		}
		return ret;
	}

	public static boolean userIsLogged(HttpServletRequest request) {
		Object attr = request.getSession().getAttribute("userIsLogged");
		if(attr != null){
			if(Boolean.parseBoolean(attr.toString())){
				if(request.getSession().getAttribute("LDAPManager") != null){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	public static void userNotLogged(HttpServletRequest request) {
		request.getSession().setAttribute("userIsLogged", false);
	}

	public static void userLogged(HttpServletRequest request) {
		request.getSession().setAttribute("userIsLogged", true);
	}

	public static boolean isFromFilter() {
		StackTraceElement ste = findACallerClass("filter");
		if (ste.getMethodName() == "doFilter") {
			return true;
		}
		return false;
	}

	public static StackTraceElement findACallerClass(String callerClass) {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement result = null;
		// for (int i = stackTrace.length - 1; i >= 1; i--) {
		for (int i = 1; i < stackTrace.length; i++) {
			StackTraceElement ste = stackTrace[i];
			if (ste.getClassName().toLowerCase().contains(callerClass)) {
				result = ste;
				break;
			}
		}
		return result;
	}
}
