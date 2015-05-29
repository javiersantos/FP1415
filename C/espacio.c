/*
 * espacio.c
 *
 *  Created on: May 22, 2015
 *      Author: javiersantos
 */

#include "espacio.h"

int inicializaEspacio(PEspacio res, const Cadena nombre, int planta, TipoEspacio tipo, int capacidad) {
	int ok = 0;
	if (!compruebaCapacidad(capacidad)) {
		printf("espacio_c.inicializaGrado: Error en parametros");
		ok = -1;
	} else {
		strcpy(res->nombre, nombre);
		res->planta = planta;
		res->tipo = tipo;
		res->capacidad = capacidad;
	}
	return ok;
}

Logico compruebaCapacidad(int capacidad) {
	Logico res = CIERTO;
	if (!capacidad > 0) {
		res = FALSO;
	}
	return res;
}

int leeEspacioTeclado(PEspacio res) {
	int ok = 0;
	Cadena nombre;
	int planta;
	Cadena tipo;
	TipoEspacio tipoEsp;
	int capacidad;

	printf("Nombre: ");
	fflush(stdout);
	gets(nombre);
	printf("Planta: ");
	fflush(stdout);
	scanf("%d", &planta);
	printf("TipoEspacio: ");
	fflush(stdout);
	gets(tipo);
	printf("Capacidad: ");
	fflush(stdout);
	scanf("%d", &capacidad);
	ok = deCadenaATipoEspacio(tipo, &tipoEsp);
	if (ok == 0) {
		ok = inicializaEspacio(res, nombre, planta, tipoEsp, capacidad);
	} else {
		printf("Error en el tipo Espacio");
	}
	return ok;
}

void muestraEspacio(Espacio e) {
	Cadena tipo;

	printf("\tNombre: %s\n", e.nombre);
	printf("\tPlanta: %d\n", e.planta);
	deTipoEspacioACadena(tipo, e.tipo);
	printf("\tTipo: %s\n", tipo);
	printf("\tCapacidad: %d\n", e.capacidad);
}

int leeEspaciosTeclado(ArrayEspacios res) {
	int nEspacios = 0;
	int i = 0;

	printf("Introduce el numero de espacios a leer: ");
	fflush(stdout);
	scanf("%d", &nEspacios);
	fflush(stdin);
	while (nEspacios <= 0 || nEspacios > NUM_MAX_ESPACIOS) {
		printf("El numero de espacios debe estar entre 1 y %d\n", NUM_MAX_ESPACIOS);
		printf("Introduce el numero de resultados: ");
		fflush(stdout);
		scanf("%d", &nEspacios);
		fflush(stdin);
	}
	while(i < nEspacios) {
		printf("Espacios[%d]:\n", i+1);
		fflush(stdout);
		if (leeEspacioTeclado(&res[i])) {
			i++;
		}
	}
	return nEspacios;
}

void muestraEspacios(const ArrayEspacios res, int nEspacios) {
	int i = 0;
	for (i = 0; i < nEspacios; i++) {
		printf("Espacios[%d]\n", i+1);
		muestraEspacio(res[i]);
	}
}

int leeEspaciosFichero(const Cadena nombreFichero, ArrayEspacios res) {
	int nEspacios = 0;
	int i = 0;

	FILE* pf = NULL;
	pf = fopen(nombreFichero,"r");
	if (pf == NULL) {
		printf("Error en la apertura del fichero %s", nombreFichero);
		nEspacios = 0;
	} else {
		leeEspacioFichero(&res[0], pf);
		i = 1;
		while(i < NUM_MAX_ESPACIOS) {
			leeEspacioFichero(&res[i], pf);
			i++;
			if (feof(pf)) {
				break;
			}
		}
		nEspacios = i;
	}
	fclose(pf);
	return nEspacios;

}

void leeEspacioFichero(PEspacio pe, FILE* pf) {
	Cadena aux;
	char noSirvePaNa;

	fgets(pe->nombre, NUM_MAX_CARACTERES, pf);
	quitaSaltoDeLineaEspacio(pe->nombre);
	fscanf(pf, "%d%c", &pe->planta, &noSirvePaNa);
	fgets(aux, NUM_MAX_CARACTERES, pf);
	quitaSaltoDeLineaEspacio(aux);
	deCadenaATipoEspacio(aux, &pe->tipo);
	fscanf(pf, "%d%c", &pe->capacidad, &noSirvePaNa);
}

void quitaSaltoDeLineaEspacio(Cadena cad) {
	int i = 0;
	for (i = 0; strlen(cad); i++) {
		if (cad[i] == '\n') {
			cad[i] = '\0';
			break;
		}
	}
}
