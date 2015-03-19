package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionEspacioNoValido;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.TipoEspacio;

public class TestEspacio {

	public static void main(String[] args) {

		testConstructor1Normal();
		
	}

	private static void testConstructor1Normal() {
		System.out
				.println("==================================Probando el primer constructor");
		testConstructor1(TipoEspacio.TEORIA, "Fundamentos de Programación", 100, 1);
	}
		
	private static void testConstructor1(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {

		try {
			Espacio e = new EspacioImpl(tipo, nombre, capacidad, planta);
			mostrarEspacio(e);
		} catch (ExcepcionEspacioNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionEspacioNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}

	private static void mostrarEspacio(Espacio e) {
		System.out.println("Espacio --> <" + e + ">");
		System.out.println("\tNombre: <" + e.getNombre() + ">");
		System.out.println("\tCapacidad: <" + e.getCapacidad() + ">");
		System.out.println("\tPlanta: <" + e.getPlanta() + ">");
		System.out.println("\tTipo:  <" + e.getTipo() + ">");
	}

}