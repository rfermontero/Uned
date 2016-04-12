package es.uned.lsi.eped.pract2016;

import es.uned.lsi.eped.DataStructures.CollectionIF;

public interface AcademiaIF extends CollectionIF<DoctorIF> {
    /**
     * Consulta el Doctor que fundó la Academia
     *
     * @returns el Doctor fundador de la Academia
     */
    DoctorIF getFounder();

    /**
     * Busca un Doctor dentro de la Academia a partir de su identificador
     *
     * @pre el doctor pertenece a la Academia && /* @param el identificador del Doctor a buscar /* @returns el Doctor buscado
     * id>0
     */
    DoctorIF getDoctor(int id);

    /**
     * Consulta el número de Doctores que pertenecen a la Academia @returns el número de Doctores pertenecientes a la Academia
     */
    int size();

    /**
     * Añade un nuevo Doctor a la Academia a partir de la lectura de su Tesis
     *
     * @param el nuevo Doctor y su primer director de Tesis @pre el nuevo doctor no debe pertenecer a la Academia &&
     *           el supervisor  sí debe pertenecer a la Academia
     */
    void addDoctor(DoctorIF newDoctor, DoctorIF supervisor);

    /**
     * Añade una relación de dirección al Árbol Genealógico de la Academia
     *
     * @param el nuevo Doctor y uno de sus codirectores de Tesis
     * @pre ambos doctores deben pertenecer a la Academia &&
     * no existe una relación de supervisión previa entre ambos
     */
    void addSupervision(DoctorIF student, DoctorIF supervisor);

}