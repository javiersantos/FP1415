package fp.grados.tipos.test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoEspacio;

public class TestDespacho {
	
	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor2Normal();
		testConstructor3Normal();

		testSetTipoExcepcion();

	}
	
	private static void testConstructor1Normal() {
		Set<Profesor> listaProfesores = new HashSet<Profesor>();
		Departamento d = new DepartamentoImpl("LSI");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, d);
		listaProfesores.add(p1);
		
		System.out
				.println("\n==================================Probando el primer constructor");
		testConstructor1("Juan", 10, 2, listaProfesores);
	}
	
	private static void testConstructor2Normal() {
		Departamento d = new DepartamentoImpl("LSI");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, d);
		
		System.out
				.println("\n==================================Probando el segundo constructor");
		testConstructor2("Juan", 10, 2, p1);
	}
	
	private static void testConstructor3Normal() {
		
		System.out
				.println("\n==================================Probando el segundo constructor");
		testConstructor3("Juan", 10, 2);
	}
	
	private static void testConstructor1(String nombre, Integer capacidad, Integer planta, Set<Profesor> profesores) {

		try {
			Despacho d = new DespachoImpl(nombre, capacidad, planta, profesores);
			mostrarDespacho(d);
		} catch (ExcepcionDespachoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionDespachoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void testConstructor2(String nombre, Integer capacidad, Integer planta, Profesor profesor) {

		try {
			Despacho d = new DespachoImpl(nombre, capacidad, planta, profesor);
			mostrarDespacho(d);
		} catch (ExcepcionDespachoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionDespachoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void testConstructor3(String nombre, Integer capacidad, Integer planta) {

		try {
			Despacho d = new DespachoImpl(nombre, capacidad, planta);
			mostrarDespacho(d);
		} catch (ExcepcionDespachoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionDespachoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void testSetTipoExcepcion() {
		System.out
				.println("\n==================================Probando setTipo");

		Departamento dep = new DepartamentoImpl("LSI");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, dep);
		Despacho des = new DespachoImpl("Juan", 10, 2, p1);

		try {
			des.setTipo(TipoEspacio.EXAMEN);
		} catch (UnsupportedOperationException e) {
			System.out
					.println("******************** Se ha capturado la excepción UnsupportedOperationException");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void mostrarDespacho(Despacho d) {
		System.out.println("Despacho --> <" + d + ">");
		System.out.println("\tNombre: <" + d.getNombre() + ">");
		System.out.println("\tCapacidad: <" + d.getCapacidad() + ">");
		System.out.println("\tPlanta: <" + d.getPlanta() + ">");
		System.out.println("\tProfesores:  <" + d.getProfesores() + ">");
	}

}
