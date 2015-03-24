package fp.grados.tipos;

import java.util.stream.Stream;

public class DepartamentoImpl2 extends DepartamentoImpl implements Departamento {

	public DepartamentoImpl2(String nombre) {
		super(nombre);
	}
	
	public void borraTutorias() {
		super.getProfesores().stream().forEach(Profesor::borraTutorias);
	}
	
	public void borraTutorias(Categoria categoria) {
		super.getProfesores().stream().filter(p -> p.getCategoria().equals(categoria)).forEach(Profesor::borraTutorias);
	}
	
	public Boolean existeProfesorAsignado(Asignatura a) {
		Boolean res = false;
		Stream<Profesor> stream = super.getProfesores().stream().filter(p -> p.getAsignaturas().contains(a));
		if(stream.count() > 0) {
			res = true;
		}
		
		return res;
	}
	
	public Boolean estanTodasAsignaturasAsignadas() {
		Boolean res = true;
		Stream<Asignatura> stream = super.getAsignaturas().stream().forEach();
	}
	
	public void eliminaAsignacionProfesorado(Asignatura a) {
		super.getProfesores().stream().filter(p -> p.getAsignaturas().contains(a)).forEach(Profesor::eliminaAsignatura);
	}
	
}
