package com.rfernandez.uned.resolver;

import java.io.IOException;
import java.math.BigInteger;

import com.rfernandez.uned.print.PrintStrategy;

class KaratsubaPrinter extends Karatsuba {

	private final PrintStrategy printStrategy;

	KaratsubaPrinter(PrintStrategy printStrategy) {
		this.printStrategy = printStrategy;
	}

	@Override
	public BigInteger resolve(BigInteger left, BigInteger right) {
		final BigInteger result;
		try {
			printStrategy.trace(getTrace(left, right));
			result = super.resolve(left, right);
			printStrategy.trace("Result is: " + result.toString() + System.lineSeparator());
			return result;
		} catch (IOException e) {
			System.out.println("Trace output error.");
			throw new RuntimeException(e);
		}
	}

	private String getTrace(BigInteger left, BigInteger right) {
		return "Applying karatsuba algorithm for " + left.toString() + " and " + right.toString() + System.lineSeparator();
	}
}
