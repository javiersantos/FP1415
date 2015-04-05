package fp.grados.tipos;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
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
	
	public Integer[] getConteosEspacios() {
		Stream<Espacio> stream = super.getEspacios().stream();
		
		return new Integer[] {contarUnTipo(stream, TipoEspacio.TEORIA), contarUnTipo(stream, TipoEspacio.LABORATORIO), contarUnTipo(stream, TipoEspacio.SEMINARIO), contarUnTipo(stream, TipoEspacio.EXAMEN), contarUnTipo(stream, TipoEspacio.OTRO)};
	}
	
	private int contarUnTipo(Stream<Espacio> stream, TipoEspacio te) {
		return (int) stream.filter(e -> e.getTipo().equals(te)).count();
	}
	
	public Set<Profesor> getProfesores() {
		return this.getDespachos().stream().flatMap(d -> d.getProfesores().stream()).collect(Collectors.toSet());
	}

	public Set<Profesor> getProfesores(Departamento d) {
		return this.getProfesores().stream().filter(p -> p.getDepartamento().equals(d)).collect(Collectors.toSet());
	}
	
	// http://www.leveluplunch.com/java/examples/filter-collection-by-class-type/
	public Set<Despacho> getDespachos() {
		return this.getEspacios().stream().filter(e -> e instanceof Despacho).map(e -> (Despacho) e).collect(Collectors.toSet());
	}
	
	public Set<Despacho> getDespachos(Departamento d) {
		return this.getDespachos().stream().filter(dep -> existeProfesorDepartamento(d)).collect(Collectors.toSet());
		
	}
	
	// existeProfesorDepartamento
	private Boolean existeProfesorDepartamento(Departamento d) {
		return this.getProfesores().stream().anyMatch(p -> p.getDepartamento().equals(d));
	}
	
}
