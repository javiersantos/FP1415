package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;

public class ProfesorImpl2 extends PersonaImpl implements Profesor {
	
	private Categoria categoria;
	private SortedSet<Tutoria> tutorias;
//	private List<Asignatura> asignaturas;
//	private List<Double> creditos;
	private Departamento departamento;
	private Map<Asignatura, Double> asigDedicacion;
	private static final Double creditosMaxDefault = 24.;
	
	public ProfesorImpl2(String DNI, String nombre, String apellidos, LocalDate fecha, String email, Categoria categoria, Departamento departamento){
		super(DNI, nombre, apellidos, fecha, email);
		
		checkEdad(fecha);
		this.categoria = categoria;
		this.tutorias = new TreeSet<Tutoria>();
//		this.asignaturas = new ArrayList<Asignatura>();
//		this.creditos = new ArrayList<Double>();
		this.asigDedicacion = new HashMap<Asignatura, Double>();
		this.departamento = departamento;
	}
	
	private void checkEdad(LocalDate date) {
		Integer edad = (int) date.until(LocalDate.now(), ChronoUnit.YEARS);
		if(edad < 18) {
			throw new ExcepcionProfesorNoValido("El profesor tiene que ser mayor de edad.");
		}
	}
	
	private void checkAsignaturaDepartamento(Asignatura asig) {
		if(!(getDepartamento().getAsignaturas().contains(asig))) {
			throw new ExcepcionProfesorOperacionNoPermitida("En el departamento no está incluido la asignatura.");
		}
	}
	
	private void checkCreditosAsignatura(Asignatura asig, Double dedicacion) {
		if(!(dedicacion > 0)) {
			throw new ExcepcionProfesorOperacionNoPermitida("El número de créditos impartidos por un profesor en una asignatura debe ser mayor que 0.");
		} else {
			if(dedicacion > asig.getCreditos()) {
				throw new ExcepcionProfesorOperacionNoPermitida("El número de créditos impartidos por un profesor en una asignatura debe ser mayor que 0 y menor o igual que el número de créditos de dicha asignatura.");
			}
		}
	}
	
	private void checkCreditos() {
		if(this.getDedicacionTotal() > creditosMaxDefault) {
			throw new ExcepcionProfesorOperacionNoPermitida("El profesor no puede impartir más de " + creditosMaxDefault + " créditos.");
		}
	}

	@Override
	public Categoria getCategoria() {
		return categoria;
	}

	@Override
	public SortedSet<Tutoria> getTutorias() {
		return new TreeSet<Tutoria>(this.tutorias);
	}
	
	@Override
	public void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos,
			DayOfWeek dia) {
		Tutoria t = new TutoriaImpl(dia, horaComienzo, duracionMinutos);
		this.tutorias.add(t);
	}

	@Override
	public void borraTutoria(LocalTime horaComienzo, DayOfWeek dia) {
		for(Tutoria t : tutorias) {
			if(t.getHoraComienzo().equals(horaComienzo) && t.getDiaSemana().equals(dia)) {
				this.tutorias.remove(t);
			}
		}
	}

	@Override
	public void borraTutorias() {
		tutorias.clear();
	}
	
	public String toString() {
		return super.toString() + " (" + getCategoria() + ")"; 
	}

	@Override
	public List<Asignatura> getAsignaturas() {
		return new ArrayList<Asignatura>(this.asigDedicacion.keySet());
	}

	@Override
	public List<Double> getCreditos() {
		return new ArrayList<Double>(this.asigDedicacion.values());
	}

	@Override
	public Double getDedicacionTotal() {
		Double creditos = 0.;
		for(Asignatura a : getAsignaturas()) {
			creditos = creditos + this.dedicacionAsignatura(a);
		}
		if(creditos > creditosMaxDefault) {
			throw new ExcepcionProfesorOperacionNoPermitida("Los creditos son superior a " + creditosMaxDefault);
		}
		return creditos;
	}

	@Override
	public void imparteAsignatura(Asignatura asig, Double dedicacion) {
		checkAsignaturaDepartamento(asig);
		checkCreditosAsignatura(asig, dedicacion);
		
		this.asigDedicacion.put(asig, dedicacion);
		
		checkCreditos();
	}

	@Override
	public Double dedicacionAsignatura(Asignatura asig) {
		Double res = 0.;
		if (this.asigDedicacion.containsKey(asig)) {
			res = this.asigDedicacion.get(asig);
		}
		return res;
	}

	@Override
	public void eliminaAsignatura(Asignatura asig) {
		if(this.asigDedicacion.containsKey(asig)) {
			this.asigDedicacion.remove(asig);
		}
	}

	@Override
	public Departamento getDepartamento() {
		return departamento;
	}
	
	@Override
	public void setFechaNacimiento(LocalDate date) {
		checkEdad(date);
		super.setFechaNacimiento(date);
	}
	
	@Override
	public void setCategoria(Categoria c) {
		this.categoria = c;		
	}

	@Override
	public void setDepartamento(Departamento d) {
		if(this.getDepartamento() != d) {
			
			if(this.getDepartamento() != null) {
				this.getDepartamento().eliminaProfesor(this);
			}
			this.departamento = d;
			if(d != null) {
				d.nuevoProfesor(this);
			}
		}
	}
	
}
