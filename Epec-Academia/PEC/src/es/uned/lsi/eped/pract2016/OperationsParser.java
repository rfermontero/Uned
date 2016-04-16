package es.uned.lsi.eped.pract2016;

/**
 * Created by colymore on 12/4/16.
 */
public interface OperationsParser {
    void getSiblings(AcademiaIF academia, int doctorId);

    void getDescendants(AcademiaIF academia, int doctorId, int generations);

    void getStudients(AcademiaIF academia, int doctorId);

    void getAncestors(AcademiaIF academia, int doctorId, int generations);

    void getDirectors(AcademiaIF academia, int doctorId);
}
