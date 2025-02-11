package ar.com.sistema.secundario.modelo;

public class Materia {
	private Integer codMateria;
	private String nombre;
	private Integer dniProfesor;
	
	public Materia() {}
	
	public Materia(Integer codMateria, String nombre, Integer dniProfesor) {
		super();
		this.codMateria = codMateria;
		this.nombre = nombre;
		this.dniProfesor = dniProfesor;
	}

	public Integer getCodMateria() {
		return codMateria;
	}
	public void setCodMateria(Integer codMateria) {
		this.codMateria = codMateria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getDniProfesor() {
		return dniProfesor;
	}
	public void setDniProfesor(Integer dniProfesor) {
		this.dniProfesor = dniProfesor;
	}

	@Override
	public String toString() {
		return "Materia [codMateria=" + codMateria + ", nombre=" + nombre + ", dniProfesor=" + dniProfesor + "]";
	}
	
	
	
}
