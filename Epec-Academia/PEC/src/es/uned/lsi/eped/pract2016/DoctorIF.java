package es.uned.lsi.eped.pract2016;

/* Representación de un Doctor perteneciente a una Academia */

import es.uned.lsi.eped.DataStructures.CollectionIF;

public interface DoctorIF {

    /**
     * Consulta los ancestros académicos del doctor, limitándose al número de
     * generaciones indicado por el parámetro.
     *
     * @param número de generaciones a considerar
     * @returns la colección de ancestros académicos del doctor limitada al
     * número de generaciones indicado o hasta llegar al fundador de la
     * Academia. No deberá contener repeticiones.
     * @pre generations > 0
     */
    CollectionIF<DoctorIF> getAncestors(int generations);

    /**
     * Consulta los doctores a quienes el doctor ha dirigido sus Tesis.
     *
     * @returns la colección de doctores cuyo director de tesis es el doctor.
     */
    CollectionIF<DoctorIF> getStudents();

    /**
     * Consulta los descendientes académicos del doctor, limitándose al número
     * de generaciones indicado por el parámetro.
     *
     * @param número de generaciones a considerar
     * @returns la colección de descendientes académicos del doctor limitada
     * al número de generaciones indicado o hasta llegar a Doctores que no
     * hayan dirigido ninguna Tesis. No deberá contener repeticiones.
     * @pre generations > 0
     */
    CollectionIF<DoctorIF> getDescendants(int generations);

    /**
     * Consulta los doctores que comparten director de tesis con el doctor.
     *
     * @returns la colección de hermanos académicos del doctor. No deberá
     * contener repeticiones ni al doctor llamante
     */
    CollectionIF<DoctorIF> getSiblings();
}