package es.uned.lsi.eped.pract2016.parser;

import es.uned.lsi.eped.DataStructures.CollectionIF;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.pract2016.AcademiaIF;
import es.uned.lsi.eped.pract2016.DoctorC;
import es.uned.lsi.eped.pract2016.DoctorIF;
import es.uned.lsi.eped.pract2016.OperationsParser;

class OperationsParserSScenario implements OperationsParser {

	@Override
	public void getSiblings(AcademiaIF academia, int doctorId) {
		DoctorC doctor = (DoctorC) academia.getDoctor(doctorId);
		if (doctor != null) {
			CollectionIF<DoctorIF> siblings = doctor.getSiblings();
			IteratorIF<DoctorIF> siblingsIterator = siblings.iterator();
			System.out.println(String.format("Los hermanos de %d son %s",
					doctorId, getStringForElementsIn(siblingsIterator)));
		}
	}

	@Override
	public void getDescendants(AcademiaIF academia, int doctorId, int generations) {
		DoctorC doctor = (DoctorC) academia.getDoctor(doctorId);
		if (doctor != null) {
			CollectionIF<DoctorIF> supervisors = doctor.getDescendants(generations);
			IteratorIF<DoctorIF> supervisorsIterator = supervisors.iterator();
			System.out.println(String.format("Los descendientes de %d hasta %d generaciones son %s",
					doctorId, generations, getStringForElementsIn(supervisorsIterator)));
		}
	}

	@Override
	public void getStudents(AcademiaIF academia, int doctorId) {
		DoctorC doctor = (DoctorC) academia.getDoctor(doctorId);
		if (doctor != null) {
			CollectionIF<DoctorIF> students = doctor.getStudents();
			IteratorIF<DoctorIF> studentsIterator = students.iterator();
			System.out.println(String.format("%d ha dirigido la(s) tesis de %s",
					doctorId, getStringForElementsIn(studentsIterator)));
		}
	}

	@Override
	public void getAncestors(AcademiaIF academia, int doctorId, int generations) {
		DoctorC doctor = (DoctorC) academia.getDoctor(doctorId);
		if (doctor != null) {
			CollectionIF<DoctorIF> supervisors = doctor.getAncestors(generations);
			IteratorIF<DoctorIF> supervisorsIterator = supervisors.iterator();
			System.out.println(String.format("Los ancestros de %d hasta %d generaciones son %s",
					doctorId, generations, getStringForElementsIn(supervisorsIterator)));
		}
	}

	@Override
	public void getDirectors(AcademiaIF academia, int doctorId) {
		DoctorC doctor = (DoctorC) academia.getDoctor(doctorId);
		if (doctor != null) {
			CollectionIF<DoctorIF> supervisors = doctor.getSupervisors();
			System.out.println(String.format("Tesis de %d codirigida por %s", doctorId,
					getStringForElementsIn(supervisors.iterator())));
		}
	}

	private String getStringForElementsIn(IteratorIF<DoctorIF> it) {
		final StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			DoctorC supervisor = (DoctorC) it.getNext();
			sb.append(supervisor.getId())
					.append(" ");
		}
		return sb.toString();
	}
}
