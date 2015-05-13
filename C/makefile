C_COMPILER=gcc

ifeq ($(OS),Windows_NT)
	RM=cmd \/C del
else
	RM=rm -f 
endif

INCLUDES_ROOT=./includes
INC_DIRS=-I . -I$(INCLUDES_ROOT)
BIN_DIR=

SRC_FILES1=cadena.c testCadena.c
TARGET1=$(BIN_DIR)testCadena.exe

SRC_FILES2=tipoAsignatura.c testTipoAsignatura.c
TARGET2=$(BIN_DIR)testTipoAsignatura.exe

SRC_FILES3=asignatura.c tipoAsignatura.c testAsignatura.c
TARGET3=$(BIN_DIR)testAsignatura.exe

SRC_FILES4=grado.c asignatura.c tipoAsignatura.c testGrado.c
TARGET4=$(BIN_DIR)testGrado.exe

SRC_FILES5=tipoEspacio.c testTipoEspacio.c
TARGET5=$(BIN_DIR)testTipoEspacio.exe

SRC_FILES6=espacio.c tipoEspacio.c testEspacio.c
TARGET6=$(BIN_DIR)testEspacio.exe

SRC_FILES7=espacio.c tipoEspacio.c centro.c testCentro.c
TARGET7=$(BIN_DIR)testCentro.exe

all: clean default
	
clean:
	-$(RM) $(TARGET1)
	-$(RM) $(TARGET2)
	-$(RM) $(TARGET3)
	-$(RM) $(TARGET4)
	-$(RM) $(TARGET5)
	-$(RM) $(TARGET6)
	-$(RM) $(TARGET7)
	echo Borrados ejecutables
	 
testCadena:
	-$(C_COMPILER) $(INC_DIRS) $(SRC_FILES1) -o $(TARGET1)
	
testTipoAsignatura:
	-$(C_COMPILER) $(INC_DIRS) $(SRC_FILES2) -o $(TARGET2)

testAsignatura:
	-$(C_COMPILER) $(INC_DIRS) $(SRC_FILES3) -o $(TARGET3)

testGrado:
	-$(C_COMPILER) $(INC_DIRS) $(SRC_FILES4) -o $(TARGET4)

testTipoEspacio:
	-$(C_COMPILER) $(INC_DIRS) $(SRC_FILES5) -o $(TARGET5)

testEspacio:
	-$(C_COMPILER) $(INC_DIRS) $(SRC_FILES6) -o $(TARGET6)
	
testCentro:
	-$(C_COMPILER) $(INC_DIRS) $(SRC_FILES7) -o $(TARGET7)
	
default: testCadena testTipoAsignatura testAsignatura testGrado testTipoEspacio testEspacio testCentro
	-$(TARGET1)
	-$(TARGET2)
	-$(TARGET3)
	-$(TARGET4)
	-$(TARGET5)
	-$(TARGET6)
	-$(TARGET7)