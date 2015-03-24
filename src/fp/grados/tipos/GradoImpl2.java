package fp.grados.tipos;

import java.util.Set;

public class GradoImpl2 extends GradoImpl implements Grado {

	public GradoImpl2(String nombre, Set<Asignatura> asignaturasObligatorias,
			Set<Asignatura> asignaturasOptativas,
			Double creditosMinimosOptativas) {
		super(nombre, asignaturasObligatorias, asignaturasOptativas,
				creditosMinimosOptativas);
	}

}
