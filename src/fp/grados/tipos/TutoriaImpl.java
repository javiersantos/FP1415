package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;

public class TutoriaImpl implements Tutoria {
	
	private DayOfWeek diaSemana;
	private LocalTime horaComienzo;
	private LocalTime horaFin;
	
	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo, LocalTime horaFin) {
		checkDia(diaSemana);
		checkTiempo((int) horaComienzo.until(horaFin, ChronoUnit.MINUTES));
		
		this.diaSemana = diaSemana;
		this.horaComienzo = horaComienzo;
		this.horaFin = horaFin;
	}
	
	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo, Integer duracion) {
		checkDia(diaSemana);
		checkTiempo(duracion);
		
		this.diaSemana = diaSemana;
		this.horaComienzo = horaComienzo;
		this.horaFin = horaComienzo.plusMinutes(duracion);
	}
	
	public TutoriaImpl(String s) {
		String[] split = s.split(",");
		if(split.length != 3) {
			throw new ExcepcionTutoriaNoValida("No cumple la condici�n de constructor.");
		} else {
			switch (split[0].trim()) {
			case "L":
				this.diaSemana = DayOfWeek.MONDAY;
				break;
			case "M":
				this.diaSemana = DayOfWeek.TUESDAY;
				break;
			case "X":
				this.diaSemana = DayOfWeek.WEDNESDAY;
				break;
			case "J":
				this.diaSemana = DayOfWeek.THURSDAY;
				break;
			case "V":
				this.diaSemana = DayOfWeek.FRIDAY;
				break;
			case "S":
				this.diaSemana = DayOfWeek.SATURDAY;
				break;
			case "D":
				this.diaSemana = DayOfWeek.SUNDAY;
				break;
			default:
				break;
			}
			checkDia(this.diaSemana);
			this.horaComienzo = LocalTime.parse(split[1].trim());
			this.horaFin = LocalTime.parse(split[2].trim());
			checkTiempo((int) this.horaComienzo.until(this.horaFin, ChronoUnit.MINUTES));
		}
	}

	private void checkDia(DayOfWeek dia){
		if(!(dia.getValue()==1 || dia.getValue()==2 || dia.getValue()==3 || dia.getValue()==4 || dia.getValue()==5 )){
			throw new ExcepcionTutoriaNoValida("La tutoría solo puede ser de lunes a viernes.");
		}
	}
	
	private void checkTiempo(Integer minutes){
		if(minutes<15){
			throw new ExcepcionTutoriaNoValida("La tutoría debe durar al menos 15 minutos.");
		}
	}
	
	@Override
	public DayOfWeek getDiaSemana() {
		return diaSemana;
	}

	@Override
	public LocalTime getHoraComienzo() {
		return horaComienzo;
	}

	@Override
	public LocalTime getHoraFin() {
		return horaFin;
	}

	@Override
	public Integer getDuracion() {
		return (int) horaComienzo.until(horaFin, ChronoUnit.MINUTES);
	}
	
	@Override
	public String toString() {
		String res = "";
		if(!(getHoraFin() == null)) {
			res = getDiaSemana().getDisplayName(TextStyle.NARROW, new Locale("ES")) + " " + getHoraComienzo() + "-" + getHoraFin();
		} else {
			res = getDiaSemana().getDisplayName(TextStyle.NARROW, new Locale("ES")) + " " + getHoraComienzo() + "-" + getHoraComienzo()+getDuracion();
		}
		return res;
	}
	
	// CRITERIO DE IGUALDAD //
	public boolean equals(Object o) {
		boolean res = false;
		if(o instanceof Tutoria){
			Tutoria t = (Tutoria)o;
			res = getDiaSemana().equals(t.getDiaSemana()) && getHoraComienzo().equals(t.getHoraComienzo());
		}
		return res;
	}
		
	// CRITERIO DE ORDEN NATURAL //
	public int compareTo(Tutoria t) {
		int res = getDiaSemana().compareTo(t.getDiaSemana());
		if(res==0){
			res = getHoraComienzo().compareTo(t.getHoraComienzo());
		}
		return res;
	}
		
	public int hashCode() {
		return getDiaSemana().hashCode() + getHoraComienzo().hashCode()*31;
	}

}
