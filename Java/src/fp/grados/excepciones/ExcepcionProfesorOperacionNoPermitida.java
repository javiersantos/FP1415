package fp.grados.excepciones;

public class ExcepcionProfesorOperacionNoPermitida extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ExcepcionProfesorOperacionNoPermitida(){
		super();
	}
	
	public ExcepcionProfesorOperacionNoPermitida(String s){
		super(s);
	}

}
