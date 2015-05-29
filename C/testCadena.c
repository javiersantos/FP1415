/*
 * testCadena.c
 *
 *  Created on: 25/5/2015
 *      Author: Javier
 */

#include "cadena.h"

int main (void) {
	Cadena res = "Javier\nSantos";
	printf("(1): %s***\n", res);
	quitaSaltoDeLineaCadena(res);
	printf("(2): %s***\n", res);

	return 0;
}
