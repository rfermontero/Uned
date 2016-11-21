package com.rfernandez.uned.resolver;

import com.rfernandez.uned.Input;
import com.rfernandez.uned.print.PrintStrategy;

public class ResolverFactory {

	public static Resolver get(Input input, PrintStrategy printStrategy) {
		if (input.isWithTraces()) {
			return new KaratsubaPrinter(printStrategy);
		} else {
			return new Karatsuba();
		}
	}
}
