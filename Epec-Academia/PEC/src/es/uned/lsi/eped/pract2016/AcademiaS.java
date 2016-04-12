package es.uned.lsi.eped.pract2016;

import es.uned.lsi.eped.DataStructures.CollectionIF;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Set;
import es.uned.lsi.eped.DataStructures.SetIF;

public class AcademiaS implements AcademiaIF {

    private DoctorIF founder;

    public AcademiaS(DoctorIF founder) {
        this.founder = founder;
    }

    @Override
    public DoctorIF getFounder() {
        return founder;
    }

    @Override
    public DoctorIF getDoctor(int id) {
        return findDoctorInStudentsLis(founder, id);
    }

    @Override
    public int size() {
        return 0;
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
        IteratorIF<DoctorIF> studientsIt = doctor.getStudents().iterator();
        while (studientsIt.hasNext()) {
            current = current.union(getAll(studientsIt.getNext()));
        }
        return current;
    }

    @Override
    public void addDoctor(DoctorIF newDoctor, DoctorIF supervisor) {
        DoctorS supervisorDoctor = (DoctorS) getDoctor(supervisor);
        DoctorS studientDoctor = (DoctorS) getDoctor(newDoctor);
        if (supervisorDoctor != null && studientDoctor == null) {
            DoctorS newDoctorS = (DoctorS) newDoctor;
            newDoctorS.setSupervisor(supervisor);
            supervisorDoctor.addStudent(newDoctorS);
        }
    }

    @Override
    public void addSupervision(DoctorIF student, DoctorIF supervisor) {
        //
    }

    private DoctorIF getDoctor(DoctorIF e) {
        DoctorS doctor = (DoctorS) e;
        return findDoctorInStudentsLis(founder, doctor.getId());
    }

    private DoctorIF findDoctorInStudentsLis(DoctorIF doctor, int id) {
        if (doctor == null || doctor.equals(new DoctorS(id))) {
            return doctor;
        } else {
            CollectionIF<DoctorIF> studients = doctor.getStudents();
            if (studients == null || studients.isEmpty()) {
                return null;
            } else {
                IteratorIF<DoctorIF> studientsIt = studients.iterator();
                while (studientsIt.hasNext()) {
                    DoctorIF d = findDoctorInStudentsLis(studientsIt.getNext(), id);
                    if (d != null) {
                        return d;
                    }
                }
                return null;
            }
        }
    }
}
