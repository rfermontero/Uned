package es.uned.lsi.eped.pract2016;

import org.junit.Test;

import es.uned.lsi.eped.DataStructures.CollectionIF;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Set;
import es.uned.lsi.eped.DataStructures.SetIF;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoctorCTest {

	private static final int ID = 1;

	@Test
	public void shouldReturnEmptyCollectionIfDoctorHasNotStudients() {
		DoctorIF doctor = givenADoctorWithoutStudentsAnAncestor();

		CollectionIF<DoctorIF> students = doctor.getStudents();

		assertTrue(students.isEmpty());
	}

	@Test
	public void shouldReturnListOfTwoStudentsUsedToCreateTheDoctor() {
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
		DoctorC doctor = givenAnyDoctor();

		assertTrue(doctor.getDescendants(1).isEmpty());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowAnExceptionIfTheDescendantsGenerationIsLessOrEqualToZero() {
		DoctorIF doctor = givenAnyDoctor();

		doctor.getDescendants(0);
	}

	@Test
	public void shouldReturnStudentsIfTheDescendantGenerationIsOne() {
		DoctorIF doctor = givenADoctorWithStudents(2);

		CollectionIF<DoctorIF> descendants = doctor.getDescendants(1);

		assertEquals(2, descendants.size());
	}

	@Test
	public void shouldNotContainsCurrentDoctorInSibling() {
		DoctorIF doctor = givenADoctorWithStudents(4);
		DoctorIF anyStudentOfDoctor = getAnyStudentForDoctor(doctor);

		CollectionIF<DoctorIF> siblings = anyStudentOfDoctor.getSiblings();

		assertTrue(!siblings.contains(anyStudentOfDoctor));
	}

	@Test
	public void shouldHaveStudentsOfDoctorAsSiblingLessOne() {
		int anyStudentsSize = 4;
		DoctorIF doctor = givenADoctorWithStudents(anyStudentsSize);
		DoctorIF anyStudentOfDoctor = getAnyStudentForDoctor(doctor);

		CollectionIF<DoctorIF> siblings = anyStudentOfDoctor.getSiblings();

		assertEquals(anyStudentsSize - 1, siblings.size());
	}

	private DoctorIF getAnyStudentForDoctor(DoctorIF doctor) {
		int size = doctor.getStudents().size() / 2;
		IteratorIF<DoctorIF> it = doctor.getStudents().iterator();
		DoctorIF student = null;
		for (int i = 0; i < size; i++) {
			student = it.getNext();
		}
		return student;
	}

	private DoctorC givenAnyDoctor(int id) {
		return new DoctorC(ID + id);
	}

	private DoctorC givenAnyDoctor() {
		return new DoctorC(ID);
	}

	private DoctorIF givenADoctorWithSupervisorGenerations(int numberOfSupervisorGenerations) {
		DoctorC currentDoctor = givenAnyDoctor();

		for (int i = 0; i < numberOfSupervisorGenerations; i++) {
			currentDoctor = new DoctorC(currentDoctor, i);
		}

		return currentDoctor;
	}

	private DoctorIF givenADoctorWithOneSupervisor() {
		DoctorIF supervisor = givenADoctorWithoutStudentsAnAncestor();
		return new DoctorC(supervisor, ID);
	}

	private DoctorIF givenAFounder() {
		return givenADoctorWithoutStudentsAnAncestor();
	}

	private DoctorIF givenADoctorWithoutStudentsAnAncestor() {
		return givenAnyDoctor();
	}

	private DoctorIF givenADoctorWithStudents(int numberOfStudents) {
		SetIF<DoctorIF> students = new Set<>();
		for (int i = 1; i <= numberOfStudents; i++) {
			Set<DoctorIF> oneStudentSet = new Set<DoctorIF>(givenAnyDoctor(i));
			students = students.union(oneStudentSet);
		}
		return new DoctorC(students, ID);
	}
}
