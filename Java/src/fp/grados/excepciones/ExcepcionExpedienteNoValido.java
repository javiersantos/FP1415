package fp.grados.excepciones;

public class ExcepcionExpedienteNoValido extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ExcepcionExpedienteNoValido(){
		super();
	}
	
	public ExcepcionExpedienteNoValido(String s){
		super(s);
	}

}
