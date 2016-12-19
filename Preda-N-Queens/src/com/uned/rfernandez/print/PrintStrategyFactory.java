package com.uned.rfernandez.print;


import com.uned.rfernandez.Input;

public class PrintStrategyFactory {

	public static Printer getResult(Input input) {
		if (input.hasOutputFile()) {
			if (input.isWithGraphics()) {
				return new GraphicOutputFileStrategy(input.getOutputFile());
			} else {
				return new OutputFileStrategy(input.getOutputFile());
			}
		} else {
			if (input.isWithGraphics()) {
				return new GraphicConsoleStrategy();
			} else {
				return new ConsoleStrategy();
			}
		}
	}
}
