package fp.grados.tipos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionExpedienteOperacionNoPermitida;

public class ExpedienteImpl implements Expediente {
	
	private List<Nota> notas;
	
	public ExpedienteImpl() {
		this.notas = new ArrayList<Nota>();
		
	}
	
	private void checkConvocatorias(Nota n) {
		Integer cont = 0;
		for(Nota a : getNotas()) {
			if(a.getAsignatura().equals(n.getAsignatura()) && a.getCursoAcademico().equals(n.getCursoAcademico())) {
				cont++;
			}
		}
		if(cont>=2) {
			throw new ExcepcionExpedienteOperacionNoPermitida("Un expediente no puede contener notas para más de dos convocatorias de una misma asignatura y curso");
		}
	}

	@Override
	public List<Nota> getNotas() {
		return new ArrayList<Nota>(notas);
	}

	@Override
	public Double getNotaMedia() {
		Double sum = 0.;
		Integer cont = 0;
		Double res = 0.0;
		for(Nota a : getNotas()) {
			if(a.getValor()>=5) {
				sum = sum + a.getValor();
				cont++;
			}
		}
		if(!(cont == 0)) {
			res = sum/cont;
		}
		return res;
			
	}

	@Override
	public void nuevaNota(Nota n) {
		checkConvocatorias(n);
		if(getNotas().isEmpty()) {
			this.notas.add(n);
		} else {
			for(Nota n1 : getNotas()) {
				if(n1.getAsignatura().equals(n.getAsignatura()) && n1.getConvocatoria().equals(n.getConvocatoria()) && n1.getCursoAcademico().equals(n.getCursoAcademico())) {
					checkConvocatorias(n1);
					this.notas.remove(n1);
				}
			}
			this.notas.add(n);	
		}
	}
	
	@Override
	public List<Nota> getNotasOrdenadasPorAsignatura() {
		Comparator<Nota> comp = Comparator.comparing(Nota::getAsignatura);
		SortedSet<Nota> res = new TreeSet<Nota>(comp.thenComparing(Comparator.naturalOrder()));
		res.addAll(getNotas());
		
		return new ArrayList<Nota>(res);
	}

	@Override
	public Nota getMejorNota() {
		Nota res;
		Comparator<Nota> comp = Comparator.comparing(Nota::getMencionHonor).reversed().thenComparing(Nota::getValor).reversed().thenComparing(Nota::getConvocatoria).thenComparing(Nota::getCursoAcademico);
		SortedSet<Nota> notas = new TreeSet<Nota>(comp);
		if(notas.isEmpty()) {
			throw new NoSuchElementException("No existe ninguna nota.");
		} else {
			res = notas.first();
		}
		
		return res;
	}
	
	// CRITERIO DE IGUALDAD //
	@Override
	public boolean equals(Object o) {
		boolean res = false;
		if(o instanceof Expediente){
			Expediente e = (Expediente)o;
			res = getNotas().equals(e.getNotas());
		}
		return res;
	}
	
	@Override
	public int hashCode() {
		return getNotas().hashCode();
	}

	@Override
	public String toString() {
		return getNotas().toString();
	}

}
