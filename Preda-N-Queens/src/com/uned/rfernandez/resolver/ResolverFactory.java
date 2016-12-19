package com.uned.rfernandez.resolver;


import com.uned.rfernandez.Input;
import com.uned.rfernandez.print.Printer;

public class ResolverFactory {

	public static Resolver get(Input input, Printer printer) {
		if (input.isWithTraces()) {
			return new Queens(printer);
		} else {
			return new Queens();
		}
	}
}
