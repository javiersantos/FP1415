package fp.grados.tipos;

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
	
}
