/*
 * testEspacio.c
 *
 *  Created on: 28/5/2015
 *      Author: Javier
 */

#include "espacio.h"
#include <stdio.h>

void testMuestraEspacio();

int main (void) {
	PEspacio pe;
	leeEspacioTeclado(pe);

	testMuestraEspacio();

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
