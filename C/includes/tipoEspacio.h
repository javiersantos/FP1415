/*
 * tipoEspacio.h
 *
 *  Created on: 11/5/2015
 *      Author: Javier
 */

#ifndef INCLUDES_TIPOESPACIO_H_
#define INCLUDES_TIPOESPACIO_H_

#include "cadena.h"

typedef enum {TEORIA, LABORATORIO, SEMINARIO, EXAMEN, OTRO}TipoEspacio;

void deTipoEspacioACadena(Cadena, TipoEspacio);
int deCadenaATipoEspacio(const Cadena, TipoEspacio *);


#endif /* INCLUDES_TIPOESPACIO_H_ */
