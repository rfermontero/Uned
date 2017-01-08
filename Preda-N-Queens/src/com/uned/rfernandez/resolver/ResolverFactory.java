package com.uned.rfernandez.resolver;


import com.uned.rfernandez.Input;
import com.uned.rfernandez.print.Printer;

public class ResolverFactory {

	public static Resolver get(Printer printer, Input input) {
			return new Queens(printer, input);
	}
}
