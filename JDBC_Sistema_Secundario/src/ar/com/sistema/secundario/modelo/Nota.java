package ar.com.sistema.secundario.modelo;

public class Nota {
	private Integer codigo;
	private Integer nota;
	private Integer dniAlumno;
	private Integer dniProfesor;
	private Integer codMateria;
	
	public Nota() {}
	
	public Nota(Integer codigo, Integer nota, Integer dniAlumno, Integer dniProfesor, Integer codMateria) {
		super();
		this.codigo = codigo;
		this.nota = nota;
		this.dniAlumno = dniAlumno;
		this.dniProfesor = dniProfesor;
		this.codMateria = codMateria;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	public Integer getDniAlumno() {
		return dniAlumno;
	}
	public void setDniAlumno(Integer dniAlumno) {
		this.dniAlumno = dniAlumno;
	}
	public Integer getDniProfesor() {
		return dniProfesor;
	}
	public void setDniProfesor(Integer dniProfesor) {
		this.dniProfesor = dniProfesor;
	}
	public Integer getCodMateria() {
		return codMateria;
	}
	public void setCodMateria(Integer codMateria) {
		this.codMateria = codMateria;
	}

	@Override
	public String toString() {
		return "Nota [codigo=" + codigo + ", nota=" + nota + ", dniAlumno=" + dniAlumno + ", dniProfesor=" + dniProfesor
				+ ", codMateria=" + codMateria + "]";
	}
	
	
}
