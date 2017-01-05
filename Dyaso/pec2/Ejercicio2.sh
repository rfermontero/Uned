#!/bin/bash
# Elimina ficheros compilados previamente
# Elimina fichero1
# Compila ejecutables
# Otorga permisos
# Lanza Ej1
ls | grep -P "^Ej.*[0-9]{1}$" | xargs -d"\n" rm
echo y | rm fichero1 2>/dev/null

gcc Trabajo2/fuente1.c -o Ej1
gcc Trabajo2/fuente2.c -o Ej2
gcc Trabajo2/fuente3.c -o Ej3

chmod +x Ej*

./Ej1