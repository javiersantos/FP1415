/*
 * testTipoAsignatura.c
 *
 *  Created on: 25/5/2015
 *      Author: Javier
 */

#include "tipoAsignatura.h"
#include <stdio.h>

void testAnual();

int main(void) {
	testAnual();

	return 0;
}

void testAnual() {
	TipoAsignatura tipo = ANUAL;
	Cadena res;
	deTipoAsignaturaACadena(res, tipo);
	printf("%s", res);
}
