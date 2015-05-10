package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionGradoNoValido;

public class GradoImpl implements Grado {

	private String nombre;
	private Set<Asignatura> asignaturasObligatorias;
	private Set<Asignatura> asignaturasOptativas;
	private Double creditosMinimosOptativas;
	
	public GradoImpl(String nombre, Set<Asignatura> asignaturasObligatorias, Set<Asignatura> asignaturasOptativas, Double creditosMinimosOptativas) {
		checkCreditosOptativas(asignaturasOptativas);
		checkCreditosMinimosOptativas(creditosMinimosOptativas, asignaturasOptativas);
		
		this.nombre = nombre;
		this.asignaturasObligatorias = asignaturasObligatorias;
		this.asignaturasOptativas = asignaturasOptativas;
		this.creditosMinimosOptativas = creditosMinimosOptativas;
	}
	
	private void checkCreditosOptativas(Set<Asignatura> asigOpt) {
		Boolean esPrimero = true;
		Double creditos = 0.;
		for(Asignatura a : asigOpt) {
			if(esPrimero) {
				creditos = a.getCreditos();
				esPrimero = false;
			} else {
				if(!(creditos.equals(a.getCreditos()))) {
					throw new ExcepcionGradoNoValido("Todas las asignaturas optativas de un grado deben tener el mismo número de créditos.");
				}
			}
		}
	}
	
	private void checkCreditosMinimosOptativas(Double credits, Set<Asignatura> asigOpt) {
		Double creditosAlumno = credits;
		Double creditosGrado = 0.;
		for(Asignatura a : asigOpt) {
			creditosGrado = creditosGrado + a.getCreditos();
		}
		if(0 > creditosAlumno || creditosGrado < creditosAlumno) {
			throw new ExcepcionGradoNoValido("El número mínimo de créditos de asignaturas optativas que debe cursar un alumno debe estar comprendido entre cero y el número total de créditos de asignaturas optativas del grado.");
		}
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public Double getNumeroMinimoCreditosOptativas() {
		return creditosMinimosOptativas;
	}

	@Override
	public Set<Asignatura> getAsignaturasObligatorias() {
		return asignaturasObligatorias;
	}

	@Override
	public Set<Asignatura> getAsignaturasOptativas() {
		return asignaturasOptativas;
	}

	@Override
	public Double getNumeroTotalCreditos() {
		Double creditosObligatorios = 0.;
		for(Asignatura a : getAsignaturasObligatorias()) {
			creditosObligatorios = creditosObligatorios + a.getCreditos();
		}
		return creditosObligatorios + creditosMinimosOptativas;
	}

	@Override
	public Set<Departamento> getDepartamentos() {
		Set<Departamento> res = new HashSet<Departamento>();
		for(Asignatura a : getAsignaturasObligatorias()) {
			res.add(a.getDepartamento());
		}
		for(Asignatura a : getAsignaturasOptativas()) {
			res.add(a.getDepartamento());
		}
		
		return res;
	}

	@Override
	public Set<Profesor> getProfesores() {
		Set<Profesor> res = new HashSet<Profesor>();
		for(Departamento d : getDepartamentos()) {
			res.addAll(d.getProfesores());
		}
		return res;
	}

	@Override
	public Set<Asignatura> getAsignaturas(Integer curso) {
		Set<Asignatura> res = new HashSet<Asignatura>();
		for(Asignatura a : getAsignaturasObligatorias()) {
			if(a.getCurso().equals(curso)) {
				res.add(a);
			}
		}
		for(Asignatura a : getAsignaturasOptativas()) {
			if(a.getCurso().equals(curso)) {
				res.add(a);
			}
		}
		return res;
	}

	@Override
	public Asignatura getAsignatura(String codigo) {
		Asignatura res = null;
		for(Asignatura a : getAsignaturasObligatorias()) {
			if(a.getCodigo().equals(codigo)) {
				res = a;
			}
		}
		for(Asignatura a : getAsignaturasOptativas()) {
			if(a.getCodigo().equals(codigo)) {
				res = a;
			}
		}
		return res;
	}
	
	@Override
	public SortedMap<Asignatura, Double> getCreditosPorAsignatura() {
		SortedMap<Asignatura, Double> res = new TreeMap<Asignatura, Double>();
		
		for(Asignatura a : getAsignaturasObligatorias()) {
			res.put(a, a.getCreditos());
		}
		for(Asignatura a : getAsignaturasOptativas()) {
			res.put(a, a.getCreditos());
		}
		
		return res;
	}
	
	@Override
	public SortedSet<Departamento> getDepartamentosOrdenadosPorAsignaturas() {
		Comparator<Departamento> comp = Comparator.comparing(x->x.getAsignaturas().size());
		SortedSet<Departamento> res = new TreeSet<Departamento>(comp.reversed().thenComparing(Comparator.naturalOrder()));
		res.addAll(getDepartamentos());
		
		return res;
	}
	
	// CRITERIO DE IGUALDAD //
	public boolean equals(Object o) {
		boolean res = false;
		if(o instanceof Grado){
			Grado g = (Grado)o;
			res = getNombre().equals(g.getNombre());
		}
		return res;
	}
	
	// CRITERIO DE ORDEN NATURAL //
	public int compareTo(Grado g) {
		return getNombre().compareTo(g.getNombre());
	}
		
	public int hashCode() {
		return getNombre().hashCode();
	}
	
	public String toString() {
		return getNombre();
	}

}
