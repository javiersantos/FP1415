/*
 * asignatura.h
 *
 *  Created on: 11/5/2015
 *      Author: Javier
 */

#ifndef INCLUDES_ASIGNATURA_H_
#define INCLUDES_ASIGNATURA_H_

#include <stdio.h>
#include "tipoAsignatura.h"
#define NUM_MAX_ASIGNATURAS 100

typedef struct {
	Cadena nombre;
	TipoCodigo codigo;
	double creditos;
	TipoAsignatura tipo;
	int curso;
	Cadena departamento;
}Asignatura;

typedef Asignatura* PAsignatura;
typedef char TipoCodigo[8];
typedef Asignatura ArrayAsignaturas[NUM_MAX_ASIGNATURAS];

int inicializaAsignatura(PAsignatura, const	Cadena, const TipoCodigo, double, TipoAsignatura, int, const Cadena);
void getAcronimo(Asignatura, Cadena);
int	leeAsignaturaTeclado(PAsignatura);
void muestraAsignatura(Asignatura);
int	leeAsignaturasTeclado(ArrayAsignaturas);
void muestraAsignaturas(const ArrayAsignaturas,	int);
int leeAsignaturasFichero(const Cadena, ArrayAsignaturas);

Logico compruebaCodigo(const TipoCodigo);
Logico compruebaCreditos(double);
Logico compruebaCurso(int);


#endif /* INCLUDES_ASIGNATURA_H_ */
