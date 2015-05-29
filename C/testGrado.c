/*
 * testGrado.c
 *
 *  Created on: 28/5/2015
 *      Author: Javier
 */

#include "grado.h"
#include <stdio.h>

void testMuestraGrado();

int main (void) {
	testMuestraGrado();

	return 0;
}

void testMuestraGrado() {
	Grado g1;
	strcpy(g1.nombre, "Software");
	strcpy(g1.centro, "ETSII");
	g1.numObligatorias = 0;
	g1.numOptativas = 0;
	g1.minimoCreditosOptativas = 120.;

	muestraGrado(g1);
}
