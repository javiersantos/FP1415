package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface Centro extends Comparable<Centro> {
	
	String getNombre();
	String getDireccion();
	Integer getNumeroPlantas();
	Integer getNumeroSotanos();
	Set<Espacio> getEspacios();
	SortedMap<Profesor, Despacho> getDespachosPorProfesor();
	
	void nuevoEspacio(Espacio e);
	void eliminaEspacio(Espacio e);
	
	Integer[] getConteosEspacios();
	Set<Despacho> getDespachos();
	Set<Despacho> getDespachos(Departamento d);
	Set<Profesor> getProfesores();
	Set<Profesor> getProfesores(Departamento d);
	Espacio getEspacioMayorCapacidad();
	
	SortedSet<Espacio> getEspaciosOrdenadosPorCapacidad();
}
