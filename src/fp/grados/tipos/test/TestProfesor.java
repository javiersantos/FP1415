package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestProfesor {
	
	public static void main(String[] args) {

		testImparteAsignatura();
		testConstructor1Normal();
		testNuevaTutoria();
		testBorraTutoria();
		testBorraTutorias();

	}
	
	private static void testImparteAsignatura() {
		Departamento dep = new DepartamentoImpl("LSI");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, dep);
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, dep);
		dep.nuevaAsignatura(a1);
		p1.imparteAsignatura(a1, 6.);
		System.out.println("Asignaturas: " + p1.getAsignaturas());
	}
	
	private static void testConstructor1Normal() {
		
		System.out
				.println("\n==================================Probando el primer constructor");
		Departamento dep = new DepartamentoImpl("LSI");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1960, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, dep);

	}
	
	private static void testNuevaTutoria() {
		
		System.out
				.println("\n==================================Probando a agregar nueva tutoria");
		nuevaTutoria(LocalTime.of(10, 30), 30, DayOfWeek.FRIDAY);

	}
	
	private static void testBorraTutoria() {
		
		System.out
				.println("\n==================================Probando a borrar una tutoria");
		borraTutoria(LocalTime.of(10, 30), DayOfWeek.FRIDAY);

	}
	
	private static void testBorraTutorias() {
		
		System.out
				.println("\n==================================Probando a borrar tutorias");
		borraTutorias();

	}
	
	private static void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos,
			DayOfWeek dia) {
		Departamento dep = new DepartamentoImpl("LSI");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, dep);
		
		try {
			p1.nuevaTutoria(horaComienzo, duracionMinutos, dia);
		} catch (ExcepcionProfesorNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void borraTutoria(LocalTime horaComienzo, DayOfWeek dia) {
		Departamento dep = new DepartamentoImpl("LSI");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, dep);
		
		try {
			p1.borraTutoria(horaComienzo, dia);
		} catch (ExcepcionProfesorNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void borraTutorias() {
		Departamento dep = new DepartamentoImpl("LSI");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, dep);
		
		try {
			p1.borraTutorias();
		} catch (ExcepcionProfesorNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void testConstructor1(String DNI, String nombre, String apellidos, LocalDate fecha, String email, Categoria categoria, Departamento departamento) {

		try {
			Profesor p = new ProfesorImpl(DNI, nombre, apellidos, fecha, email, categoria, departamento);
			mostrarProfesor(p);
		} catch (ExcepcionProfesorNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void mostrarProfesor(Profesor p) {
		System.out.println("Profesor --> <" + p + ">");
		System.out.println("\tDNI: <" + p.getDNI() + ">");
		System.out.println("\tNombre: <" + p.getNombre() + ">");
		System.out.println("\tApellidos: <" + p.getApellidos() + ">");
		System.out.println("\tFecha:  <" + p.getFechaNacimiento() + ">");
		System.out.println("\tEmail:  <" + p.getEmail() + ">");
		System.out.println("\tCategoría:  <" + p.getCategoria() + ">");
	}

}
