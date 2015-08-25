/*
 * testEspacio.c
 *
 *  Created on: 28/5/2015
 *      Author: Javier
 */

#include "espacio.h"
#include <stdio.h>

void testMuestraEspacio();
void testMuestraEspacios();
int testLeeEspaciosFichero();

int main (void) {
	PEspacio pe;
	leeEspacioTeclado(pe);

	testMuestraEspacio();
	testMuestraEspacios();

	printf("Numero de espacios leidos: %d", testLeeEspaciosFichero());

	return 0;
}

void testMuestraEspacio() {
	Espacio e1;
	strcpy(e1.nombre, "H0.13");
	e1.capacidad = 60;
	e1.planta = 0;
	e1.tipo = TEORIA;

	muestraEspacio(e1);
}

void testMuestraEspacios() {
	ArrayEspacios res;
	strcpy(res[0].nombre, "H0.13");
	res[0].planta = 0;
	res[0].capacidad = 60;
	res[0].tipo = TEORIA;
	strcpy(res[1].nombre, "A1.03");
	res[1].planta = 0;
	res[1].capacidad = 50;
	res[1].tipo = EXAMEN;

	muestraEspacios(res, 2);
}

int testLeeEspaciosFichero() {
	const Cadena fichero = "res/espacios.txt";
	ArrayEspacios res;

	return leeEspaciosFichero(fichero, res);
}
