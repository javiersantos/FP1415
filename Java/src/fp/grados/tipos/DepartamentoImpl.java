package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;

public class DepartamentoImpl implements Departamento {
	
	private String nombre;
	private Set<Asignatura> asignaturas;
	private Set<Profesor> profesores;
	
	public DepartamentoImpl(String nombre) {
		this.nombre = nombre;
		this.asignaturas = new HashSet<Asignatura>();
		this.profesores = new HashSet<Profesor>();
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public Set<Asignatura> getAsignaturas() {
		return new HashSet<Asignatura>(this.asignaturas);
	}
	
	@Override
	public Set<Profesor> getProfesores() {
		return new HashSet<Profesor>(this.profesores);
	}

	@Override
	public void nuevaAsignatura(Asignatura asig) {
		if(!(getAsignaturas().contains(asig))) {
			this.asignaturas.add(asig);
			asig.setDepartamento(this);
		}
	}

	@Override
	public void eliminaAsignatura(Asignatura asig) {
		if(getAsignaturas().contains(asig)) {
			this.asignaturas.remove(asig);
			asig.setDepartamento(null);
		}
	}
	
	@Override
	public void nuevoProfesor(Profesor prof) {
		if(!(getProfesores().contains(prof))) {
			this.profesores.add(prof);
			prof.setDepartamento(this);
		}
	}

	@Override
	public void eliminaProfesor(Profesor prof) {
		if(getProfesores().contains(prof)) {
			this.profesores.remove(prof);
			prof.setDepartamento(null);
		}
	}
	
	@Override
	public void borraTutorias() {
		for(Profesor p : getProfesores()) {
			p.borraTutorias();
		}
	}

	@Override
	public void borraTutorias(Categoria c) {
		for(Profesor p : getProfesores()) {
			if(p.getCategoria().equals(c)) {
				p.borraTutorias();
			}
		}
	}

	@Override
	public Boolean existeProfesorAsignado(Asignatura a) {
		Boolean res = false;
		for(Profesor p : getProfesores()) {
			if(p.getAsignaturas().contains(a)) {
				res = true;
				break;
			}
		}
		return res;
	}

	@Override
	public Boolean estanTodasAsignaturasAsignadas() {
		Boolean res = true;
		for(Asignatura a : getAsignaturas()) {
			if(!existeProfesorAsignado(a)) {
				res = false;
				break;
			}
		}
		return res;
	}

	@Override
	public void eliminaAsignacionProfesorado(Asignatura a) {
		for(Profesor p : getProfesores()) {
			if(p.getAsignaturas().contains(a)) {
				p.eliminaAsignatura(a);
			}
		}
	}
	
	@Override
	public SortedMap<Asignatura, SortedSet<Profesor>> getProfesoresPorAsignatura() {
		SortedMap<Asignatura, SortedSet<Profesor>> res = new TreeMap<Asignatura, SortedSet<Profesor>>();
		for(Profesor p : getProfesores()) {
			for(Asignatura a : p.getAsignaturas()) {
				if(!(res.containsKey(a))) {
					SortedSet<Profesor> aux = new TreeSet<Profesor>();
					aux.add(p);
					res.put(a, aux);
				} else {
					SortedSet<Profesor> aux = res.get(a);
					aux.add(p);
					res.put(a, aux);
				}
			}
		}
		return res;
	}

	@Override
	public SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor() {
		SortedMap<Profesor, SortedSet<Tutoria>> res = new TreeMap<Profesor, SortedSet<Tutoria>>();
		for(Profesor p : getProfesores()) {
			res.put(p, p.getTutorias());
		}
		return res;
	}
	
	@Override
	public Profesor getProfesorMaximaDedicacionMediaPorAsignatura() {
		Stream<Profesor> stream = getProfesores().stream().filter(x->x.getAsignaturas().size()>0);
		Optional<Profesor> opt = stream.max(Comparator.comparing(x->x.getDedicacionTotal()/x.getAsignaturas().size()));
		if(!(opt.isPresent())) {
			throw new NoSuchElementException("No hay profesores.");
		}
		
		return opt.get();
	}
	
	// CRITERIO DE IGUALDAD //
	public boolean equals(Object o) {
		boolean res = false;
		if(o instanceof Departamento){
			Departamento d = (Departamento)o;
			res = getNombre().equals(d.getNombre());
		}
		return res;
	}
		
	// CRITERIO DE ORDEN NATURAL //
	public int compareTo(Departamento d) {
		return getNombre().compareTo(d.getNombre());
	}
			
	public int hashCode() {
		return getNombre().hashCode();
	}
	
	public String toString() {
		return getNombre();
	}

}
