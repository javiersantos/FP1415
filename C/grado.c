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
	double creditos = 0.;
	int i = 0;
	if (numOptativas > 0) {
		creditos = optativas[0].creditos;
		for(i = 1; i<numOptativas; i++) {
			if(creditos != optativas[i].creditos) {
				res = FALSO;
				break;
			}
		}
	}
	return res;
}

Logico compruebaCreditosMinimosOptativas(double minimoCreditosOptativas, const ArrayAsignaturas optativas, int numOptativas) {
	Logico res = CIERTO;
	double creditosGrado = 0.;
	int i = 0;
	for(i = 0; i<numOptativas; i++) {
		creditosGrado += optativas[i].creditos;
	}
	if(0 > minimoCreditosOptativas || creditosGrado > minimoCreditosOptativas) {
		res = FALSO;
	}
	return res;
}

void muestraGrado(Grado g) {
	int i = 0;

	printf("\tNombre: %s\n", g.nombre);
	printf("\tCentro: %s\n", g.centro);
	printf("\tMinimo creditos optativas: %5.0lf\n", g.minimoCreditosOptativas);
	printf("\tAsignaturas obligatorias: \n");
	muestraAsignaturas(g.obligatorias, g.numObligatorias)
	printf("\tAsignaturas optativa: \n");
	muestraAsignaturas(g.optativas, g.numOptativas);
}

