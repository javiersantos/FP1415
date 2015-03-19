package fp.grados.excepciones;

public class ExcepcionDepartamentoNoValido extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ExcepcionDepartamentoNoValido(){
		super();
	}
	
	public ExcepcionDepartamentoNoValido(String s){
		super(s);
	}

}
