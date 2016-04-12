package es.uned.lsi.eped.pract2016;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by colymore on 10/4/16.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        assert args.length == 3;
        String scenario = args[0];
        String data = args[1];
        String operations = args[2];

        if (scenario.toUpperCase().equals("S")) {
            AcademiaIF academia = parseScenarioOne(new BufferedReader(new FileReader(new File(data))));
        }
    }

    private static AcademiaIF parseScenarioOne(BufferedReader fileData) throws IOException {
        BufferedReader br = new BufferedReader(fileData);
        String academiaFounderLine = br.readLine();
        int founderId = getFounderId(academiaFounderLine);
        if (founderId < 0) {
            System.out.println("Debe ser mayor que cero");
        } else {
            boolean finished = false;
            DoctorIF founder = new DoctorS(founderId);
            AcademiaIF academiaIF = new AcademiaS(founder);
            while (!finished) {
                String line = br.readLine();
                if (line != null) {
                    int doctorId = getNextStudient(line);
                    int supervisorId = getNextSupervisor(line);
                    academiaIF.addDoctor(new DoctorS(doctorId), new DoctorS(supervisorId));
                } else {
                    finished = true;
                }
            }
            return academiaIF;
        }
        return null;
    }

    private static int getNextStudient(String line) throws IOException {
        int nextId = -1;
        if (line != null) {
            line = line.replaceAll("[^-?0-9]+", " ");
            nextId = Integer.parseInt(Arrays.asList(line.trim().split(" ")).get(0));
        }
        return nextId;
    }

    private static int getNextSupervisor(String line) throws IOException {
        int nextId = -1;
        if (line != null) {
            line = line.replaceAll("[^-?0-9]+", " ");
            nextId = Integer.parseInt(Arrays.asList(line.trim().split(" ")).get(1));
        }
        return nextId;
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
