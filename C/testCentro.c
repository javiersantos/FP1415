/*
 * testCentro.c
 *
 *  Created on: 28/5/2015
 *      Author: Javier
 */

#include "centro.h"
#include <stdio.h>

void testMuestraCentro();

int main (void) {
	testMuestraCentro();

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
