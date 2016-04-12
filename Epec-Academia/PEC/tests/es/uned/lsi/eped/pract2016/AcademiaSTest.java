package es.uned.lsi.eped.pract2016;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AcademiaSTest {

    private static final int ANY_ID = 11;

    @Test
    public void academiaShouldReturnFounder() {
        DoctorS doctor = givenAnyDoctor();
        AcademiaIF academy = givenAnAcademyWithFounder(doctor);

        assertEquals(doctor, academy.getFounder());
    }

    @Test
    public void academiaShouldFoundDoctorWithIdIfItsFounder() {
        DoctorIF doctor = givenAnyDoctorWithId(1);

        AcademiaS academy = givenAnAcademyWithFounder(doctor);
        DoctorIF expectedDoctor = academy.getDoctor(1);
        assertEquals(doctor, expectedDoctor);
    }

    @Test
    public void academiaShouldReturnDoctorWithIdOnASimpleStructure() {
        DoctorS founder = givenAnyDoctorWithId(1);
        DoctorS student2 = new DoctorS(founder, 2);
        DoctorS student3 = new DoctorS(founder, 3);

        founder.addStudent(student2);
        founder.addStudent(student3);

        AcademiaS academy = givenAnAcademyWithFounder(founder);

        DoctorIF doctorById = academy.getDoctor(2);

        assertEquals(student2, doctorById);
    }

    @Test
    public void academiaShouldReturnDoctorWithIdOnAMoreComplexStructure() {
        DoctorS founder = givenAnyDoctorWithId(1);
        DoctorS student2 = new DoctorS(founder, 2);
        DoctorS student3 = new DoctorS(founder, 3);
        DoctorS student4 = new DoctorS(student2, 4);
        DoctorS student5 = new DoctorS(student3, 5);
        student2.addStudent(student4);
        student3.addStudent(student5);
        founder.addStudent(student2);
        founder.addStudent(student3);
        AcademiaS academy = givenAnAcademyWithFounder(founder);

        DoctorIF doctorById = academy.getDoctor(5);

        assertEquals(student5, doctorById);
    }

    @Test
    public void doctorSevenShouldBeTheSupervisorOfDoctorThree() {
        AcademiaIF academia = givenAnyAcademiaScenario();

        DoctorS doctor7 = (DoctorS) academia.getDoctor(7);

        assertEquals(new DoctorS(3), doctor7.getSupervisor());
    }

    private AcademiaIF givenAnyAcademiaScenario() {
        DoctorS founder = new DoctorS(1);
        AcademiaIF academia = new AcademiaS(founder);

        DoctorS doctor2 = new DoctorS(2);
        DoctorS doctor3 = new DoctorS(3);
        DoctorS doctor4 = new DoctorS(4);
        DoctorS doctor5 = new DoctorS(5);
        DoctorS doctor6 = new DoctorS(6);
        DoctorS doctor7 = new DoctorS(7);

        academia.addDoctor(doctor2, founder);
        academia.addDoctor(doctor3, founder);
        academia.addDoctor(doctor4, doctor2);
        academia.addDoctor(doctor5, doctor4);
        academia.addDoctor(doctor6, doctor2);
        academia.addDoctor(doctor7, doctor3);
        return academia;
    }

    private DoctorS givenAnyDoctorWithId(int doctorId) {
        return new DoctorS(doctorId);
    }

    private DoctorS givenAnyDoctor() {
        return new DoctorS(ANY_ID);
    }

    private AcademiaS givenAnAcademyWithFounder(DoctorIF doctor) {
        return new AcademiaS(doctor);
    }

}