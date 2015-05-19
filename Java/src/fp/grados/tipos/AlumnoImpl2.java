package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlumnoImpl2 extends AlumnoImpl implements Alumno {
	
	public AlumnoImpl2(String DNI, String nombre, String apellidos, LocalDate fecha, String email){
		super(DNI, nombre, apellidos, fecha, email);
	}
	
	public AlumnoImpl2(String s) {
		super(s);
	}
	
	@Override
	public Integer getCurso() {
		Integer res = 0;
		Stream<Asignatura> stream = this.getAsignaturas().stream();
		Optional<Asignatura> opt = stream.max(Comparator.comparing(Asignatura::getCurso));
		if(opt.isPresent()) {
			res = opt.get().getCurso();
		}
		
		return res;
	}
	
	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura() {
		return this.getExpediente().getNotas().stream().collect(Collectors.toMap(Nota::getAsignatura, Nota::getCalificacion, (p1, p2) -> p1, TreeMap::new));
	}
}
