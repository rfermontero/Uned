package es.uned.lsi.eped.pract2016.parser;

import es.uned.lsi.eped.pract2016.OperationsParser;

public class ParserFactory {
	public static OperationsParser getParser(String scenario) {
		if (scenario.equals("S")) {
			return new OperationsMeasurerDecorator(new ParserSSceario());
		} else if (scenario.equals("C")) {
			return new OperationsMeasurerDecorator(new ParserCScenarario());
		}
		return null;
	}
}
