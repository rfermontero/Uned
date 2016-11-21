package com.rfernandez.uned.print;


import com.rfernandez.uned.Input;

public class PrintStrategyFactory {

	public static PrintStrategy getResult(Input input) {
		if (input.hasOutputFile()) {
			return new OutputFileStrategy(input.getOutputFile());
		} else {
			return new ConsoleStrategy();
		}
	}
}
