package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl implements Centro {
	
	private String nombre, direccion;
	private Integer plantas, sotanos;
	private Set<Espacio> espacios;
	
	public CentroImpl(String nombre, String direccion, Integer plantas, Integer sotanos) {
		checkPlantas(plantas);
		checkSotanos(sotanos);
		
		this.nombre = nombre;
		this.direccion = direccion;
		this.plantas = plantas;
		this.sotanos = sotanos;
		this.espacios = new HashSet<Espacio>();
	}
	
	private void checkPlantas(Integer p) {
		if(p<1) {
			throw new ExcepcionCentroNoValido("Un centro debe tener mínimo una planta.");
		}
	}
	
	private void checkSotanos(Integer s) {
		if(s<0) {
			throw new ExcepcionCentroNoValido("Un centro debe tener 0 o más sótanos.");
		}
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getDireccion() {
		return direccion;
	}

	@Override
	public Integer getNumeroPlantas() {
		return plantas;
	}

	@Override
	public Integer getNumeroSotanos() {
		return sotanos;
	}

	@Override
	public Set<Espacio> getEspacios() {
		return new HashSet<Espacio>(espacios);
	}

	@Override
	public void nuevoEspacio(Espacio e) {
		Integer plantaEspacio = e.getPlanta();
		Integer plantasCentro = getNumeroPlantas();
		Integer sotanosCentro = getNumeroSotanos();
		if(-sotanosCentro <= plantaEspacio) {
			if(plantaEspacio <= plantasCentro-1) {
				espacios.add(e);
			} else {
				throw new ExcepcionCentroOperacionNoPermitida("No está comprendido dentro del intervalo.");
			}
		} else {
			throw new ExcepcionCentroOperacionNoPermitida("No está comprendido dentro del intervalo.");
		}
	}
	
	@Override
	public Integer[] getConteosEspacios() {
		Integer[] res = {0,0,0,0,0};
		for(Espacio e : getEspacios()) {
			switch(e.getTipo()) {
			case TEORIA:
				res[0]++;
				break;
			case LABORATORIO:
				res[1]++;
				break;
			case SEMINARIO:
				res[2]++;
				break;
			case EXAMEN:
				res[3]++;
				break;
			case OTRO:
				res[4]++;
				break;
			}
		}
		return res;
	}

	@Override
	public Set<Despacho> getDespachos() {
		Set<Despacho> res = new HashSet<Despacho>();
		for(Espacio e : getEspacios()) {
			if(e instanceof Despacho) {
				Despacho d = (Despacho)e;
				res.add(d);
			}
		}
		return res;
	}

	@Override
	public Set<Despacho> getDespachos(Departamento d) {
		Set<Despacho> res = new HashSet<Despacho>();
		for(Despacho des : getDespachos()) {
			if(existeProfesorDepartamento(d)) {
				res.add(des);
			}
		}
		return res;
	}
	
	private Boolean existeProfesorDepartamento(Departamento d) {
		Boolean res = false;
		for(Profesor p : getProfesores()) {
			if(p.getDepartamento().equals(d)) {
				res = true;
				break;
			}
		}
		return res;
	}

	@Override
	public Set<Profesor> getProfesores() {
		Set<Profesor> res = new HashSet<Profesor>();
		for(Despacho d : getDespachos()) {
			res.addAll(d.getProfesores());
		}
		return res;
	}

	@Override
	public Set<Profesor> getProfesores(Departamento d) {
		Set<Profesor> res = new HashSet<Profesor>();
		for(Profesor p : getProfesores()) {
			if(p.getDepartamento().equals(d)) {
				res.add(p);
			}
		}
		return res;
	}

	@Override
	public Espacio getEspacioMayorCapacidad() {
		Integer espacio = 0;
		Espacio res = null;
		
		for(Espacio e : getEspacios()) {
			if(e.getCapacidad()>espacio || espacio == 0) {
				espacio = e.getCapacidad();
				res = e;
			}
		}
		if(res == null) {
			throw new ExcepcionCentroOperacionNoPermitida("No hay espacios en el centro.");
		}
		return res;
	}

	@Override
	public void eliminaEspacio(Espacio e) {
		if(espacios.contains(e)) {
			espacios.remove(e);
		}
	}

	public SortedMap<Profesor, Despacho> getDespachosPorProfesor() {
		SortedMap<Profesor, Despacho> res = new TreeMap<Profesor, Despacho>();
		for(Despacho d : getDespachos()) {
			for(Profesor p : d.getProfesores()) {
				res.put(p, d);
			}
		}
		return res;
	}
	
	@Override
	public SortedSet<Espacio> getEspaciosOrdenadosPorCapacidad() {
		
		Comparator<Espacio> comp = Comparator.comparing(Espacio::getCapacidad);
		SortedSet<Espacio> res = new TreeSet<Espacio>(comp.reversed().thenComparing(Comparator.naturalOrder()));
		res.addAll(getEspacios());
		
		return res;
	}
	
	// CRITERIO DE IGUALDAD //
	public boolean equals(Object o) {
		boolean res = false;
		if(o instanceof Centro){
			Centro c = (Centro)o;
			res = getNombre().equals(c.getNombre());
		}
		return res;
	}
	
	// CRITERIO DE ORDEN NATURAL //
	public int compareTo(Centro c) {
		return getNombre().compareTo(c.getNombre());
	}
		
	public int hashCode() {
		return getNombre().hashCode();
	}
	
	public String toString() {
		return getNombre();
	}

}
