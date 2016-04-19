package es.uned.lsi.eped.pract2016;

import org.junit.Test;

import es.uned.lsi.eped.DataStructures.CollectionIF;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AcademiaCTest {

	private static final int ANY_ID = 11;

	@Test
	public void academiaShouldReturnFounder() {
		DoctorC doctor = givenAnyDoctor();
		AcademiaC academy = givenAnAcademyWithFounder(doctor);

		assertEquals(doctor, academy.getFounder());
	}

	@Test
	public void academiaShouldFoundDoctorWithIdIfItsFounder() {
		DoctorIF doctor = givenAnyDoctorWithId(1);

		AcademiaC academy = givenAnAcademyWithFounder(doctor);
		DoctorIF expectedDoctor = academy.getDoctor(1);
		assertEquals(doctor, expectedDoctor);
	}

	@Test
	public void academiaShouldReturnDoctorWithIdOnASimpleStructure() {
		DoctorC founder = givenAnyDoctorWithId(1);
		DoctorC student2 = new DoctorC(founder, 2);
		DoctorC student3 = new DoctorC(founder, 3);

		founder.addStudent(student2);
		founder.addStudent(student3);

		AcademiaC academy = givenAnAcademyWithFounder(founder);

		DoctorIF doctorById = academy.getDoctor(2);

		assertEquals(student2, doctorById);
	}

	@Test
	public void academiaShouldReturnDoctorWithIdOnAMoreComplexStructure() {
		DoctorC founder = givenAnyDoctorWithId(1);
		DoctorC student2 = new DoctorC(founder, 2);
		DoctorC student3 = new DoctorC(founder, 3);
		DoctorC student4 = new DoctorC(student2, 4);
		DoctorC student5 = new DoctorC(student3, 5);
		student2.addStudent(student4);
		student3.addStudent(student5);
		founder.addStudent(student2);
		founder.addStudent(student3);
		AcademiaC academy = givenAnAcademyWithFounder(founder);

		DoctorIF doctorById = academy.getDoctor(5);

		assertEquals(student5, doctorById);
	}

	@Test
	public void doctorOneAndTwoShouldBeSupervisorsOfDoctorSeven() {
		AcademiaIF academia = givenAnyAcademiaScenario();

		DoctorC doctor7 = (DoctorC) academia.getDoctor(7);

		CollectionIF<DoctorIF> doctorSevenSupervisors = doctor7.getSupervisors();
		assertEquals(2, doctorSevenSupervisors.size());
		assertTrue(doctorSevenSupervisors.contains(new DoctorC(1)));
		assertTrue(doctorSevenSupervisors.contains(new DoctorC(2)));
	}

	@Test
	public void doctorTwoDescendantsInTwoGenerationsShouldBeTheExpectedOnes() {
		AcademiaIF academy = givenAnyAcademiaScenario();

		DoctorC doctor2 = (DoctorC) academy.getDoctor(2);
		CollectionIF<DoctorIF> descendantsOfTwoTwoGenerations = doctor2.getDescendants(2);

		assertTrue(descendantsOfTwoTwoGenerations.contains(new DoctorC(4)));
		assertTrue(descendantsOfTwoTwoGenerations.contains(new DoctorC(5)));
		assertTrue(descendantsOfTwoTwoGenerations.contains(new DoctorC(6)));
		assertTrue(descendantsOfTwoTwoGenerations.contains(new DoctorC(7)));
		assertEquals(4, descendantsOfTwoTwoGenerations.size());
	}

	private AcademiaIF givenAnyAcademiaScenario() {
		DoctorC founder = new DoctorC(1);
		AcademiaIF academia = new AcademiaC(founder);

		DoctorC doctor2 = new DoctorC(2);
		DoctorC doctor3 = new DoctorC(3);
		DoctorC doctor4 = new DoctorC(4);
		DoctorC doctor5 = new DoctorC(5);
		DoctorC doctor6 = new DoctorC(6);
		DoctorC doctor7 = new DoctorC(7);

		academia.addDoctor(doctor2, founder);
		academia.addDoctor(doctor3, founder);
		academia.addDoctor(doctor4, doctor2);
		academia.addDoctor(doctor5, doctor4);
		academia.addDoctor(doctor6, doctor2);
		academia.addDoctor(doctor7, founder);
		academia.addSupervision(doctor7, doctor2);
		return academia;
	}

	private DoctorC givenAnyDoctorWithId(int doctorId) {
		return new DoctorC(doctorId);
	}

	private DoctorC givenAnyDoctor() {
		return new DoctorC(ANY_ID);
	}

	private AcademiaC givenAnAcademyWithFounder(DoctorIF founder) {
		return new AcademiaC(founder);
	}

}
