package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionEspacioNoValido;

public class EspacioImpl implements Espacio {
	
	private TipoEspacio tipo;
	private String nombre;
	private Integer capacidad;
	private Integer planta;
	
	public EspacioImpl(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta){
		checkCapacidad(capacidad);
		
		this.tipo = tipo;
		this.nombre = nombre;
		this.planta = planta;
		this.capacidad = capacidad;
		
	}
	
	public EspacioImpl(String s) {
		String[] split = s.split(",");
		if(split.length != 4) {
			throw new IllegalArgumentException("No cumple la condición del constructor.");
		} else {
			this.nombre = split[0].trim();
			this.planta = new Integer(split[1].trim());
			this.capacidad = new Integer(split[2].trim());
			checkCapacidad(this.capacidad);
			this.tipo = TipoEspacio.valueOf(split[3].trim());
		}
	}
	
	private void checkCapacidad(Integer c){
		if(!(c>0)){
			throw new ExcepcionEspacioNoValido("La capacidad debe de ser mayor a 0.");
		}
	}

	@Override
	public TipoEspacio getTipo() {
		return tipo;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public Integer getCapacidad() {
		return capacidad;
	}

	@Override
	public Integer getPlanta() {
		return planta;
	}

	@Override
	public void setTipo(TipoEspacio t) {
		tipo = t;
	}

	@Override
	public void setNombre(String n) {
		nombre = n;
	}

	@Override
	public void setCapacidad(Integer c) {
		checkCapacidad(c);
		capacidad = c;
	}
	
	@Override
	public String toString() {
		return getNombre() + " (Planta " + getPlanta() + ")";
	}
	
	// CRITERIO DE IGUALDAD //
	public boolean equals(Object o) {
		boolean res = false;
		if(o instanceof Espacio){
			Espacio e = (Espacio)o;
			res = getNombre().equals(e.getNombre()) && getPlanta().equals(e.getPlanta());
		}
		return res;
	}
		
	// CRITERIO DE ORDEN NATURAL //
	public int compareTo(Espacio e) {
		int res = getPlanta().compareTo(e.getPlanta());
		if(res==0){
			res = getNombre().compareTo(e.getNombre());
		}
		return res;
	}
		
	public int hashCode() {
		return getPlanta().hashCode() + getNombre().hashCode()*31;
	}
}
