package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;

public class AlumnoImpl2 extends AlumnoImpl implements Alumno {
	
	public AlumnoImpl2(String DNI, String nombre, String apellidos, LocalDate fecha, String email){
		super(DNI, nombre, apellidos, fecha, email);
	}
	
	public AlumnoImpl2(String s) {
		super(s);
	}
	
	@Override
	public Integer getCurso() {
		Stream<Asignatura> stream = super.getAsignaturas().stream();
		Optional<Asignatura> opt = stream.max(Comparator.comparing(Asignatura::getCurso));
		if(!(opt.isPresent())) {
			throw new ExcepcionAlumnoOperacionNoPermitida("No hay asignaturas en el alumno.");
		}
		
		return opt.get().getCurso();
	}
	
	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura() {
		return this.getExpediente().getNotas().stream().collect(Collectors.toMap(n -> n.getAsignatura(), n -> n.getCalificacion(), (n1, n2) -> n1, TreeMap::new));
	}

}
