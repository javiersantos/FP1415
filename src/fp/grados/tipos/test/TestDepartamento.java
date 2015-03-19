package fp.grados.tipos.test;

import java.time.LocalDate;

import fp.grados.excepciones.ExcepcionDepartamentoNoValido;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestDepartamento {
	
	public static void main(String[] args) {

		testExisteProfesorAsignado();
		testConstructor1Normal();
		testAsignaturaNormal();
		testProfesorNormal();

	}
	
	private static void testExisteProfesorAsignado() {
		
		Departamento d = new DepartamentoImpl("LSI");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, d);
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, d);
		d.nuevoProfesor(p1);
		d.nuevaAsignatura(a);
		System.out.println(d.getAsignaturas());
		System.out.println(d.getProfesores());
		System.out.println(d.existeProfesorAsignado(a));
		
	}
	
	private static void testConstructor1Normal() {
	
		System.out
				.println("\n==================================Probando el primer constructor");
		testConstructor1("LSI");
	}
	
	private static void testAsignaturaNormal() {
		
		System.out
				.println("\n==================================Probando añadir asignatura");
		testAsignatura();
	}
	
	private static void testProfesorNormal() {
		
		System.out
				.println("\n==================================Probando añadir profesor");
		testProfesor();
	}
	
	private static void testConstructor1(String nombre) {

		try {
			Departamento d = new DepartamentoImpl(nombre);
			mostrarDepartamento(d);
		} catch (ExcepcionDepartamentoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionDepartamentoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void testAsignatura() {

		try {
			Departamento d = new DepartamentoImpl("LSI");
			Asignatura asig = new AsignaturaImpl("FP", "2415687", 12., TipoAsignatura.ANUAL, 1, d);
			d.nuevaAsignatura(asig);
			mostrarDepartamento(d);
		} catch (ExcepcionDepartamentoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionDepartamentoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void testProfesor() {

		try {
			Departamento d = new DepartamentoImpl("LSI");
			Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, d);
			d.nuevoProfesor(p);
			mostrarDepartamento(d);
		} catch (ExcepcionDepartamentoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionDepartamentoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void mostrarDepartamento(Departamento d) {
		System.out.println("Departamento --> <" + d + ">");
		System.out.println("\tNombre: <" + d.getNombre() + ">");
		System.out.println("\tAsignaturas: <" + d.getAsignaturas() + ">");
		System.out.println("\tProfesores: <" + d.getProfesores() + ">");
	}

}
