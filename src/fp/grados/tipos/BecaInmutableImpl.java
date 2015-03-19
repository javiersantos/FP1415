package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionBecaNoValida;

final public class BecaInmutableImpl implements BecaInmutable {
	
	final private String codigo;
	final private Double cuantiaTotal;
	final private Integer duracion;
	final private TipoBeca tipo;
	final private static Double cuantiaTotalDefault = 1500.;
	final private static Integer duracionDefault = 1;
	
	public BecaInmutableImpl(String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo){
		checkCodigo(codigo);
		checkCuantiaMinima(cuantiaTotal);
		checkDuracionMinima(duracion);
		
		this.codigo = codigo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
		this.tipo = tipo;

	}
	
	public BecaInmutableImpl(String codigo, TipoBeca tipo){
		this.codigo = codigo;
		this.tipo = tipo;
		this.cuantiaTotal = cuantiaTotalDefault;
		this.duracion = duracionDefault;
		
		checkCodigo(codigo);
	}
	
	private void checkCodigo(String code){
		if(!((code.length()==7) && Character.isAlphabetic(code.charAt(0)) && Character.isAlphabetic(code.charAt(1)) && Character.isAlphabetic(code.charAt(2)) && Character.isDigit(code.charAt(3)) && Character.isDigit(code.charAt(4)) && Character.isDigit(code.charAt(5)) && Character.isDigit(code.charAt(6)))){
			throw new ExcepcionBecaNoValida("El código no está formado por 3 letras y 3 dígitos");
		}
	}
	
	private void checkDuracionMinima(Integer time){
		if(time<duracionDefault){
			throw new ExcepcionBecaNoValida("La duración mínima es de 1 mes.");
		}
	}
	
	private void checkCuantiaMinima(Double price){
		if(price<cuantiaTotalDefault){
			throw new ExcepcionBecaNoValida("La cuantía mínima es de " + cuantiaTotalDefault);
		}
	}
	
	@Override
	public String getCodigo() {
		return codigo;
	}

	@Override
	public Double getCuantiaTotal() {
		return cuantiaTotal;
	}

	@Override
	public Integer getDuracion() {
		return duracion;
	}

	@Override
	public TipoBeca getTipo() {
		return tipo;
	}

	@Override
	public Double getCuantiaMensual() {
		return getCuantiaTotal()/getDuracion();
	}
	
	@Override
	public String toString() {
		return "[" + getCodigo() + ", " + getTipo().toString() + "]";
	}
	
	// CRITERIO DE IGUALDAD //
	public boolean equals(Object o) {
		boolean res = false;
		if(o instanceof Beca){
			Beca b = (Beca)o;
			res = getCodigo().equals(b.getCodigo()) && getTipo().equals(b.getTipo());
		}
		return res;
	}
	
	// CRITERIO DE ORDEN NATURAL //
	public int compareTo(BecaInmutable b) {
		int res = getCodigo().compareTo(b.getCodigo());
		if(res==0){
			res = getTipo().compareTo(b.getTipo());
		}
		return res;
	}
	
	public int hashCode() {
		return getCodigo().hashCode() + getTipo().hashCode()*31;
	}

}
