package es.uned.lsi.eped.pract2016;

import es.uned.lsi.eped.DataStructures.CollectionIF;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Set;
import es.uned.lsi.eped.DataStructures.SetIF;

public class DoctorC implements DoctorIF {

	private CollectionIF<DoctorIF> students;
	private CollectionIF<DoctorIF> supervisors;
	private int id;

	public DoctorC(int id) {
		this.supervisors = new Set<>();
		this.students = new Set<>();
		this.id = id;
	}

	DoctorC(DoctorIF supervisor, int id) {
		this.supervisors = new Set<>(supervisor);
		this.students = new Set<>();
		this.id = id;
	}

	DoctorC(SetIF<DoctorIF> students, int id) {
		this.supervisors = new Set<>();
		this.students = students;
		this.id = id;
		setSupervisors();
	}

	public CollectionIF<DoctorIF> getSupervisors() {
		return supervisors;
	}

	public int getId() {
		return id;
	}

	@Override
	public CollectionIF<DoctorIF> getAncestors(int generations) {
		if (generations <= 0) {
			throw new IllegalArgumentException("Generations number should be > 0");
		}
		boolean isLastGeneration = generations - 1 == 0;
		if (!isLastGeneration) {
			Set<DoctorIF> supervisorsOfCurrentGeneration = (Set<DoctorIF>) supervisors;
			return supervisorsOfCurrentGeneration.union(getParentAncestorOf(generations));
		} else {
			return supervisors;
		}
	}

	@Override
	public CollectionIF<DoctorIF> getStudents() {
		return students;
	}

	@Override
	public CollectionIF<DoctorIF> getDescendants(int generations) {
		if (generations <= 0) {
			throw new IllegalArgumentException("Generations number should be > 0");
		} else {
			SetIF<DoctorIF> descendants = new Set<>();
			if (generations > 1) {
				final IteratorIF studentsIterator = students.iterator();
				while (studentsIterator.hasNext()) {
					final DoctorIF parent = (DoctorIF) studentsIterator.getNext();
					SetIF<DoctorIF> parentDescendantsSet = (SetIF<DoctorIF>) parent.getDescendants(generations - 1);
					SetIF<DoctorIF> parentWithChildSet = new Set<>(parent).union(parentDescendantsSet);
					descendants = descendants.union(parentWithChildSet);
				}
				return descendants;
			} else {
				return getStudents();
			}
		}
	}

	@Override
	public CollectionIF<DoctorIF> getSiblings() {
		SetIF<DoctorIF> siblings = new Set<>();
		IteratorIF<DoctorIF> superVisorIterators = supervisors.iterator();
		DoctorIF supervisor;
		while (superVisorIterators.hasNext()) {
			supervisor = superVisorIterators.getNext();
			siblings = siblings.union((SetIF<DoctorIF>) supervisor.getStudents());
		}
		return siblings.difference(new Set<DoctorIF>(this));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DoctorC doctorC = (DoctorC) o;

		return id == doctorC.id;
	}

	@Override
	public int hashCode() {
		return id;
	}

	void setSupervisor(DoctorIF supervisor) {
		this.supervisors = new Set<>(supervisor).union((SetIF<DoctorIF>) supervisors);
	}

	void addStudent(DoctorIF student) {
		students = new Set<>(student).union((SetIF<DoctorIF>) students);
	}

	private SetIF<DoctorIF> getParentAncestorOf(int generations) {
		Set<DoctorIF> ancestors = new Set<>();
		IteratorIF<DoctorIF> superVisorIterators = supervisors.iterator();
		DoctorIF supervisor;
		while (superVisorIterators.hasNext()) {
			supervisor = superVisorIterators.getNext();
			ancestors = (Set<DoctorIF>) ancestors.union((SetIF<DoctorIF>) supervisor.getAncestors(generations - 1));
		}
		return ancestors;
	}

	private void setSupervisors() {
		IteratorIF<DoctorIF> it = this.students.iterator();
		while (it.hasNext()) {
			DoctorC student = (DoctorC) it.getNext();
			student.setSupervisor(this);
		}
	}

}