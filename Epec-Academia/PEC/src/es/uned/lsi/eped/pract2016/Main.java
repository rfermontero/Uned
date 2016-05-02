package es.uned.lsi.eped.pract2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.uned.lsi.eped.pract2016.parser.ParserFactory;

public class Main {

	public static void main(String[] args) throws IOException {

		assert args.length == 3;
		String scenario = args[0];
		String data = args[1];
		String operations = args[2];

		if (scenario.toUpperCase().equals("S")) {
			final BufferedReader dataBr = new BufferedReader(new FileReader(new File(data)));
			final BufferedReader operationBr = new BufferedReader(new FileReader(new File(operations)));
			AcademiaIF academia = parseScenarioOne(dataBr);
			System.out.println("Doctores en la Academia: " + academia.size() + ".");
			String line = operationBr.readLine();
			if (line != null) {
				do {
					parseOperation(academia, ParserFactory.getParser(scenario.toUpperCase()), line);
					line = operationBr.readLine();
				} while (line != null);
			}
		} else if (scenario.toUpperCase().equals("C")) {
			final BufferedReader dataBr = new BufferedReader(new FileReader(new File(data)));
			final BufferedReader operationBr = new BufferedReader(new FileReader(new File(operations)));
			AcademiaIF academia = parseScenarioTwo(dataBr);
			System.out.println("Doctores en la Academia: " + academia.size() + ".");
			String line = operationBr.readLine();
			if (line != null) {
				do {
					parseOperation(academia, ParserFactory.getParser(scenario), line);
					line = operationBr.readLine();
				} while (line != null);
			}
		}
	}

	private static AcademiaIF parseScenarioOne(BufferedReader dataBr) throws IOException {
		BufferedReader br = new BufferedReader(dataBr);
		String academiaFounderLine = br.readLine();
		int founderId = getFounderId(academiaFounderLine);
		if (founderId < 0) {
			System.out.println("Should be bigger than zero");
		} else {
			boolean finished = false;
			DoctorIF founder = new DoctorS(founderId);
			AcademiaIF academiaIF = new AcademiaS(founder);
			while (!finished) {
				String line = br.readLine();
				if (line != null) {
					int doctorId = getNextStudent(line);
					int supervisorId = getNextSupervisor(line);
					DoctorIF newDoctor = new DoctorS(doctorId);
					DoctorIF doctorSupervisor = new DoctorS(supervisorId);
					academiaIF.addDoctor(newDoctor, doctorSupervisor);
				} else {
					finished = true;
				}
			}
			return academiaIF;
		}
		return null;
	}

	private static void parseOperation(AcademiaIF academia, OperationsParser operationsParser, String line) {
		String operation = line.substring(0, 2);
		switch (operation) {
			case "SU":
				operationsParser.getDirectors(academia, getNumberInLine(line, 0));
				break;
			case "AN":
				operationsParser.getAncestors(academia, getNumberInLine(line, 0), getNumberInLine(line, 1));
				break;
			case "ST":
				operationsParser.getStudents(academia, getNumberInLine(line, 0));
				break;
			case "DE":
				operationsParser.getDescendants(academia, getNumberInLine(line, 0), getNumberInLine(line, 1));
				break;
			case "SI":
				operationsParser.getSiblings(academia, getNumberInLine(line, 0));
				break;
		}
	}


	private static AcademiaIF parseScenarioTwo(BufferedReader fileData) throws IOException {
		BufferedReader br = new BufferedReader(fileData);
		String academiaFounderLine = br.readLine();
		int founderId = getFounderId(academiaFounderLine);
		if (founderId < 0) {
			System.out.println("Should be bigger than zero");
		} else {
			boolean finished = false;
			DoctorIF founder = new DoctorC(founderId);
			AcademiaIF academiaIF = new AcademiaC(founder);
			while (!finished) {
				String line = br.readLine();
				if (line != null) {
					int doctorId = getNextStudent(line);
					List<Integer> supervisorIds = getNextSupervisors(line);
					DoctorC newDoctor = new DoctorC(doctorId);
					academiaIF.addDoctor(newDoctor, new DoctorC(supervisorIds.get(0)));
					for (int i = 1; i < supervisorIds.size(); i++) {
						academiaIF.addSupervision(newDoctor, new DoctorC(supervisorIds.get(i)));
					}
				} else {
					finished = true;
				}
			}
			return academiaIF;
		}
		return null;
	}

	private static List<Integer> getNextSupervisors(String line) {
		line = line.replaceAll("[^-?0-9]+", " ");
		List<String> supervisors = Arrays.asList(line.trim().split(" "));
		List<Integer> results = new ArrayList<>(supervisors.size() - 1);
		for (int i = 1; i < supervisors.size(); i++) {
			results.add(Integer.parseInt(supervisors.get(i)));
		}
		return results;
	}

	private static int getNextStudent(String line) throws IOException {
		int nextId = -1;
		if (line != null) {
			nextId = getNumberInLine(line, 0);
		}
		return nextId;
	}

	private static int getNextSupervisor(String line) throws IOException {
		int nextId = -1;
		if (line != null) {
			nextId = getNumberInLine(line, 1);
		}
		return nextId;
	}

	private static int getNumberInLine(String line, int position) {
		line = line.replaceAll("[^-?0-9]+", " ");
		return Integer.parseInt(Arrays.asList(line.trim().split(" ")).get(position));
	}

	private static int getFounderId(String academiaFounderLine) {
		Matcher matcher = Pattern.compile("\\d+").matcher(academiaFounderLine);
		if (matcher.find()) {
			return Integer.valueOf(matcher.group());
		} else {
			return -1;
		}
	}
}
