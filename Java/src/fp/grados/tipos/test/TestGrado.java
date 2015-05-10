package fp.grados.tipos.test;

import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionGradoNoValido;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Grado;
import fp.grados.tipos.GradoImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestGrado {
	
	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcion();
		
	}

	private static void testConstructor1Normal() {
		System.out
				.println("==================================Probando el primer constructor");
		Departamento d = new DepartamentoImpl("LSI");
		Set<Asignatura> asigOblig = new HashSet<Asignatura>();
		Set<Asignatura> asigOpt = new HashSet<Asignatura>();
		asigOblig.add(new AsignaturaImpl("FP", "1254875", 12., TipoAsignatura.ANUAL, 1, d));
		asigOpt.add(new AsignaturaImpl("FFI", "1254876", 6., TipoAsignatura.PRIMER_CUATRIMESTRE, 1, d));
		testConstructor1("Software", asigOblig, asigOpt, 6.);
	}
	
	private static void testConstructor1Excepcion() {
		System.out
		.println("==================================Probando el primer constructor, créditos totales son menores a las asignaturas optativas");
		Departamento d = new DepartamentoImpl("LSI");
		Set<Asignatura> asigOblig = new HashSet<Asignatura>();
		Set<Asignatura> asigOpt = new HashSet<Asignatura>();
		asigOblig.add(new AsignaturaImpl("FP", "1254875", 12., TipoAsignatura.ANUAL, 1, d));
		asigOpt.add(new AsignaturaImpl("FFI", "1254876", 6., TipoAsignatura.PRIMER_CUATRIMESTRE, 1, d));
		testConstructor1("Software", asigOblig, asigOpt, 12.);
	}
	
	private static void testConstructor1(String nombre, Set<Asignatura> asignaturasObligatorias, Set<Asignatura> asignaturasOptativas, Double creditosMinimosOptativas) {

		try {
			Grado g = new GradoImpl(nombre, asignaturasObligatorias, asignaturasOptativas, creditosMinimosOptativas);
			mostrarGrado(g);
		} catch (ExcepcionGradoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionGradoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}
	
	private static void mostrarGrado(Grado g) {
		System.out.println("Grado --> <" + g + ">");
		System.out.println("\tNombre: <" + g.getNombre() + ">");
		System.out.println("\tAsignaturas obligatorias: <" + g.getAsignaturasObligatorias() + ">");
		System.out.println("\tAsignaturas optativas: <" + g.getAsignaturasOptativas() + ">");
		System.out.println("\tCréditos:  <" + g.getNumeroTotalCreditos() + ">");
	}

}
