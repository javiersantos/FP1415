package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.TipoEspacio;

public class TestCentro {
	
	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcion();
		testIncluirEspacio();
		
	}

	private static void testConstructor1Normal() {
		System.out
				.println("==================================Probando el primer constructor");
		testConstructor1("ETSII", "Reina Mercedes", 3, 1);
	}
	
	private static void testConstructor1Excepcion() {
		System.out
		.println("==================================Probando el primer constructor, plantas menor de 1");
		testConstructor1("ETSII", "Reina Mercedes", 0, 1);
	}
	
	private static void testIncluirEspacio() {
		System.out
		.println("==================================Probando a añadir espacios");
		Centro c = new CentroImpl("ETSII", "Reina Mercedes", 3, 1);
		Espacio e1 = new EspacioImpl(TipoEspacio.OTRO, "Grupo 1", 200, 1);
		Espacio e2 = new EspacioImpl(TipoEspacio.OTRO, "Grupo 2", 200, 1);
		Espacio e3 = new EspacioImpl(TipoEspacio.OTRO, "Grupo 3", 200, 1);
		c.nuevoEspacio(e1);
		c.nuevoEspacio(e2);
		c.nuevoEspacio(e3);
		
		System.out.println(c.getEspacios());
	}
	
	private static void testConstructor1(String nombre, String direccion, Integer plantas, Integer sotanos) {

		try {
			Centro c = new CentroImpl(nombre, direccion, plantas, sotanos);
			mostrarCentro(c);
		} catch (ExcepcionCentroNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionCentroNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}
	
	private static void mostrarCentro(Centro c) {
		System.out.println("Centro --> <" + c + ">");
		System.out.println("\tNombre: <" + c.getNombre() + ">");
		System.out.println("\tDireccion: <" + c.getDireccion() + ">");
		System.out.println("\tPlantas: <" + c.getNumeroPlantas() + ">");
		System.out.println("\tSótanos:  <" + c.getNumeroSotanos() + ">");
	}

}
