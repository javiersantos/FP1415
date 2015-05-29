/*
 * cadena.c
 *
 *  Created on: 11/5/2015
 *      Author: Javier
 */

#include "cadena.h"

void quitaSaltoDeLineaCadena(Cadena cad) {
	int i = 0;
	for (i = 0; strlen(cad); i++) {
		if (cad[i] == '\n') {
			cad[i] = '\0';
			break;
		}
	}
}
