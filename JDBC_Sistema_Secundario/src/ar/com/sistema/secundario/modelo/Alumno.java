package ar.com.sistema.secundario.modelo;

import ar.com.sistema.secundario.utils.Nivel;

public class Alumno {
	private Integer dni;
	private String nombre;
	private String apellido;
	private Integer edad;
	private Nivel nivel;
	

	public Alumno() {}

	public Alumno(Integer dni, String nombre, String apellido, Integer edad, Nivel nivel) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.nivel = nivel;
	}
	
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
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
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public Nivel getNivel() {
		return nivel;
	}
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return "Alumno [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", nivel="
				+ nivel + "]";
	}
	
	
	
}
