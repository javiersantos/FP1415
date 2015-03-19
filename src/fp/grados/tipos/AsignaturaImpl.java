package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;


public class AsignaturaImpl implements Asignatura {
	
	private String nombre;
	private String codigo;
	private Double creditos;
	private TipoAsignatura tipo;
	private Integer curso;
	private Departamento departamento;
	
	public AsignaturaImpl(String nombre, String codigo, Double creditos, TipoAsignatura tipo, Integer curso, Departamento departamento) {
		checkCodigo(codigo);
		checkCreditos(creditos);
		checkCurso(curso);
		
		this.nombre = nombre;
		this.codigo = codigo;
		this.creditos = creditos;
		this.tipo = tipo;
		this.curso = curso;
		this.departamento = departamento;
		if(departamento != null) {
			departamento.nuevaAsignatura(this);
		}
	}
	
	public AsignaturaImpl(String s) {
		String[] split = s.split("#");
		if(split.length != 5) {
			throw new IllegalArgumentException("No cumple la condición del constructor.");
		} else {
			this.nombre = split[0].trim();
			this.codigo = split[1].trim();
			checkCodigo(this.codigo);
			this.creditos = new Double(split[2].trim());
			checkCreditos(this.creditos);
			this.tipo = TipoAsignatura.valueOf(split[3].trim());
			this.curso = new Integer(split[4].trim());
			checkCurso(this.curso);
			this.departamento = null;
		}
		
	}
	
	private void checkCodigo(String code){
		if(!((code.length() == 7) && Character.isDigit(code.charAt(0)) && Character.isDigit(code.charAt(1)) && Character.isDigit(code.charAt(2)) && Character.isDigit(code.charAt(3)) && Character.isDigit(code.charAt(4)) && Character.isDigit(code.charAt(5)) && Character.isDigit(code.charAt(6)))){
			throw new ExcepcionAsignaturaNoValida("El código necesita tener 7 dígitos.");
		}
	}
	
	private void checkCreditos(Double credits){
		if(!(credits>0)){
			throw new ExcepcionAsignaturaNoValida("La asignatura tiene que tener más de 0 créditos.");
		}
	}
	
	private void checkCurso(Integer curse){
		if(curse<1 || curse>4){
			throw new ExcepcionAsignaturaNoValida("Solo existen grados de 4 años, debe de estar comprendido entre 1 y 4 años.");
		}
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getAcronimo() {
		String acronimo = "";
//		String acronimoAux = "";
//		
//		String[] split = nombre.split(" ");
//		for(String str : split){
//			String firstChar = str.valueOf(0);
//			if(Character.isUpperCase(firstChar.charAt(0))){
//				acronimoAux = firstChar;
//				acronimo = acronimo + firstChar;
//			}
//		}
	
		String[] split = getNombre().split(" ");
		for(int i=0; i<=split.length-1; i++){
			String newName = split[i].trim();
			if(Character.isUpperCase(newName.charAt(0))){
				char aux = newName.charAt(0);
				acronimo = acronimo + aux + "";
			}
		}
		
		return acronimo;
	}

	@Override
	public String getCodigo() {
		return codigo;
	}

	@Override
	public Double getCreditos() {
		return creditos;
	}

	@Override
	public TipoAsignatura getTipo() {
		return tipo;
	}

	@Override
	public Integer getCurso() {
		return curso;
	}

	@Override
	public String toString() {
		return "(" + getCodigo() + ")" + " " + getNombre();
	}

	@Override
	public Departamento getDepartamento() {
		return departamento;
	}

	@Override
	public void setDepartamento(Departamento d) {
		if(this.getDepartamento() != d) {
			if(this.getDepartamento() != null) {
				this.getDepartamento().eliminaAsignatura(this);
			}
			this.departamento = d;
			if(d != null) {
				d.nuevaAsignatura(this);
			}
		}
	}
	
	// CRITERIO DE IGUALDAD //
	public boolean equals(Object o) {
		boolean res = false;
		if(o instanceof Asignatura){
			Asignatura asig = (Asignatura)o;
			res = getCodigo().equals(asig.getCodigo());
		}
		return res;
	}
	
	// CRITERIO DE ORDEN NATURAL //
	public int compareTo(Asignatura asig) {
		return getCodigo().compareTo(asig.getCodigo());
	}
	
	public int hashCode() {
		return getCodigo().hashCode();
	}
	
}
