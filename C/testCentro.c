/*
 * testCentro.c
 *
 *  Created on: 28/5/2015
 *      Author: Javier
 */

#include "centro.h"
#include <stdio.h>

void testMuestraCentro();
void testGetConteosEspacios();

int main (void) {
	testMuestraCentro();

	testGetConteosEspacios();

	return 0;
}

void testMuestraCentro() {
	Centro c1;
	strcpy(c1.nombre, "ETSII");
	strcpy(c1.direccion, "Reina Mercedes");
	c1.numeroPlantas = 2;
	c1.numeroSotanos = 0;

	muestraCentro(c1);
}

void testGetConteosEspacios() {
	ArrayEspacios espacios;
	ArrayInt res;
	strcpy(espacios[0].nombre, "H0.13");
	espacios[0].planta = 0;
	espacios[0].capacidad = 60;
	espacios[0].tipo = TEORIA;
	strcpy(espacios[1].nombre, "A1.03");
	espacios[1].planta = 0;
	espacios[1].capacidad = 50;
	espacios[1].tipo = EXAMEN;

	getConteosEspacios(espacios, 2, res);
	printf("\tNumero de espacios por tipo ->\n");
	printf("\tTeoria: %d", res[0]);
	printf("\tLaboratorio: %d", res[1]);
	printf("\tSeminario: %d", res[2]);
	printf("\tExamen: %d", res[3]);
	printf("\tOtro: %d", res[4]);
}
