package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;

public class AlumnoImpl extends PersonaImpl implements Alumno {
	
	private Set<Asignatura> asignaturas;
	private Expediente expediente;
	
	public AlumnoImpl(String DNI, String nombre, String apellidos, LocalDate fecha, String email){
		super(DNI, nombre, apellidos, fecha, email);
		checkEmail(email);
		this.asignaturas = new HashSet<Asignatura>();
		this.expediente = new ExpedienteImpl();
	}
	
	public AlumnoImpl(String s) {
		super(s);
		checkEmail(super.getEmail());
		this.asignaturas = new HashSet<Asignatura>();
		this.expediente = new ExpedienteImpl();
	}
	
	private void checkEmail(String email) {
		if(!(email.endsWith("@alum.us.es"))){
			throw new ExcepcionAlumnoNoValido("El email no es @alum.es.es");
		}
	}

	@Override
	public Set<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	@Override
	public Integer getCurso() {
		Integer res = 0;
		for(Asignatura a : getAsignaturas()) {
			if(a.getCurso()>res) {
				res = a.getCurso();
			}
		}
		return res;
	}
	
	@Override
	public void setEmail(String email) {
		checkEmail(email);
		super.setEmail(email);
	}
	
	@Override
	public void matriculaAsignatura(Asignatura asig) {
		if(estaMatriculadoEn(asig)){
			throw new ExcepcionAlumnoOperacionNoPermitida("La asignatura ya existe en el conjunto.");
		} else {
			asignaturas.add(asig);
		}
	}
	
	@Override
	public void eliminaAsignatura(Asignatura asig) {
		if(!(estaMatriculadoEn(asig))){
			throw new ExcepcionAlumnoOperacionNoPermitida("La asignatura no existe en el conjunto.");
		} else {
			asignaturas.remove(asig);
		}
	}
	
	@Override
	public Boolean estaMatriculadoEn(Asignatura asig) {
		return asignaturas.contains(asig);
	}
	
	@Override
	public Expediente getExpediente() {
		return expediente;
	}
	
	@Override
	public void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria, Double nota, Boolean mencionHonor) {
		if(estaMatriculadoEn(a)) {
			expediente.nuevaNota(new NotaImpl(a, curso, convocatoria, nota, mencionHonor));
		} else {
			throw new ExcepcionAlumnoOperacionNoPermitida("El alumno no está matriculado de la asignatura.");
		}
	}
	
	@Override
	public void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria, Double nota) {
		if(estaMatriculadoEn(a)) {
			expediente.nuevaNota(new NotaImpl(a, curso, convocatoria, nota));
		} else {
			throw new ExcepcionAlumnoOperacionNoPermitida("El alumno no está matriculado de la asignatura.");
		}
	
	}
	
	@Override
	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura() {
		SortedMap<Asignatura, Calificacion> res = new TreeMap<Asignatura, Calificacion>();
		
		for(Nota n : getExpediente().getNotas()) {
			if(getAsignaturas().contains(n.getAsignatura())) {
				if(!(res.containsKey(n.getAsignatura()))) {
					res.put(n.getAsignatura(), n.getCalificacion());
				} else {
					Integer aux = res.get(n.getAsignatura()).ordinal();
					if(n.getCalificacion().ordinal() > aux) {
						res.put(n.getAsignatura(), n.getCalificacion());
					}
				}
			}
			
		}
		
		return res;
	}
	
	@Override
	public SortedSet<Asignatura> getAsignaturasOrdenadasPorCurso() {
		Comparator<Asignatura> comp = Comparator.comparing(Asignatura::getCurso);
		SortedSet<Asignatura> res = new TreeSet<Asignatura>(comp.reversed().thenComparing(Comparator.naturalOrder()));
		res.addAll(getAsignaturas());
		
		return res;
	}
	
	public String toString() {
		return "(" + getCurso() + "º) " + super.toString();
	}
	
}
