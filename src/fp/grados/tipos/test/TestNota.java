package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionNotaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.Convocatoria;

public class TestNota {

	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcion();
		testConstructor2Normal();
		
	}

	private static void testConstructor1Normal() {
		System.out
				.println("==================================Probando el primer constructor");
		Departamento dep = new DepartamentoImpl("LSI");
		testConstructor1(new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, dep), 2, Convocatoria.PRIMERA, 8.0, false);
	}
	
	private static void testConstructor1Excepcion() {
		System.out
		.println("==================================Probando el primer constructor, Matrícula de Honor incorrecto");
		Departamento dep = new DepartamentoImpl("LSI");
testConstructor1(new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, dep), 2, Convocatoria.PRIMERA, 8.0, true);
	}
	
	private static void testConstructor2Normal() {
		System.out
				.println("==================================Probando el segundo constructor");
		Departamento dep = new DepartamentoImpl("LSI");
		testConstructor2(new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, dep), 2, Convocatoria.PRIMERA, 8.0);
	}
		
	private static void testConstructor1(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor, Boolean mencionDeHonor) {

		try {
			Nota n = new NotaImpl(asignatura, cursoAcademico, convocatoria, valor, mencionDeHonor);
			mostrarNota(n);
		} catch (ExcepcionNotaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}
	
	private static void testConstructor2(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor) {

		try {
			Nota n = new NotaImpl(asignatura, cursoAcademico, convocatoria, valor);
			mostrarNota(n);
		} catch (ExcepcionNotaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}	

	private static void mostrarNota(Nota n) {
		System.out.println("Nota --> <" + n + ">");
		System.out.println("\tAsignatura: <" + n.getAsignatura() + ">");
		System.out.println("\tCalificación: <" + n.getCalificacion() + ">");
		System.out.println("\tConvocatoria: <" + n.getConvocatoria() + ">");
		System.out.println("\tCurso Académico:  <" + n.getCursoAcademico() + ">");
		System.out.println("\tValor:  <" + n.getValor() + ">");
		System.out.println("\tMención de Honor:  <" + n.getMencionHonor() + ">");
	}

}