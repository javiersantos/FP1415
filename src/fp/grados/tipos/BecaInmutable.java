package fp.grados.tipos;

public interface BecaInmutable extends Comparable<BecaInmutable> {
	
	String getCodigo();
	Double getCuantiaTotal();
	Integer getDuracion();
	TipoBeca getTipo();
	Double getCuantiaMensual(); // getCuantiaTotal/getDuracion
		
}
