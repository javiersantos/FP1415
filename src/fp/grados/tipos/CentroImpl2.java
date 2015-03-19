package fp.grados.tipos;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;


public class CentroImpl2 extends CentroImpl implements Centro {
	
	public CentroImpl2(String nombre, String direccion, Integer plantas, Integer sotanos) {
		super(nombre, direccion, plantas, sotanos);
	}
	
	public Espacio getEspacioMayorCapacidad() {
		Stream<Espacio> stream = super.getEspacios().stream();
		Optional<Espacio> opt = stream.max(Comparator.comparing(Espacio::getCapacidad));
		if(!(opt.isPresent())) {
			throw new ExcepcionCentroOperacionNoPermitida("No hay espacios en el centro.");
		}
		
		return opt.get();
	}
}
