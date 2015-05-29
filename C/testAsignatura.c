/*
 * testAsignatura.c
 *
 *  Created on: 28/5/2015
 *      Author: Javier
 */

#include "asignatura.h"
#include <stdio.h>

void testGetAcronimo();
void testMuestraAsignatura();
int testLeeAsignaturasFichero();

int main (void) {
	testGetAcronimo();

	PAsignatura pa;
	leeAsignaturaTeclado(pa);

	testMuestraAsignatura();

	printf("Numero de asignaturas leidas: %d", testLeeAsignaturasFichero());

	return 0;
}

void testGetAcronimo() {
	Asignatura a;
	Cadena res;

	strcpy(a.nombre, "Fundamentos de Programacion");
	strcpy(a.codigo, "123456ABC");
	a.creditos = 12.;
	strcpy(a.departamento, "LSI");
	a.tipo = ANUAL;
	a.curso = 1;

	getAcronimo(a, res);
	printf("\tAcronimo: %s\n", res);
}

void testMuestraAsignatura() {
	Asignatura a;
	strcpy(a.nombre, "Fundamentos de Programacion");
	strcpy(a.codigo, "123456ABC");
	a.creditos = 12.;
	strcpy(a.departamento, "LSI");
	a.tipo = ANUAL;
	a.curso = 1;

	muestraAsignatura(a);
}

int testLeeAsignaturasFichero() {
	const Cadena fichero = "res/asignaturas.txt";
	ArrayAsignaturas res;

	return leeAsignaturasFichero(fichero, res);
}
