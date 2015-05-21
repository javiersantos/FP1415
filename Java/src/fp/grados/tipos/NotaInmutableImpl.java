package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionNotaNoValida;

final public class NotaInmutableImpl implements Nota {
	
	final private Asignatura asignatura;
	final private Integer cursoAcademico;
	final private Convocatoria convocatoria;
	final private Double valor;
	final private Boolean mencionDeHonor;
	
	public NotaInmutableImpl(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor, Boolean mencionDeHonor) {
		checkNota(valor);
		checkMencionHonor(mencionDeHonor, valor);
		
		this.asignatura = new AsignaturaImpl(asignatura.getNombre(), asignatura.getCodigo(), asignatura.getCreditos(), asignatura.getTipo(), asignatura.getCurso(), asignatura.getDepartamento());
		this.cursoAcademico = cursoAcademico;
		this.convocatoria = convocatoria;
		this.valor = valor;
		this.mencionDeHonor = mencionDeHonor;
		
	}
	
	public NotaInmutableImpl(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor) {
		checkNota(valor);
		
		this.asignatura = new AsignaturaImpl(asignatura.getNombre(), asignatura.getCodigo(), asignatura.getCreditos(), asignatura.getTipo(), asignatura.getCurso(), asignatura.getDepartamento());
		this.cursoAcademico = cursoAcademico;
		this.convocatoria = convocatoria;
		this.valor = valor;
		this.mencionDeHonor = false;
		
		checkNota(valor);
		checkMencionHonor(mencionDeHonor, valor);
	}
	
	public NotaInmutableImpl(String s) {
		String[] split = s.split(";");
		if(split.length != 5) {
			throw new IllegalArgumentException("No cumple la condicion de constructor.");
		} else {
			String[] splitAsignatura = (split[0].trim()).split("#");
			if (splitAsignatura.length != 5) {
				throw new ExcepcionNotaNoValida("No cumple la condición del constructor.");
			} else{
				this.asignatura = new AsignaturaImpl(splitAsignatura[0].trim(), splitAsignatura[1].trim(), new Double(splitAsignatura[2].trim()), TipoAsignatura.valueOf(splitAsignatura[3].trim()), new Integer(splitAsignatura[4].trim()), null);
			}
			this.cursoAcademico = new Integer(split[1].trim());
			this.convocatoria = Convocatoria.valueOf(split[2].trim());
			this.valor = new Double(split[3].trim());
			checkNota(this.valor);
			this.mencionDeHonor = Boolean.valueOf(split[4].trim());
			checkMencionHonor(this.mencionDeHonor, this.valor);
		}
	}
	
	private void checkNota(Double score){
		if(!(score>=0 && score<=10)){
			throw new ExcepcionNotaNoValida("La calificacion debe estar comprendida entre 0 y 10.");
		}
	}
	
	private void checkMencionHonor(Boolean mencion, Double score){
		if(mencion){
			if(score<9){
				throw new ExcepcionNotaNoValida("Solo puede asignarse mencion de honor para notas mayor de 9.");
			}
		}
	}

	@Override
	public Asignatura getAsignatura() {
		return new AsignaturaImpl(asignatura.getNombre(), asignatura.getCodigo(), asignatura.getCreditos(), asignatura.getTipo(), asignatura.getCurso(), asignatura.getDepartamento());
	}

	@Override
	public Integer getCursoAcademico() {
		return cursoAcademico;
	}

	@Override
	public Double getValor() {
		return valor;
	}

	@Override
	public Convocatoria getConvocatoria() {
		return convocatoria;
	}

	@Override
	public Boolean getMencionHonor() {
		return mencionDeHonor;
	}

	@Override
	public Calificacion getCalificacion() {
		Calificacion res = null;
		if(getValor()<5){
			res = Calificacion.SUSPENSO;
		} if(getValor()>=5 && getValor()<7){
			res = Calificacion.APROBADO;
		} if(getValor()>=7 && getValor()<9){
			res = Calificacion.NOTABLE;
		} if(getValor()>=9 && !getMencionHonor()){
			res = Calificacion.SOBRESALIENTE;
		} if(getValor()>=9 && getMencionHonor()){
			res = Calificacion.MATRICULA_DE_HONOR;
		}
		return res;
	}
	
	@Override
	public String toString() {
		String cursoAcad = Integer.toString((getCursoAcademico()+1));
		
		return getAsignatura() + ", " + getCursoAcademico() + "-" + cursoAcad.substring(cursoAcad.length()-2) + ", " + getConvocatoria() + ", " + getValor() + ", " + getCalificacion();
	}
	
	// CRITERIO DE IGUALDAD //
	public boolean equals(Object o) {
		boolean res = false;
		if(o instanceof Nota){
			Nota n = (Nota)o;
			res = getCursoAcademico().equals(n.getCursoAcademico()) && getAsignatura().equals(n.getAsignatura()) && getConvocatoria().equals(n.getConvocatoria());
		}
		return res;
	}
	
	// CRITERIO DE ORDEN NATURAL //
	public int compareTo(Nota n) {
		int res = getCursoAcademico().compareTo(n.getCursoAcademico());
		if(res==0){
			res = getAsignatura().compareTo(n.getAsignatura());
			if(res==0){
				res = getConvocatoria().compareTo(n.getConvocatoria());
			}
		}
		return res;
	}
		
	public int hashCode() {
		return getCursoAcademico().hashCode() + getAsignatura().hashCode()*31 + getConvocatoria().hashCode()*31*31;
	}

}
