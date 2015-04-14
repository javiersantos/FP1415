package fp.grados.tipos;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class DepartamentoImpl2 extends DepartamentoImpl implements Departamento {

	public DepartamentoImpl2(String nombre) {
		super(nombre);
	}
	
	public void borraTutorias() {
		super.getProfesores().stream().forEach(Profesor::borraTutorias);
	}
	
	public void borraTutorias(Categoria categoria) {
		super.getProfesores().stream().filter(p -> p.getCategoria().equals(categoria)).forEach(Profesor::borraTutorias);
	}
	
	public Boolean existeProfesorAsignado(Asignatura a) {
		return super.getProfesores().stream().anyMatch(x -> x.getAsignaturas().contains(a));
//		Boolean res = false;
//		Stream<Profesor> stream = super.getProfesores().stream().filter(p -> p.getAsignaturas().contains(a));
//		if(stream.count() > 0) {
//			res = true;
//		}
//		
//		return res;
	}
	
	public Boolean estanTodasAsignaturasAsignadas() {
		return getAsignaturas().stream().allMatch(x->existeProfesorAsignado(x));
	}
	
	public void eliminaAsignacionProfesorado(Asignatura a) {
		super.getProfesores().stream().filter(p -> p.getAsignaturas().contains(a)).forEach(x -> x.eliminaAsignatura(a));
	}
	
	public SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor() {
		return this.getProfesores().stream().collect(Collectors.toMap(p -> p,  p -> p.getTutorias(), (p1, p2) -> p1, TreeMap::new));
	}
	
}
