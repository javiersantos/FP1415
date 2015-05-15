/*
 * tipoAsignatura.h
 *
 *  Created on: 11/5/2015
 *      Author: Javier
 */

#ifndef INCLUDES_TIPOASIGNATURA_H_
#define INCLUDES_TIPOASIGNATURA_H_

#include "cadena.h"

typedef enum {ANUAL, PRIMER_CUATRIMESTRE, SEGUNDO_CUATRIMESTRE}TipoAsignatura;

void deTipoAsignaturaACadena(Cadena, TipoAsignatura);
int	deCadenaATipoAsignatura(const Cadena, TipoAsignatura *);


#endif /* INCLUDES_TIPOASIGNATURA_H_ */
