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

int deCadenaATipoEspacio(const Cadena tipo, TipoEspacio *res {


}
