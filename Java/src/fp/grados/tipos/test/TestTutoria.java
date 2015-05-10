package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;
import fp.grados.tipos.Tutoria;
import fp.grados.tipos.TutoriaImpl;

public class TestTutoria {

	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor2Normal();
		
	}

	private static void testConstructor1Normal() {
		System.out
				.println("==================================Probando el primer constructor");
		testConstructor1(DayOfWeek.FRIDAY, LocalTime.of(10, 30), LocalTime.of(11, 30));
	}
	
	private static void testConstructor2Normal() {
		System.out
				.println("==================================Probando el segundo constructor");
		testConstructor2(DayOfWeek.FRIDAY, LocalTime.of(10, 30), 30);
	}
		
	private static void testConstructor1(DayOfWeek diaSemana, LocalTime horaComienzo, LocalTime horaFin) {

		try {
			Tutoria t = new TutoriaImpl(diaSemana, horaComienzo, horaFin);
			mostrarTutoria(t);
		} catch (ExcepcionTutoriaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}
	
	private static void testConstructor2(DayOfWeek diaSemana, LocalTime horaComienzo, Integer duracion) {

		try {
			Tutoria t = new TutoriaImpl(diaSemana, horaComienzo, duracion);
			mostrarTutoria(t);
		} catch (ExcepcionTutoriaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}	

	private static void mostrarTutoria(Tutoria t) {
		System.out.println("Tutoria --> <" + t + ">");
		System.out.println("\tDia de la Semana: <" + t.getDiaSemana() + ">");
		System.out.println("\tHora de Comienzo: <" + t.getHoraComienzo() + ">");
		System.out.println("\tHora de Fin: <" + t.getHoraFin() + ">");
		System.out.println("\tDuración:  <" + t.getDuracion() + ">");
	}

}