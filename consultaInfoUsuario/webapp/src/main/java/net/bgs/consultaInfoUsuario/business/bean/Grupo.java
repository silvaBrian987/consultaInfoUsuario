package net.bgs.consultaInfoUsuario.business.bean;

import java.io.Serializable;
import java.util.List;

public class Grupo implements Serializable{
	private static final long serialVersionUID = -7320905836705104577L;
	private String nombre;
	private String nombreCompleto;
	private String comentario;
	private List<Usuario> usuarios;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
