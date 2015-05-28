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
	}
	return res;
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
		if (isupper(a.nombre[i])) {
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
	fflush(stdin);
	printf("TipoAsignatura: ");
	fflush(stdout);
	gets(tipo);
	printf("Curso: ");
	fflush(stdout);
	scanf("%d", &curso);
	fflush(stdin);
	printf("Departamento: ");
	fflush(stdout);
	gets(departamento);
	ok = deCadenaATipoAsignatura(tipo, &tipo);
	if (ok == 0) {
		ok = inicializaAsignatura(res, nombre, codigo, creditos, tipo, curso, departamento);
	} else {
		printf("Error en el tipo de asignatura.");
	}
	return ok;
}

void muestraAsignatura(Asignatura a) {
	Cadena acronimo, tipo;

	printf("\tNombre: %s\n", a.nombre);
	getAcronimo(a, acronimo);
	printf("\tAcronimo: %s\n", acronimo);
	printf("\tCodigo: %s\n", a.codigo);
	printf("\tCreditos: %5.1lf\n", a.creditos);
	deTipoAsignaturaACadena(tipo, a.tipo);
	printf("\tTipo: %s\n", tipo);
	printf("\tCurso: %d\n", a.curso);
	printf("\tDepartamento: %s\n", a.departamento);
}

int	leeAsignaturasTeclado(ArrayAsignaturas res) {
	int nAsig = 0;
	int i = 0;

	printf("Introduce el numero de asignaturas a leer: ");
	fflush(stdout);
	scanf("%d", &nAsig);
	fflush(stdin);
	while (nAsig <= 0 || nAsig > NUM_MAX_ASIGNATURAS) {
		printf("El numero de asignaturas debe estar entre 1 y %d\n", NUM_MAX_ASIGNATURAS);
		printf("Introduce el numero de resultados: ");
		fflush(stdout);
		scanf("%d", &nAsig);
		fflush(stdin);
	}
	while (i < nAsig) {
		printf("Asignatura[%d]:\n", i+1);
		fflush(stdout);
		if (leeAsignaturaTeclado(&res[i])) {
			i++;
		}
	}
	return nAsig;
}

void muestraAsignaturas(const ArrayAsignaturas res,	int	nAsig) {
	int i =0;
	for (i=0; i < nAsig; i++) {
		printf("Asignatura[%d]\n", i+1);
		muestraAsignatura(res[i]);
	}
}

int leeAsignaturasFichero(const Cadena nombreFichero, ArrayAsignaturas res) {
	int nAsig = 0;
	int i = 0;

	FILE* pf = NULL;
	pf = fopen(nombreFichero, "r");
	if (pf == NULL) {
		printf("Error en la apertura del fichero %s", nombreFichero);
		nAsig = 0;
	} else {
		leeAsignaturasFichero(&res[0], pf);
		i = 1;
		while (!feof(pf) != NULL && i < NUM_MAX_ASIGNATURAS) {
			leeAsignaturasFichero(&res[i], pf);
			i++;
		}
		nAsig = i;
	}
	fclose(pf);
	return nAsig;
}

void leeAsignaturaFichero(PAsignatura pa, FILE* pf) {
	Cadena aux;
	char noSirvePaNa;

	fgets(pa->nombre, NUM_MAX_CARACTERES, pf);
	quitaSaltoDeLinea(pa->nombre);
	fgets(pa->codigo, NUM_MAX_CARACTERES, pf);
	quitaSaltoDeLinea(pa->codigo);
	fscanf(pf, "%lf%c", &pa->creditos, &noSirvePaNa);
	fgets(aux, NUM_MAX_CARACTERES, pf);
	quitaSaltoDeLinea(aux);
	deCadenaATipoAsignatura(aux, &pa->tipo);
	fscanf(pf, "%d%c", &pa->curso, &noSirvePaNa);
	fgets(pa->departamento, NUM_MAX_CARACTERES, pf);
	quitaSaltoDeLinea(pa->departamento);
}
