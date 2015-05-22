/*
 * espacio.h
 *
 *  Created on: May 19, 2015
 *      Author: javiersantos
 */

#ifndef INCLUDES_ESPACIO_H_
#define INCLUDES_ESPACIO_H_

#include "cadena.h"
#include "logico.h"
#include "tipoEspacio.h"

#define NUM_MAX_ESPACIOS 200

typedef struct {
	Cadena nombre;
	int planta;
	TipoEspacio tipo;
	int capacidad;
}Espacio;

typedef Espacio* PEspacio;
typedef Espacio ArrayEspacios[NUM_MAX_ESPACIOS];

int inicializaEspacio(PEspacio, const Cadena, int, TipoEspacio, int);
int leeEspacioTeclado(PEspacio);
void muestraEspacio(Espacio);
int leeEspaciosTeclado(ArrayEspacios);
void muestraEspacios(const ArrayEspacios, int);
int leeEspaciosFichero(const Cadena, ArrayEspacios);


#endif /* INCLUDES_ESPACIO_H_ */
