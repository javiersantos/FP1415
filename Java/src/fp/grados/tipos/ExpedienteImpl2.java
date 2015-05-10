package fp.grados.tipos;

import java.util.stream.Collectors;

public class ExpedienteImpl2 extends ExpedienteImpl implements Expediente {

	public ExpedienteImpl2() {
		super();
	}
	
	public Double getNotaMedia() {
		Double res = 0.;
		Double sumNotas = this.getNotas().stream().filter(n -> n.getValor() >= 5).mapToDouble(Nota::getValor).sum();
		Integer numNotas = this.getNotas().stream().filter(n -> n.getValor() >= 5).collect(Collectors.toSet()).size();
		if(!(sumNotas == 0)) {
			res = sumNotas/numNotas;
		}
		return res;
	}

}
