package fp.grados.utiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.AlumnoImpl2;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Beca;
import fp.grados.tipos.BecaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.CentroImpl2;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.DepartamentoImpl2;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.Grado;
import fp.grados.tipos.GradoImpl;
import fp.grados.tipos.GradoImpl2;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.ProfesorImpl2;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.TipoBeca;
import fp.grados.tipos.TipoEspacio;
import fp.grados.tipos.Tutoria;

public class Grados {
	
	private static Set<Departamento> departamentos = new HashSet<Departamento>();
	private static Set<Beca> becas = new HashSet<Beca>();
	private static Integer[] numBecasPorTipo = {0,0,0};
	private static Map<String, Asignatura> asigCod = new HashMap<String, Asignatura>();
	private static Boolean usarMapProf = false;
	private static Boolean usarJava8 = true;
	private static Set<Profesor> profesores = new HashSet<Profesor>();
	private static Set<Alumno> alumnos = new HashSet<Alumno>();
	private static Set<Centro> centros = new HashSet<Centro>();
	private static Set<Espacio> espacios = new HashSet<Espacio>();
	private static Set<Grado> grados = new HashSet<Grado>();
	
	public static <T> List<T> leeFichero(String nombreFichero, Function<String,T> funcion_deString_aT) {
		List<T> res = null;
		try {
			res = Files.lines(Paths.get(nombreFichero))
					 .map(funcion_deString_aT)
					 .collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error en lectura del fichero: "+nombreFichero);
		}
		
		return res;
	}
	
	// USAR JAVA8 //
	
	public static void setUsarJava8(Boolean java8) {
		usarJava8 = java8;
	}
	
	// DEPARTAMENTO
	
	public static Departamento createDepartamento(String nombre) {
		Departamento d = null;
		if(usarJava8) {
			d = new DepartamentoImpl2(nombre);
		} else {
			d = new DepartamentoImpl(nombre);
		}
		departamentos.add(d);
		
		return d;
	}
	
	public static Set<Departamento> getDepartamentosCreados() {
		return departamentos;
	}
	
	public static Integer getNumDepartamentosCreados() {
		return getDepartamentosCreados().size();
	}
	
	// BECA
	
	public static Beca createBeca(String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo) {
		Beca b = new BecaImpl(codigo, cuantiaTotal, duracion, tipo);
		becas.add(b);
		actualizaNumBecasPorTipo(b);
		return b;
	}
	
	public static Beca createBeca(String codigo, TipoBeca tipo) {
		Beca b = new BecaImpl(codigo, tipo);
		becas.add(b);
		actualizaNumBecasPorTipo(b);
		return b;
	}
	
	public static Beca createBeca(Beca beca) {
		return new BecaImpl(beca.getCodigo(), beca.getCuantiaTotal(), beca.getDuracion(), beca.getTipo());
	}
	
	public static Beca createBeca(String s) {
		Beca b = new BecaImpl(s);
		becas.add(b);
		actualizaNumBecasPorTipo(b);
		return b;
	}
	
	public static List<Beca> createBecas(String nombreFichero) {
		return leeFichero(nombreFichero, s->createBeca(s));
	}
	
	public static Set<Beca> getBecasCreadas() {
		return becas;
	}
	
	public static Integer getNumBecasCreadas() {
		return getBecasCreadas().size();
	}
	
	private static void actualizaNumBecasPorTipo(Beca b) {
		switch (b.getTipo()) { 
		case ORDINARIA: 
			numBecasPorTipo[0]++;
			break;
		case MOVILIDAD: 
			numBecasPorTipo[1]++;
			break;
		case EMPRESA: 
			numBecasPorTipo[2]++;
			break;
		}
	}
	
	public static Integer getNumBecasTipo(TipoBeca tipo) {
		Integer res = 0;
		switch (tipo) {
		case ORDINARIA:
			res = numBecasPorTipo[0];
			break;
		case MOVILIDAD:
			res = numBecasPorTipo[1];
			break;
		case EMPRESA:
			res = numBecasPorTipo[2];
			break;
		}
		return res;
	}
	
	// ASIGNATURA
	
	public static Asignatura createAsignatura(String nombre, String codigo, Double creditos, TipoAsignatura tipo, Integer curso, Departamento departamento) {
		Asignatura a = new AsignaturaImpl(nombre, codigo, creditos, tipo, curso, departamento);
		asigCod.put(codigo, a);
		return a;
	}
	
	public static Asignatura createAsignatura(String s) {
		Asignatura a = new AsignaturaImpl(s);
		asigCod.put(a.getCodigo(), a);
		return a;
	}
	
	public static List<Asignatura> createAsignaturas(String nombreFichero) {
		return leeFichero(nombreFichero, s->createAsignatura(s));
	}
	
