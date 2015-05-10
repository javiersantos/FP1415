package fp.grados.tipos;

public interface Beca extends Comparable<Beca> {
	
	String getCodigo();
	Double getCuantiaTotal();
	Integer getDuracion();
	TipoBeca getTipo();
	Double getCuantiaMensual(); // getCuantiaTotal/getDuracion

	void setCuantiaTotal(Double ct);
	void setDuracion(Integer d);
		
}
