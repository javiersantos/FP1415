/*
 * tipoEspacio.c
 *
 *  Created on: May 19, 2015
 *      Author: javiersantos
 */
#include "tipoEspacio.h"

void deTipoEspacioACadena(Cadena res, TipoEspacio tipo) {
	switch (tipo) {
		case TEORIA:
			strcpy(res, "TEORIA");
			break;
		case LABORATORIO:
			strcpy(res, "LABORATORIO");
			break;
		case SEMINARIO:
			strcpy(res, "SEMINARIO");
			break;
		case EXAMEN:
			strcpy(res, "EXAMEN");
			break;
		case OTRO:
			strcpy(res, "OTRO");
			break;
	}

}

int deCadenaATipoEspacio(const Cadena tipo, TipoEspacio *res) {
	int ok = 0;
	if (strcmp(tipo, "TEORIA") == 0) {
		*res = TEORIA;
	} else {
		if (strcmp(tipo, "LABORATORIO") == 0) {
			*res = LABORATORIO;
		} else {
			if (strcmp(tipo, "SEMINARIO") == 0) {
				*res = SEMINARIO;
			} else {
				if (strcmp(tipo, "EXAMEN") == 0) {
					*res = EXAMEN;
				} else {
					if (strcmp(tipo, "OTRO") == 0) {
						*res = OTRO;
					} else {
						ok = -1;
					}
				}
			}
		}
	}
	return ok;
}
