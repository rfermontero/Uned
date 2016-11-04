package com.colymore.uned.strategy;


import com.colymore.uned.Input;

public class PrintStrategyFactory {

	public static PrintStrategy getResult(Input input) {
		if (input.hasOutputFile()) {
			return new OutputFileStrategy(input.getOutputFile());
		} else {
			return new ConsoleStrategy();
		}
	}
}
