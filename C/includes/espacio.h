/*
 * espacio.h
 *
 *  Created on: May 19, 2015
 *      Author: javiersantos
 */

#ifndef INCLUDES_ESPACIO_H_
#define INCLUDES_ESPACIO_H_

#include "cadena.h"
#include <stdio.h>

#define NUM_MAX_ESPACIOS 200

typedef struct {
	Cadena nombre;
	int planta;
	TipoEspacio tipo;
	int capacidad;
}Espacio;

typedef Espacio* PEspacio;
typedef Espacio ArrayEspacios[NUM_MAX_ESPACIOS];


#endif /* INCLUDES_ESPACIO_H_ */
