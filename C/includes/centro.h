/*
 * centro.h
 *
 *  Created on: May 22, 2015
 *      Author: javiersantos
 */

#ifndef INCLUDES_CENTRO_H_
#define INCLUDES_CENTRO_H_

#include "cadena.h"
#include "espacio.h"
#include <stdio.h>

#define NUM_MAX_ARRAY 200

typedef struct {
	Cadena nombre;
	Cadena direccion;
	int numeroPlantas;
	int numeroSotanos;
	ArrayEspacios espacios;
	int numEspacios;
}Centro;

typedef Centro* PCentro;
typedef int ArrayInt[NUM_MAX_ARRAY];

int inicializaCentro(PCentro, const Cadena, const Cadena, int, int, const ArrayEspacios, int);
void muestraCentro (Centro);
void getConteosEspacios(const ArrayEspacios, int, ArrayInt);

Logico compruebaPlantas(int);
Logico compruebaSotanos(int);


#endif /* INCLUDES_CENTRO_H_ */
