package fp.grados.tipos;

public class ExpedienteImpl2 extends ExpedienteImpl implements Expediente {

	public ExpedienteImpl2() {
		super();
	}
	
	public Double getNotaMedia() {
		return this.getNotas().stream().filter(n -> n.getValor() >= 5).mapToDouble(Nota::getValor).average().getAsDouble();
	}

}
