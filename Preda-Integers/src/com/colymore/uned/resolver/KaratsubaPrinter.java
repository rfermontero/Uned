package com.colymore.uned.resolver;

import java.io.IOException;

import com.colymore.uned.BigNumber;
import com.colymore.uned.print.PrintStrategy;

class KaratsubaPrinter extends Karatsuba {

	private final PrintStrategy printStrategy;

	KaratsubaPrinter(PrintStrategy printStrategy) {
		this.printStrategy = printStrategy;
	}

	@Override
	public BigNumber resolve(BigNumber left, BigNumber right) {
		final BigNumber result;
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

	private String getTrace(BigNumber left, BigNumber right) {
		return "Applying karatsuba algorithm for " + left.toString() + " and " + right.toString() + System.lineSeparator();
	}
}
