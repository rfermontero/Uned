package com.colymore.uned;

import java.math.BigInteger;

import com.colymore.uned.Input.Builder;
import com.colymore.uned.strategy.PrintStrategy;
import com.colymore.uned.strategy.PrintStrategyFactory;

public class Main {

	public static void main(String[] args) {
		Input input = getInput(args);

		BigInteger result = new Karatsuba().resolve(input.getFirstInput(), input.getSecondInput());

		PrintStrategy printStrategy = PrintStrategyFactory.getResult(input);
		printStrategy.resolve(result.toString());
	}

	private static Input getInput(String[] args) {
		Builder builder = getBuilder(args);
		return builder.build();
	}

	private static Builder getBuilder(String[] args) {
		final Builder builder = new Builder();
		int argsLength = args.length;

		switch (argsLength) {
			case 0:
				throw new IllegalArgumentException("Need more arguments");
			case 1:
				builder.withInput(args[0]);
				break;
			case 2:
				parseForArguments(builder, args[0], args[1]);
				break;
			case 3:
				parseForArguments(builder, args[0], args[1], args[2]);
				break;
			case 4:
				parseForArguments(builder, args[0], args[1], args[2], args[3]);

		}
		return builder;
	}

	private static void parseForArguments(Builder builder, String... args) {
		if (args.length == 2) {
			if (hasHelp(args[0])) {
				builder.withHelp();
				return;
			}

			if (args[0].equals("-t")) {
				builder.withTraces();
				builder.withInput(args[1]);
				return;
			}

			builder.withInput(args[0]);
			builder.withOutput(args[1]);

		} else if (args.length == 3) {

			if (hasHelp(args[0])) {
				builder.withHelp();
				return;
			}

			if (args[0].equals("-t")) {
				builder.withTraces();
				builder.withInput(args[1]);
				builder.withOutput(args[2]);
				return;
			}

			throw new IllegalArgumentException("Bad arguments");
		} else if (args.length == 4) {

			if (hasHelp(args[0])) {
				builder.withHelp();
				return;
			}

			throw new IllegalArgumentException("Bad arguments");
		} else {
			throw new IllegalArgumentException("Bad arguments");
		}
	}

	private static boolean hasHelp(String arg) {
		return arg.equals("-h");
	}
}