	public static Set<Asignatura> getAsignaturasCreadas() {
		return new HashSet<Asignatura>(asigCod.values());
	}
	
	public static Integer getNumAsignaturasCreadas() {
		return getAsignaturasCreadas().size();
	}
	
	public static Asignatura getAsignaturaCreada(String codigo) {
		return asigCod.get(codigo);
	}
	
	public static Set<String> getCodigosAsignaturasCreadas() {
		return new HashSet<String>(asigCod.keySet());
	}
	
	// PROFESOR
	
	public static Profesor createProfesor(String DNI, String nombre, String apellidos, LocalDate fecha, String email, Categoria categoria, Departamento departamento) {
		Profesor p = null;
		if(usarMapProf) {
			p = new ProfesorImpl2(DNI, nombre, apellidos, fecha, email, categoria, departamento);
		} else {
			p = new ProfesorImpl(DNI, nombre, apellidos, fecha, email, categoria, departamento);
		}
		profesores.add(p);
		return p;
	}
	
	public static Profesor createProfesor(Profesor profesor) {
		Profesor p = createProfesor(profesor.getDNI(), profesor.getNombre(), profesor.getApellidos(), profesor.getFechaNacimiento(), profesor.getEmail(), profesor.getCategoria(), profesor.getDepartamento());
		copiaAsignaturasYTutorias(p, profesor);
		return p;
	}
	
	private static void copiaAsignaturasYTutorias(Profesor profesorNuevo, Profesor profesorAntiguo) {
		for(Asignatura a : profesorAntiguo.getAsignaturas()) {
			profesorNuevo.imparteAsignatura(a, profesorAntiguo.dedicacionAsignatura(a));
		}
		for(Tutoria t : profesorAntiguo.getTutorias()) {
			profesorNuevo.nuevaTutoria(t.getHoraComienzo(), t.getDuracion(), t.getDiaSemana());
		}
		
	}
	
	public static Integer getNumProfesoresCreados() {
		return getProfesoresCreados().size();
	}
	
	public static Set<Profesor> getProfesoresCreados() {
		return profesores;
	}
	
	public static void setUsarImplementacionMapProfesor(Boolean usarMap) {
		usarMapProf = usarMap;
	}
	
	// ALUMNO
	
	public static Alumno createAlumno(String DNI, String nombre, String apellidos, LocalDate fecha, String email) {
		Alumno a = null;
		if(usarJava8) {
			a = new AlumnoImpl2(DNI, nombre, apellidos, fecha, email);
		} else {
			a = new AlumnoImpl(DNI, nombre, apellidos, fecha, email);
		}
		alumnos.add(a);
		return a;
	}
	
	public static Alumno createAlumno(String s) {
		Alumno a = null;
		if(usarJava8) {
			a = new AlumnoImpl2(s);
		} else {
			a = new AlumnoImpl(s);
		}
		alumnos.add(a);
		return a;
	}
	
	public static Alumno createAlumno(Alumno alumno) {
		return new AlumnoImpl(alumno.getDNI(), alumno.getNombre(), alumno.getApellidos(), alumno.getFechaNacimiento(), alumno.getEmail());
	}
	
	public static Integer getNumAlumnosCreados() {
		return alumnos.size();
	}
	
	public static Set<Alumno> getAlumnosCreados() {
		return alumnos;
	}
	
	// CENTRO
	
	public static Centro createCentro(String nombre, String direccion, Integer plantas, Integer sotanos) {
		Centro c = null;
		if(usarJava8) {
			c = new CentroImpl2(nombre, direccion, plantas, sotanos);
		} else {
			c = new CentroImpl(nombre, direccion, plantas, sotanos);
		}
		centros.add(c);
		return c;
	}
	
	public static Centro createCentro(Centro centro) {
		return new CentroImpl(centro.getNombre(), centro.getDireccion(), centro.getNumeroPlantas(), centro.getNumeroSotanos());
	}
	
	public static Integer getNumCentrosCreados() {
		return centros.size();
	}
	
	public static Set<Centro> getCentrosCreados() {
		return centros;
	}
	
	public static Integer getMaxPlantas() {
		Integer res = null;
		for(Centro c : getCentrosCreados()) {
			if(res == null) {
				res = c.getNumeroPlantas();
			} else {
				if(c.getNumeroPlantas() > res) {
					res = c.getNumeroPlantas();
				}
			}
		}
		if(getCentrosCreados().isEmpty()) {
			res = null;
		}
		
		return res;
	}
	
	public static Integer getMaxSotanos() {
		Integer res = null;
		for(Centro c : getCentrosCreados()) {
			if(res == null) {
				res = c.getNumeroSotanos();
			} else {
				if(c.getNumeroSotanos() > res) {
					res = c.getNumeroSotanos();
				}
			}
		}
		if(getCentrosCreados().isEmpty()) {
			res = null;
		}
		
		return res;
	}
	
