package net.bgs.consultaInfoUsuario.web.exception;

public class LoginException extends Exception {
	private static final long serialVersionUID = 1131513407953465079L;
	
	public LoginException() {
		super();
	}
	
	public LoginException(String msg) {
		super(msg);
	}
	
	public LoginException(String msg, Throwable e) {
		super(msg, e);
	}
}
