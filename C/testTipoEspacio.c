/*
 * testTipoEspacio.c
 *
 *  Created on: 28/5/2015
 *      Author: Javier
 */

#include "tipoEspacio.h"
#include <stdio.h>

void testTeoria();

int main (void) {
	testTeoria();

	return 0;
}

void testTeoria() {
	TipoEspacio tipo = TEORIA;
	Cadena res;
	deTipoEspacioACadena(res, tipo);
	printf("%s", res);
}
