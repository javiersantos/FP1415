/*
 * grado.h
 *
 *  Created on: 18/5/2015
 *      Author: practica
 */

#ifndef GRADO_H_
#define GRADO_H_

#include "cadena.h"
#include "asignatura.h"
#include <stdio.h>

typedef struct {
	Cadena nombre;
	Cadena centro;
	ArrayAsignaturas obligatorias;
	int numObligatorias;
	ArrayAsignaturas optativas;
	int numOptativas;
	double minimoCreditosOptativas;
}Grado;

typedef Grado* PGrado;

int inicializaGrado(PGrado, const Cadena, const Cadena, const ArrayAsignaturas, int, const ArrayAsignaturas, int, double);
void muestraGrado(Grado);

Logico compruebaCreditosOptativas(const ArrayAsignaturas, int);
Logico compruebaCreditosMinimosOptativas(double, const ArrayAsignaturas, int);

#endif /* GRADO_H_ */
