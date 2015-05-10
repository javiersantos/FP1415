package fp.grados.tipos;

public interface Espacio extends Comparable<Espacio> {
	
	TipoEspacio getTipo();
	String getNombre();
	Integer getCapacidad();
	Integer getPlanta();
	
	void setTipo(TipoEspacio t);
	void setNombre(String n);
	void setCapacidad(Integer c);

}