	public static Double getMediaPlantas() {
		Double res = 0.;
		for(Centro c : getCentrosCreados()) {
			res += c.getNumeroPlantas();
		}
		
		if(!(getCentrosCreados().isEmpty())) {
			res = res/getNumCentrosCreados();
		}
		return res;
	}
	
	public static Double getMediaSotanos() {
		Double res = 0.;
		for(Centro c : getCentrosCreados()) {
			res += c.getNumeroSotanos();
		}
		
		if(!(getCentrosCreados().isEmpty())) {
			res = res/getNumCentrosCreados();;
		}
		return res;
	}
	
	// DESPACHO
	
	public static Despacho createDespacho(String nombre, Integer capacidad, Integer planta) {
		Despacho d = new DespachoImpl(nombre, capacidad, planta);
		return d;
	}
	
	public static Despacho createDespacho(Despacho despacho) {
		return new DespachoImpl(despacho.getNombre(), despacho.getCapacidad(), despacho.getPlanta());
	}
	
	public static Despacho createDespacho(String s) {
		Despacho d = new DespachoImpl(s);
		return d;
	}
	
	public static List<Despacho> createDespachos(String nombreFichero) {
		return leeFichero(nombreFichero, s->createDespacho(s));
	}
	
	// ESPACIO
	
	public static Espacio createEspacio(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {
		Espacio e = new EspacioImpl(tipo, nombre, capacidad, planta);
		espacios.add(e);
		return e;
	}
	
	public static Espacio createEspacio(Espacio espacio) {
		return new EspacioImpl(espacio.getTipo(), espacio.getNombre(), espacio.getCapacidad(), espacio.getPlanta());
	}
	
	public static Espacio createEspacio(String s) {
		Espacio e = new EspacioImpl(s);
		espacios.add(e);
		return e;
	}
	
	public static Integer getNumEspaciosCreados() {
		return espacios.size();
	}
	
	public static Set<Espacio> getEspaciosCreados() {
		return espacios;
	}
	
	public static Integer getPlantaMayorEspacio() {
		Integer res = null;
		for(Espacio e : getEspaciosCreados()) {
			if(res == null) {
				res = e.getPlanta();
			} else {
				if(e.getPlanta() > res) {
					res = e.getPlanta();
				}
			}
		}
		
		if(getEspaciosCreados().isEmpty()) {
			res = null;
		}
		
		return res;
	}
	
	public static Integer getPlantaMenorEspacio() {
		Integer res = null;
		for(Espacio e : getEspaciosCreados()) {
			if(res == null) {
				res = e.getPlanta();
			} else {
				if(e.getPlanta() < res) {
					res = e.getPlanta();
				}
			}
		}
		
		if(getEspaciosCreados().isEmpty()) {
			res = null;
		}
		
		return res;
	}
	
	// GRADO
	
	public static Grado createGrado(String nombre, Set<Asignatura> asignaturasObligatorias, Set<Asignatura> asignaturasOptativas, Double creditosMinimosOptativas) {
		Grado g = null;
		if(usarJava8) {
			g = new GradoImpl2(nombre, asignaturasObligatorias, asignaturasOptativas, creditosMinimosOptativas);
		} else {
			g = new GradoImpl(nombre, asignaturasObligatorias, asignaturasOptativas, creditosMinimosOptativas);
		}
		grados.add(g);
		return g;
	}
	
	public static Integer getNumGradosCreados() {
		return grados.size();
	}
	
	public static Set<Grado> getGradosCreados() {
		return grados;
	}
	
	public static Double getMediaAsignaturasGrados() {
		Double res = 0.;
		for(Grado g : getGradosCreados()) {
			res += g.getAsignaturasObligatorias().size();
			res += g.getAsignaturasOptativas().size();
		}
		
		if(!(getGradosCreados().isEmpty())) {
			res = res/getNumGradosCreados();
		}
		return res;
	}
	
	public static Double getMediaAsignaturasObligatoriasGrados() {
		Double res = 0.;
		for(Grado g : getGradosCreados()) {
			res += g.getAsignaturasObligatorias().size();
		}
		
		if(!(getGradosCreados().isEmpty())) {
			res = res/getNumGradosCreados();
		}
		return res;
	}
	
	public static Double getMediaAsignaturasOptativasGrados() {
		Double res = 0.;
		for(Grado g : getGradosCreados()) {
			res += g.getAsignaturasOptativas().size();
		}
		
		if(!(getGradosCreados().isEmpty())) {
			res = res/getNumGradosCreados();
		}
		return res;
	}

}