package com.colymore.uned.resolver;

import com.colymore.uned.Input;
import com.colymore.uned.print.PrintStrategy;

public class ResolverFactory {

	public static Resolver get(Input input, PrintStrategy printStrategy) {
		if (input.isWithTraces()) {
			return new KaratsubaPrinter(printStrategy);
		} else {
			return new Karatsuba();
		}
	}
}
