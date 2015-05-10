package fp.grados.tipos;

public interface Asignatura extends Comparable<Asignatura> {
	
	String getNombre();
	String getAcronimo();  // Derivada
	String getCodigo();
	Double getCreditos();
	TipoAsignatura getTipo();
	Integer getCurso();
	
	Departamento getDepartamento();
	void setDepartamento(Departamento d);

}
