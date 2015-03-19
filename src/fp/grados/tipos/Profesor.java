package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.SortedSet;

public interface Profesor extends Persona {
	
	Categoria getCategoria();
	SortedSet<Tutoria> getTutorias();
	List<Asignatura> getAsignaturas();
	List<Double> getCreditos();
	Double getDedicacionTotal();
	
	void setCategoria(Categoria c);
	void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos, DayOfWeek dia);
	void borraTutoria(LocalTime horaComienzo, DayOfWeek dia);
	void borraTutorias();
	void imparteAsignatura(Asignatura asig, Double dedicacion);
	Double dedicacionAsignatura(Asignatura asig);
	void eliminaAsignatura(Asignatura asig);
	
	Departamento getDepartamento();
	void setDepartamento(Departamento d);

}
