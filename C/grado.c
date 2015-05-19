/*
 * grado.c
 *
 *  Created on: May 19, 2015
 *      Author: javiersantos
 */

#include "grado.h"

int inicializaGrado(PGrado res, const Cadena nombre, const Cadena centro, const ArrayAsignaturas obligatorias, int numObligatorias, const ArrayAsignaturas optativas, int numOptativas, double minimoCreditosOptativas) {
	int ok = 0;
	if(!compruebaCreditosOptativas(optativas, numOptativas) || !compruebaCreditosMinimosOptativas(minimoCreditosOptativas, optativas, numOptativas)) {
		printf("grado_c.inicializaGrado: Error en parametros");
		ok = -1;
	} else {
		strcpy(res->nombre, nombre);
		strcpy(res->centro, centro);
		res->numObligatorias, numObligatorias;
		res->numOptativas, numOptativas;
		res->minimoCreditosOptativas, minimoCreditosOptativas;
		strncpy(res->obligatorias, obligatorias, numObligatorias);
		strncpy(res->optativas, optativas, numOptativas);
	}
	return ok;
}

Logico compruebaCreditosOptativas(const ArrayAsignaturas optativas, int numOptativas) {
	Logico res = CIERTO;
	Logico esPrimero = FALSO;
	double creditos = 0.;
	int i = 0;
	for(i = 0; i<numOptativas; i++) {
		if(esPrimero) {
			creditos = optativas[i].creditos;
			esPrimero = CIERTO;
		} else {
			if(!strcmp(creditos, optativas[i].creditos)) {
				res = FALSO;
			}
		}
	}
	return res;
}

Logico compruebaCreditosMinimosOptativas(double minimoCreditosOptativas, const ArrayAsignaturas optativas, int numOptativas) {
	Logico res = CIERTO;
	double creditosAlumno = minimoCreditosOptativas;
	double creditosGrado = 0.;
	int i = 0;
	for(i = 0; i<numOptativas; i++) {
		creditosGrado += optativas[i].creditos;
	}
	if(0 > creditosAlumno || creditosGrado > creditosAlumno) {
		res = FALSO;
	}
	return res;
}

void muestraGrado(Grado) {


}
