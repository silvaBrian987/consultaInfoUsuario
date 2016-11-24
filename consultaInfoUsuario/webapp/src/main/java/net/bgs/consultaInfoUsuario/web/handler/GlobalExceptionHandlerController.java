package net.bgs.consultaInfoUsuario.web.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.bgs.consultaInfoUsuario.business.utils.Utiles;
import net.bgs.consultaInfoUsuario.web.exception.LoginException;
import net.bgs.consultaInfoUsuario.web.exception.MapedException;

@ControllerAdvice
// @EnableWebMvc
public class GlobalExceptionHandlerController {
	private static final Logger log = Logger.getLogger(GlobalExceptionHandlerController.class);
	public static final String DEFAULT_ERROR_VIEW = "errorPage";
	
	@ExceptionHandler(value = { LoginException.class })
	public ModelAndView handleLoginException(HttpServletRequest req, HttpServletResponse res, Exception ex) {
		log.error("Request: " + req.getRequestURL() + " raised " + ex, ex);
		ModelAndView page = new ModelAndView(DEFAULT_ERROR_VIEW);
		//page.addObject("exception", new Exception("Error al acceder", ex));
		setAllErrors(page, new Exception("Error al acceder", ex));
		page.addObject("url", req.getRequestURL());
		return page;
	}
	
	@ExceptionHandler(MapedException.class)
	public @ResponseBody Map<String, Object> handleException(HttpServletRequest req, HttpServletResponse res, Exception ex) {
		log.error("Request: " + req.getRequestURL() + " raised " + ex, ex);
		res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", ex.getMessage());
		return response;
	}

	//@ExceptionHandler(value = { JspException.class, NoHandlerFoundException.class })
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handlePageException(HttpServletRequest req, HttpServletResponse res, Exception ex) {
		log.error("Request: " + req.getRequestURL() + " raised " + ex, ex);
		ModelAndView page = new ModelAndView(DEFAULT_ERROR_VIEW);
		//page.addObject("exception", new Exception("Acceso a la aplicacion inexistente", ex));
		setAllErrors(page, ex);
		page.addObject("url", req.getRequestURL());
		return page;
	}
	
	private void setAllErrors(ModelAndView page, Throwable ex){
		page.addObject("errors", Utiles.getExceptionList(ex));
	}
}