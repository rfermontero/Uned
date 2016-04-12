package es.uned.lsi.eped.pract2016;

import es.uned.lsi.eped.DataStructures.CollectionIF;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Set;
import es.uned.lsi.eped.DataStructures.SetIF;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoctorSTest {

    private static final int ID = 1;

    @Test
    public void shouldReturnEmptyCollectionIfDoctorHasNotStudients() {
        DoctorIF doctor = givenADoctorWithoutStudientsAnAncestor();

        CollectionIF<DoctorIF> studients = doctor.getStudents();

        assertTrue(studients.isEmpty());
    }

    @Test
    public void shouldReturnListOfTwoStudientsUsedToCreateTheDoctor() {
        int anyNumberOfStudents = 2;
        DoctorIF doctor = givenADoctorWithStudents(anyNumberOfStudents);

        CollectionIF<DoctorIF> students = doctor.getStudents();

        assertEquals(anyNumberOfStudents, students.size());
    }

    @Test
    public void shouldReturnAnEmptyCollectionOfAncestorsIfTheDoctorIsAFounder() {
        int anyGenerations = 1;
        DoctorIF founder = givenAFounder();

        CollectionIF<DoctorIF> ancestors = founder.getAncestors(anyGenerations);

        assertTrue(ancestors.isEmpty());
    }

    @Test
    public void shouldReturnAnCollectionWithJustOneAncestorIfTheDoctorHasOneSupervisorAndTheGenerationUsedIsOne() {
        DoctorIF doctor = givenADoctorWithOneSupervisor();

        CollectionIF<DoctorIF> ancestors = doctor.getAncestors(1);

        assertEquals(1, ancestors.size());
    }

    @Test
    public void shouldReturnMaxAncestorsFoundedIfAskForBiggerAncestorsNumber() {
        DoctorIF doctor = givenADoctorWithSupervisorGenerations(2);

        CollectionIF<DoctorIF> ancestors = doctor.getAncestors(3);

        assertEquals(2, ancestors.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionIfTheGenerationIsLessOrEqualToZero() {
        DoctorIF doctor = givenAnyDoctor();

        doctor.getAncestors(0);
    }

    @Test
    public void shouldReturnEmptyDescendantsCollectionIfNotHasDescendants() {
        DoctorS doctor = givenAnyDoctor();

        assertTrue(doctor.getDescendants(1).isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionIfTheDescendantsGenerationIsLessOrEqualToZero() {
        DoctorIF doctor = givenAnyDoctor();

        doctor.getDescendants(0);
    }

    @Test
    public void shouldReturnStudientsIfTheDescendantGenerationIsOne() {
        DoctorIF doctor = givenADoctorWithStudents(2);

        CollectionIF<DoctorIF> descendats = doctor.getDescendants(1);

        assertEquals(2, descendats.size());
    }

    @Test
    public void shoudlNotContainsCurrentDoctorInSlibings() {
        DoctorIF doctor = givenADoctorWithStudents(4);
        DoctorIF anyStudientOfDoctor = getAnyStudientForDoctor(doctor);

        CollectionIF<DoctorIF> slibings = anyStudientOfDoctor.getSiblings();

        assertTrue(!slibings.contains(anyStudientOfDoctor));
    }

    @Test
    public void shouldHaveStudientsOfDoctorAsSlibingsLessOne() {
        DoctorIF doctor = givenADoctorWithStudents(4);
        DoctorIF anyStudientOfDoctor = getAnyStudientForDoctor(doctor);

        CollectionIF<DoctorIF> slibings = anyStudientOfDoctor.getSiblings();

        assertEquals(4-1, slibings.size());
    }

    private DoctorIF getAnyStudientForDoctor(DoctorIF doctor) {
        int size = doctor.getStudents().size() / 2;
        IteratorIF<DoctorIF> it = doctor.getStudents().iterator();
        DoctorIF studient = null;
        for (int i = 0; i < size; i++) {
            studient = it.getNext();
        }
        return studient;
    }

    private DoctorS givenAnyDoctor() {
        return new DoctorS(ID);
    }

    private DoctorIF givenADoctorWithSupervisorGenerations(int numberOfSupervisorGenerations) {
        DoctorS currentDoctor = givenAnyDoctor();

        for (int i = 0; i < numberOfSupervisorGenerations; i++) {
            currentDoctor = new DoctorS(currentDoctor, i);
        }

        return currentDoctor;
    }

    private DoctorIF givenADoctorWithOneSupervisor() {
        DoctorIF supervisor = givenADoctorWithoutStudientsAnAncestor();
        return new DoctorS(supervisor, ID);
    }

    private DoctorIF givenAFounder() {
        return givenADoctorWithoutStudientsAnAncestor();
    }

    private DoctorIF givenADoctorWithoutStudientsAnAncestor() {
        return givenAnyDoctor();
    }

    private DoctorIF givenADoctorWithStudents(int numberOfStudients) {
        SetIF<DoctorIF> studients = new Set<>();
        for (int i = 0; i < numberOfStudients; i++) {
            Set<DoctorIF> oneStudientSet = new Set<>(givenADoctorWithoutStudientsAnAncestor());
            studients = studients.union(oneStudientSet);
        }
        return new DoctorS(studients, ID);
    }
}
