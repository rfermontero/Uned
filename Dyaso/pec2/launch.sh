#!/bin/bash
rm Ej*;
echo y | rm fichero1
gcc fuente1.c -o Ej1
gcc fuente2.c -o Ej2
./Ej1