package es.uned.lsi.eped.pract2016;

import es.uned.lsi.eped.DataStructures.CollectionIF;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Set;
import es.uned.lsi.eped.DataStructures.SetIF;

public class AcademiaC implements AcademiaIF {

	private DoctorIF founder;

	public AcademiaC(DoctorIF founder) {
		this.founder = founder;
	}

	@Override
	public DoctorIF getFounder() {
		return founder;
	}

	@Override
	public DoctorIF getDoctor(int id) {
		return findDoctorInStudentsLis(getFounder(), id);
	}

	@Override
	public int size() {
		int counter = founder == null ? 0 : 1 + founder.getStudents().size();

		if (founder != null) {
			CollectionIF<DoctorIF> founderStudents = founder.getStudents();
			IteratorIF<DoctorIF> studentsIterator = founderStudents.iterator();
			while (studentsIterator.hasNext()) {
				counter += getNumberOfStudents(studentsIterator.getNext());
			}
		}
		return counter;
	}

	private int getNumberOfStudents(DoctorIF doctor) {
		int childs = doctor.getStudents().size();
		IteratorIF<DoctorIF> studentsIterator = doctor.getStudents().iterator();
		while (studentsIterator.hasNext()) {
			childs += getNumberOfStudents(studentsIterator.getNext());
		}
		return childs;
	}

	@Override
	public boolean isEmpty() {
		return founder == null;
	}

	@Override
	public boolean contains(DoctorIF e) {
		return getDoctor(e) != null;
	}

	@Override
	public void clear() {
		founder = null;
	}

	@Override
	public IteratorIF<DoctorIF> iterator() {
		SetIF<DoctorIF> all = getAll(founder);
		return all.iterator();
	}

	private SetIF<DoctorIF> getAll(DoctorIF doctor) {
		SetIF<DoctorIF> current = new Set<>(doctor);
		IteratorIF<DoctorIF> studentsIt = doctor.getStudents().iterator();
		while (studentsIt.hasNext()) {
			current = current.union(getAll(studentsIt.getNext()));
		}
		return current;
	}

	@Override
	public void addDoctor(DoctorIF newDoctor, DoctorIF supervisor) {
		DoctorC supervisorDoctor = (DoctorC) getDoctor(supervisor);
		DoctorC student = (DoctorC) getDoctor(newDoctor);
		if (supervisorDoctor != null && student == null) {
			DoctorC newDoctorC = (DoctorC) newDoctor;
			newDoctorC.setSupervisor(supervisorDoctor);
			supervisorDoctor.addStudent(newDoctorC);
		}
	}

	@Override
	public void addSupervision(DoctorIF student, DoctorIF newSupervisor) {
		DoctorC supervisorDoctor = (DoctorC) getDoctor(newSupervisor);
		DoctorC studentDoctor = (DoctorC) getDoctor(student);
		if (supervisorDoctor != null && student != null && canBeNewSupervisor(supervisorDoctor, studentDoctor)) {
			studentDoctor.setSupervisor(newSupervisor);
			supervisorDoctor.addStudent(studentDoctor);
		}
	}

	private boolean canBeNewSupervisor(DoctorC newSupervisor, DoctorC studentDoctor) {
		IteratorIF<DoctorIF> studentSupervisorsIt = studentDoctor.getSupervisors().iterator();
		while (studentSupervisorsIt.hasNext()) {
			DoctorIF supervisor = studentSupervisorsIt.getNext();
			if (supervisor != null && supervisor.equals(newSupervisor)) {
				return false;
			}
		}
		return true;
	}

	private DoctorIF findDoctorInStudentsLis(DoctorIF doctor, int id) {
		if (doctor == null || doctor.equals(new DoctorC(id))) {
			return doctor;
		} else {
			CollectionIF<DoctorIF> students = doctor.getStudents();
			if (students == null || students.isEmpty()) {
				return null;
			} else {
				IteratorIF<DoctorIF> studentsIt = students.iterator();
				while (studentsIt.hasNext()) {
					DoctorIF d = findDoctorInStudentsLis(studentsIt.getNext(), id);
					if (d != null) {
						return d;
					}
				}
				return null;
			}
		}
	}

	private DoctorIF getDoctor(DoctorIF e) {
		DoctorC doctor = (DoctorC) e;
		return findDoctorInStudentsLis(founder, doctor.getId());
	}
}
