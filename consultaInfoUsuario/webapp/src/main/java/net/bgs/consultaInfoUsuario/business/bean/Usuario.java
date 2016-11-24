package net.bgs.consultaInfoUsuario.business.bean;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable{
	private static final long serialVersionUID = -72065275666999667L;
	
	//Generales
	private String nombre;
	private String apellido;
	private String iniciales;
	private String displayName;
	private String descripcion;
	private String oficina;
	private String telefono;
	private String otroTelefono;
	private String email;
	
	//Direccion
	private String calle;
	private String codigoPostal;
	private String ciudad;
	private String provincia;
	private String pais;
	
	//Organizacion
	private String titulo;
	private String departamento;
	private String compania;
	private String nombreJefe;
	private String reportaA;
	
	//Datos de usuario
	private String usuario;
	private String usuarioCompleto;
	private String scriptInicio;
	private List<Grupo> grupos;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getIniciales() {
		return iniciales;
	}
	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getOtroTelefono() {
		return otroTelefono;
	}
	public void setOtroTelefono(String otroTelefono) {
		this.otroTelefono = otroTelefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public String getNombreJefe() {
		return nombreJefe;
	}
	public void setNombreJefe(String nombreJefe) {
		this.nombreJefe = nombreJefe;
	}
	public String getReportaA() {
		return reportaA;
	}
	public void setReportaA(String reportaA) {
		this.reportaA = reportaA;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getUsuarioCompleto() {
		return usuarioCompleto;
	}
	public void setUsuarioCompleto(String usuarioCompleto) {
		this.usuarioCompleto = usuarioCompleto;
	}
	public String getScriptInicio() {
		return scriptInicio;
	}
	public void setScriptInicio(String scriptInicio) {
		this.scriptInicio = scriptInicio;
	}
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
}
