/*
 * asignatura.c
 *
 *  Created on: 11/5/2015
 *      Author: Javier
 */
#include "asignatura.h"

int inicializaAsignatura(PAsignatura res, const	Cadena nombre, const TipoCodigo	codigo, double creditos, TipoAsignatura	 tipo, int curso, const	Cadena departamento) {
	int ok = 0;
	if (!compruebaCodigo(codigo) || !compruebaCreditos(creditos) || !compruebaCurso(curso)) {
		printf("asignatura_c.inicializaAsignatura: Error en parametros");
		ok = -1;
	} else {
		strcpy(res->nombre, nombre);
		strcpy(res->codigo, codigo);
		res->creditos = creditos;
		res->tipo = tipo;
		res->curso = curso;
		strcpy(res->departamento, departamento);
	}
	return ok;
}

Logico compruebaCodigo(const TipoCodigo codigo) {
	Logico res = CIERTO;
	int i = 0;
	if (strlen(codigo) == 7) {
		for (i=0; i<7; i++) {
			if (codigo[i] < '0' || codigo[i] > '9') {
				res = FALSO;
				break;
			}
		}
	} else {
		res = FALSO;
		return res;
	}
}
Logico compruebaCreditos(double creditos) {
	Logico res = CIERTO;
	if (creditos <=0) {
		res = FALSO;
	}
	return res;
}
Logico compruebaCurso(int curso) {
	Logico res = CIERTO;
	if (curso <=0) {
		res = FALSO;
	}
	return res;
}

void getAcronimo(Asignatura	a, Cadena res) {
	int i = 0;
	int j = 0;
	for (i=0; i < strlen(a.nombre); i++) {
		if (isupper(a.nombre)) {
			res[j] = a.nombre[i];
			j++;
		}
	}
	res[j] = '\0';
}

int	leeAsignaturaTeclado(PAsignatura res) {
	int ok = 0;
	Cadena nombre;
	Cadena codigo;
	double creditos;
	TipoAsignatura tipo;
	int curso;
	Cadena departamento;

	printf("Nombre: ");
	fflush(stdout);
	gets(nombre);
	printf("Codigo: ");
	fflush(stdout);
	gets(codigo);
	printf("Creditos: ");
	fflush(stdout);
	scanf("%lf", &creditos);
	printf("TipoAsignatura: ");
	fflush(stdout);
	gets(tipo);
	printf("Curso: ");
	fflush(stdout);
	scanf("%d", &curso);
	printf("Departamento: ");
	fflush(stdout);
	gets(departamento);
	ok = deCadenaATipoAsignatura(tipo, &tipo);
	if (ok == 0) {
		ok = inicializaAsignatura(res, nombre, codigo, creditos, tipo, curso, departamento);
	}
}

void muestraAsignatura(Asignatura a) {
	Cadena acronimo, tipo;

	printf("Nombre: %s", a.nombre);
	getAcronimo(a, acronimo);
	printf("Acronimo: %s", acronimo);
	printf("Codigo: %s", a.codigo);
	printf("Creditos: %lf", a.creditos);
	deTipoAsignaturaACadena(tipo, a.tipo);
	printf("Tipo: %s", tipo);
	printf("Curso: %d", a.curso);
	printf("Departamento: %s", a.departamento);
}

int	leeAsignaturasTeclado(ArrayAsignaturas res) {
	int resultados;
	int i = 0;

	printf("Introduce el numero de resultados: ");
	fflush(stdout);
	scanf("%d", &resultados);

	if (resultados >= sizeof(res)) {
		printf("Introduce el numero de resultados: ");
		fflush(stdout);
		scanf("%d", &resultados);
	} else {
		for (i = 0; i < resultados; i++) {

		}
	}

}

void muestraAsignaturas(const ArrayAsignaturas res,	int	nAsig) {


}

int leeAsignaturasFichero(const Cadena nombreFichero, ArrayAsignaturas res) {


}

