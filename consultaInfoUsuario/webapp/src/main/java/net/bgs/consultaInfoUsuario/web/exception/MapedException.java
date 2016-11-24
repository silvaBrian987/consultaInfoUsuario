package net.bgs.consultaInfoUsuario.web.exception;

public class MapedException extends Exception {
	private static final long serialVersionUID = 2953533353112054734L;
	
	public MapedException(){
		super();
	}
	
	public MapedException(String msg){
		super(msg);
	}
	
	public MapedException(String msg, Throwable ex){
		super(msg, ex);
	}
}
