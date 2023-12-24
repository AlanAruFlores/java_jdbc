package ar.com.sistema.secundario.modelo;

public class AlumnoMateria {
	private Integer dniAlumno;
	private Integer codMateria;
	
	
	public AlumnoMateria() {}

	public AlumnoMateria(Integer dniAlumno, Integer codMateria) {
		this.dniAlumno = dniAlumno;
		this.codMateria = codMateria;
	}

	public Integer getDniAlumno() {
		return dniAlumno;
	}

	public void setDniAlumno(Integer dniAlumno) {
		this.dniAlumno = dniAlumno;
	}

	public Integer getCodMateria() {
		return codMateria;
	}

	public void setCodMateria(Integer codMateria) {
		this.codMateria = codMateria;
	}

	@Override
	public String toString() {
		return "AlumnoMateria [dniAlumno=" + dniAlumno + ", codMateria=" + codMateria + "]";
	}
	
	
}
