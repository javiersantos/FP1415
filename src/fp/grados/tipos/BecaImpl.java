package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionBecaNoValida;

public class BecaImpl implements Beca {
	
	private String codigo;
	private Double cuantiaTotal;
	private Integer duracion;
	private TipoBeca tipo;
	private static final Double cuantiaTotalDefault = 1500.;
	private static final Integer duracionDefault = 1;
	
	public BecaImpl(String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo){
		checkCodigo(codigo);
		checkCuantiaMinima(cuantiaTotal);
		checkDuracionMinima(duracion);
		
		this.codigo = codigo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
		this.tipo = tipo;

	}
	
	public BecaImpl(String codigo, TipoBeca tipo){
		this.codigo = codigo;
		this.tipo = tipo;
		this.cuantiaTotal = cuantiaTotalDefault;
		this.duracion = duracionDefault;
		
		checkCodigo(codigo);
	}
	
	public BecaImpl(String s) {
		String[] split = s.split(",");
		if(split.length != 4) {
			throw new IllegalArgumentException("No cumple la condición del constructor.");
		} else {
			this.codigo = split[0].trim();
			checkCodigo(this.codigo);
			this.cuantiaTotal = new Double(split[1].trim());
			checkCuantiaMinima(this.cuantiaTotal);
			this.duracion = new Integer(split[2].trim());
			checkDuracionMinima(this.duracion);
			this.tipo = TipoBeca.valueOf(split[3].trim());
		}
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
	public void setCuantiaTotal(Double ct) {
		checkCuantiaMinima(ct);
		cuantiaTotal = ct;
	}

	@Override
	public void setDuracion(Integer d) {
		checkDuracionMinima(d);
		duracion = d;
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
	public int compareTo(Beca b) {
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
