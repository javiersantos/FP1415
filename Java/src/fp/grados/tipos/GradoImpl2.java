package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GradoImpl2 extends GradoImpl implements Grado {

	public GradoImpl2(String nombre, Set<Asignatura> asignaturasObligatorias,
			Set<Asignatura> asignaturasOptativas,
			Double creditosMinimosOptativas) {
		super(nombre, asignaturasObligatorias, asignaturasOptativas,
				creditosMinimosOptativas);
	}
	
	public Double getNumeroTotalCreditos() {
		return this.getNumeroMinimoCreditosOptativas() + this.getAsignaturasObligatorias().stream().mapToDouble(Asignatura::getCreditos).sum();
	}
	
	public Set<Asignatura> getAsignaturas(Integer curso) {
		return Stream.concat(this.getAsignaturasObligatorias().stream(), this.getAsignaturasOptativas().stream()).filter(a -> a.getCurso().equals(curso)).collect(Collectors.toSet());
	}
	
	public Asignatura getAsignatura(String codigo) {
		return Stream.concat(this.getAsignaturasObligatorias().stream(), this.getAsignaturasOptativas().stream()).filter(a -> a.getCodigo().equals(codigo)).findFirst().get();
	}

	public Set<Departamento> getDepartamentos() {
		return Stream.concat(this.getAsignaturasObligatorias().stream(), this.getAsignaturasOptativas().stream()).map(Asignatura::getDepartamento).collect(Collectors.toSet());
	}
	
	public Set<Profesor> getProfesores() {
		return this.getDepartamentos().stream().flatMap(d -> d.getProfesores().stream()).collect(Collectors.toSet());
	}
	
	public SortedMap<Asignatura, Double> getCreditosPorAsignatura() {
		return Stream.concat(this.getAsignaturasObligatorias().stream(), this.getAsignaturasOptativas().stream()).collect(Collectors.toMap(a -> a, a -> a.getCreditos(), (a1, a2) -> a1, TreeMap::new));
	}

}
