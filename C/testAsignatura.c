/*
 * testAsignatura.c
 *
 *  Created on: 28/5/2015
 *      Author: Javier
 */

#include "asignatura.h"
#include <stdio.h>

void testMuestraAsignatura();

int main (void) {
	PAsignatura pa;
	leeAsignaturaTeclado(pa);

	testMuestraAsignatura();

	return 0;
}

void testMuestraAsignatura() {
	Asignatura a1;
	strcpy(a1.nombre, "Fundamentos de Programacion");
	strcpy(a1.codigo, "123456ABC");
	a1.creditos = 12.;
	strcpy(a1.departamento, "LSI");
	a1.tipo = ANUAL;
	a1.curso = 1;

	muestraAsignatura(a1);
}
