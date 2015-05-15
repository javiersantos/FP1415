package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;

public class DespachoImpl extends EspacioImpl implements Despacho {
	
	private Set<Profesor> profesores = new HashSet<Profesor>();
	
	public DespachoImpl(String nombre, Integer capacidad, Integer planta, Set<Profesor> profesores){
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		
		checkProfesores(profesores);
		this.profesores = profesores;
	}
	
	public DespachoImpl(String nombre, Integer capacidad, Integer planta, Profesor profesor){
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
				
		profesores.add(profesor);		
	}
	
	public DespachoImpl(String nombre, Integer capacidad, Integer planta){
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
				
		this.profesores = new HashSet<Profesor>();
	}
	
	public DespachoImpl(String s) {
		super(TipoEspacio.OTRO, (s.split(","))[0].trim(), new Integer((s.split(","))[2].trim()), new Integer((s.split(","))[1].trim()));
		this.profesores = new HashSet<Profesor>();
	}
	
//	public DespachoImpl(String s) {
//		String[] split = s.split(",");
//		if (split.length != 3) {
//			throw new IllegalArgumentException("No cumple la condición del constructor.");
//		} else {
//			new DespachoImpl(split[0].trim(), new Integer(split[1].trim()), new Integer(split[2].trim()));
//		}
//	}
	
	private void checkProfesores(Set<Profesor> teachers) {
		if(teachers.size()>getCapacidad()){
			throw new ExcepcionDespachoNoValido("El número de profesores no puede ser mayor a la capacidad del despacho.");
		}
	}
	
	@Override
	public void setTipo(TipoEspacio t) {
		throw new UnsupportedOperationException("No puede modificarse el tipo, siempre tiene que ser 'Otro'.");
	}
	
	@Override
	public Set<Profesor> getProfesores() {
		return profesores;
	}

	@Override
	public void setProfesores(Set<Profesor> p) {
		checkProfesores(p);
		profesores = p;
	}
	
	public String toString() {
		return super.toString() + getProfesores().toString();
	}

}
