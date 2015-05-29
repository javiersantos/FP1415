/*
 * centro.c
 *
 *  Created on: May 22, 2015
 *      Author: javiersantos
 */

#include "centro.h"

int inicializaCentro(PCentro res, const Cadena nombre, const Cadena direccion, int numeroPlantas, int numeroSotanos, const ArrayEspacios espacios, int numEspacios) {
	int ok = 0;
	int i = 0;
	if (!compruebaPlantas(numeroPlantas) || !compruebaSotanos(numeroSotanos)) {
		printf("centro_c.inicializaCentro: Error en parametros");
		ok = -1;
	} else {
		strcpy(res->nombre, nombre);
		strcpy(res->direccion, direccion);
		res->numeroPlantas = numeroPlantas;
		res->numeroSotanos = numeroSotanos;
		for (i = 0; i < numEspacios; i++) {
			res->espacios[i] = espacios[i];
		}
		res->numEspacios = numEspacios;
	}
	return ok;
}

Logico compruebaPlantas(int numeroPlantas) {
	Logico res = CIERTO;
	if (numeroPlantas < 1) {
		res = FALSO;
	}
	return res;
}

Logico compruebaSotanos(int numeroSotanos) {
	Logico res = CIERTO;
	if (numeroSotanos < 0) {
		res = FALSO;
	}
	return res;
}

void muestraCentro (Centro c) {
	int i = 0;

	printf("\tNombre: %s\n", c.nombre);
	printf("\tDireccion: %s\n", c.direccion);
	printf("\tNumero de plantas: %d\n", c.numeroPlantas);
	printf("\tNumero de sotanos: %d\n", c.numeroSotanos);
	printf("\tEspacios: \n");
	if (c.numEspacios == 0) {
		printf("(No hay espacios en el centro)\n");
	}
	for (i = 0; i < c.numEspacios; i++) {
		muestraEspacio(c.espacios[i]);
	}
}

void getConteosEspacios(const ArrayEspacios espacios, int nEspacios, ArrayInt c) {
	int i = 0;

	for (i = 0; i < nEspacios; i++) {
		switch (espacios[i].tipo) {
			case TEORIA:
				c[0]++;
				break;
			case LABORATORIO:
				c[1]++;
				break;
			case SEMINARIO:
				c[2]++;
				break;
			case EXAMEN:
				c[3]++;
				break;
			default:
				c[4]++;
				break;
		}
	}
}
