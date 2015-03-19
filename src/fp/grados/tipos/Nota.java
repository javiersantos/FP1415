package fp.grados.tipos;

public interface Nota extends Comparable<Nota> {
	
	Asignatura getAsignatura();
	Integer getCursoAcademico();
	Double getValor();
	Convocatoria getConvocatoria();
	Boolean getMencionHonor();
	Calificacion getCalificacion();

}
