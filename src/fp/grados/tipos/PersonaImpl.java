package fp.grados.tipos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import fp.grados.excepciones.ExcepcionPersonaNoValida;

public class PersonaImpl implements Persona {
	
	private String DNI;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private String email;
	
	public PersonaImpl(String DNI, String nombre, String apellidos, LocalDate fecha, String email){
		checkDNI(DNI);
		checkEmail(email);
		
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fecha;
		this.email = email;
	}
	
	public PersonaImpl(String DNI, String nombre, String apellidos, LocalDate fecha){
		checkDNI(DNI);
		
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fecha;
		this.email = "";
	}
	
	public PersonaImpl(String s) {
		String[] split = s.split(",");
		if(split.length != 5) {
			throw new IllegalArgumentException("No cumple la condición del constructor.");
		} else {
			this.DNI = split[0].trim();
			checkDNI(this.DNI);
			this.nombre = split[1].trim();
			this.apellidos = split[2];
			this.fechaNacimiento = LocalDate.parse(split[3].trim(), DateTimeFormatter.ofPattern("d/M/yyyy"));
			this.email = split[4].trim();
			checkEmail(this.email);
		}
		
	}
	
	private void checkDNI(String dni){
		if(!(dni.length()==9)){
			throw new ExcepcionPersonaNoValida("El DNI no es correcto.");
		}
		for(int i=0; i<8; i++){
			if(!(Character.isDigit(dni.charAt(i)))){
				throw new ExcepcionPersonaNoValida("El DNI no es correcto.");
			}
			if(!(Character.isLetter(dni.charAt(8)))){
				throw new ExcepcionPersonaNoValida("El DNI no es correcto.");
			}
		}
		
		String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";
		Integer numberDNI =  Integer.parseInt(dni.replaceAll("\\D+",""))%23;
		if(!(dni.charAt(8)==NIF_STRING_ASOCIATION.charAt(numberDNI))){
			throw new ExcepcionPersonaNoValida("La letra del DNI no es correcta.");
		}
	}
	
	private void checkEmail(String mail){
		if(!(mail.isEmpty())){
			if(!(mail.contains("@"))){
				throw new ExcepcionPersonaNoValida("El correo no contiene '@' o está vacío.");
			}
		}
	}

	@Override
	public String getDNI() {
		return DNI;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getApellidos() {
		return apellidos;
	}

	@Override
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public Integer getEdad() {

		return (int) getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
	}

	@Override
	public void setDNI(String dni) {
		checkDNI(dni);
		DNI = dni;
	}

	@Override
	public void setNombre(String n) {
		nombre = n;
	}

	@Override
	public void setApellidos(String a) {
		apellidos = a;
	}

	@Override
	public void setFechaNacimiento(LocalDate f) {
		fechaNacimiento = f;
	}

	@Override
	public void setEmail(String e) {
		checkEmail(e);
		email = e;
	}
	
	@Override
	public String toString() {
		return getDNI() + " - " + getApellidos() + ", " + getNombre() + " - " + getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	// CRITERIO DE IGUALDAD //
		public boolean equals(Object o) {
			boolean res = false;
			if(o instanceof Persona){
				Persona p = (Persona)o;
				res = getDNI().equals(p.getDNI()) && getNombre().equals(p.getNombre()) && getApellidos().equals(p.getApellidos());
			}
			return res;
		}
		
		// CRITERIO DE ORDEN NATURAL //
		public int compareTo(Persona p) {
			int res = getApellidos().compareTo(p.getApellidos());
			if(res==0){
				res = getNombre().compareTo(p.getNombre());
				if(res==0){
					res = getDNI().compareTo(p.getDNI());
				}
			}
			return res;
		}
		
		public int hashCode() {
			return getApellidos().hashCode() + getNombre().hashCode()*31 + getDNI().hashCode()*31*31;
		}

}
