package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;

public class ProfesorImpl extends PersonaImpl implements Profesor {
	
	private Categoria categoria;
	private SortedSet<Tutoria> tutorias;
	private List<Asignatura> asignaturas;
	private List<Double> creditos;
	private Departamento departamento;
	private static final Double creditosMaxDefault = 24.;
	
	public ProfesorImpl(String DNI, String nombre, String apellidos, LocalDate fecha, String email, Categoria categoria, Departamento departamento){
		super(DNI, nombre, apellidos, fecha, email);
		
		checkEdad(fecha);
		this.categoria = categoria;
		this.tutorias = new TreeSet<Tutoria>();
		this.asignaturas = new ArrayList<Asignatura>();
		this.creditos = new ArrayList<Double>();
		this.departamento = departamento;
		if(departamento != null) {
			departamento.nuevoProfesor(this);
		}
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
		for(Tutoria t : getTutorias()) {
			if(t.getHoraComienzo().equals(horaComienzo) && t.getDiaSemana().equals(dia)) {
				this.tutorias.remove(t);
			}
		}
	}

	@Override
	public void borraTutorias() {
		this.tutorias.clear();
	}
	
	public String toString() {
		return super.toString() + " (" + getCategoria() + ")"; 
	}

	@Override
	public List<Asignatura> getAsignaturas() {
		return new ArrayList<Asignatura>(this.asignaturas);
	}

	@Override
	public List<Double> getCreditos() {
		return new ArrayList<Double>(this.creditos);
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
		
		if(getAsignaturas().contains(asig)) {
			this.creditos.set(asignaturas.indexOf(asig), dedicacion);
		} else {
			this.asignaturas.add(asig);
			this.creditos.add(dedicacion);
		}
		checkCreditos();
	}

	@Override
	public Double dedicacionAsignatura(Asignatura asig) {
		if(getAsignaturas().contains(asig)) {
			return creditos.get(asignaturas.indexOf(asig));
		} else {
			return 0.0;
		}
	}

	@Override
	public void eliminaAsignatura(Asignatura asig) {
		if(getAsignaturas().contains(asig)) {
			this.creditos.remove(asignaturas.indexOf(asig));
			this.asignaturas.remove(asig);
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
