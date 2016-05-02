package es.uned.lsi.eped.pract2016.parser;

import es.uned.lsi.eped.pract2016.AcademiaIF;
import es.uned.lsi.eped.pract2016.OperationsParser;

public class OperationsMeasurerDecorator implements OperationsParser {

	private static final int OPERATIONS = 1;

	private final OperationsParser decorated;

	public OperationsMeasurerDecorator(OperationsParser decorated) {
		this.decorated = decorated;
	}

	@Override
	public void getSiblings(AcademiaIF academia, int doctorId) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < OPERATIONS; i++) {
			decorated.getSiblings(academia, doctorId);
		}
		getTime(startTime);

	}

	@Override
	public void getDescendants(AcademiaIF academia, int doctorId, int generations) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < OPERATIONS; i++) {
			decorated.getDescendants(academia, doctorId, generations);
		}
		getTime(startTime);
	}

	@Override
	public void getStudents(AcademiaIF academia, int doctorId) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < OPERATIONS; i++) {
			decorated.getStudents(academia, doctorId);
		}
		getTime(startTime);
	}

	@Override
	public void getAncestors(AcademiaIF academia, int doctorId, int generations) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < OPERATIONS; i++) {
			decorated.getAncestors(academia, doctorId, generations);
		}
		getTime(startTime);
	}

	@Override
	public void getDirectors(AcademiaIF academia, int doctorId) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < OPERATIONS; i++) {
			decorated.getDirectors(academia, doctorId);
		}
		getTime(startTime);
	}

	private void getTime(long time) {
		System.out.println("- Tiempo: " + (System.currentTimeMillis() - time));
	}

}
