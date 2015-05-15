/*
 * tipoAsignatura.c
 *
 *  Created on: 11/5/2015
 *      Author: Javier
 */
#include "tipoAsignatura.h"

void deTipoAsignaturaACadena(Cadena res, TipoAsignatura	 tipo) {
	switch (tipo) {
		case ANUAL:
			strcpy(res, "ANUAL");
			break;
		case PRIMER_CUATRIMESTRE:
			strcpy(res, "PRIMER_CUATRIMESTRE");
			break;
		case SEGUNDO_CUATRIMESTRE:
			strcpy(res, "SEGUNDO_CUATRIMESTRE");
			break;
	}

}

int	deCadenaATipoAsignatura(const Cadena tipo, TipoAsignatura *res) {
	int ok = 0;
	if (strcmp(tipo, "ANUAL") == 0) {
		*res = ANUAL;
	} else {
		if (strcmp(tipo, "PRIMER_CUATRIMESTRE") == 0) {
			*res = PRIMER_CUATRIMESTRE;
		} else {
			if (strcmp(tipo, "SEGUNDO_CUATRIMESTRE") == 0) {
				*res = SEGUNDO_CUATRIMESTRE;
			} else {
				ok = -1;
			}
		}
	}

	return res;
}
