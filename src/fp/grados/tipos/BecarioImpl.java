package fp.grados.tipos;

import java.time.LocalDate;

import fp.grados.excepciones.ExcepcionBecarioNoValido;

public class BecarioImpl extends AlumnoImpl implements Becario {
	
	private Beca beca;
	private LocalDate fechaComienzo;
	
	public BecarioImpl(String DNI, String nombre, String apellidos, LocalDate fecha, String email, Beca beca, LocalDate fechaComienzo){
		super(DNI, nombre, apellidos, fecha, email);
		checkFechaComienzo(fechaComienzo);
		
		this.beca = beca;
		this.fechaComienzo = fechaComienzo;
	}
	
	public BecarioImpl(String DNI, String nombre, String apellidos, LocalDate fecha, String email, String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo, LocalDate fechaComienzo){
		super(DNI, nombre, apellidos, fecha, email);
		checkFechaComienzo(fechaComienzo);
		
		this.beca = new BecaImpl(codigo, cuantiaTotal, duracion, tipo);
		this.fechaComienzo = fechaComienzo;
	}
	
	private void checkFechaComienzo(LocalDate startDate) {
		if(!(startDate.isAfter(LocalDate.now()))){
			throw new ExcepcionBecarioNoValido("La fecha de comienzo tiene que ser posterior a la fecha actual.");
		}
	}
	
	@Override
	public void setEmail(String e) {
		throw new UnsupportedOperationException("No puede modificarse el correo electrónico.");
	}

	@Override
	public Beca getBeca() {
		return beca;
	}

	@Override
	public LocalDate getFechaComienzo() {
		return fechaComienzo;
	}

	@Override
	public LocalDate getFechaFin() {
		return fechaComienzo.plusMonths(getBeca().getDuracion());
	}

	@Override
	public void setFechaComienzo(LocalDate f) {
		checkFechaComienzo(f);
		this.fechaComienzo = f;	
	}
	
	public String toString() {
		return super.toString() + "[" + getBeca().getCodigo() + ", " + getBeca().getTipo() + "]";
//		return "(?º) " + getDNI() + " - " + getApellidos() + ", " + getNombre() + " + " + getFechaNacimiento() + "[" + getBeca().getCodigo() + ", " + getBeca().getTipo() + "]";

	}

}
