package es.uned.lsi.eped.pract2016;

public class ParserFactory {
    static OperationsParser getParser(String scenario) {
        if (scenario.equals("S")) {
            return new OperationsParserCScenario();
        } else {
            return null;
        }
    }
}
