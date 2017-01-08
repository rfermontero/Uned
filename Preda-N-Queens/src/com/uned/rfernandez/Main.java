package com.uned.rfernandez;

import java.io.IOException;

import com.uned.rfernandez.Input.Builder;
import com.uned.rfernandez.print.Printer;
import com.uned.rfernandez.print.PrintStrategyFactory;
import com.uned.rfernandez.resolver.Resolver;
import com.uned.rfernandez.resolver.ResolverFactory;


public class Main {

	public static void main(String[] args) throws IOException {
		Input input = getInput(args);
		if (input.isWithHelp()) {
			printHelp();
		} else {
			Printer printer = PrintStrategyFactory.getResult(input);
			Resolver resolver = ResolverFactory.get(printer, input);
			resolver.resolve(input.getNumber());
		}
	}

	private static void printHelp() {
		String help = "SINTAXIS:\n" +
				"reinas [-h][-t][-g] N [fichero_salida]\n" +
				"-t Traza\n" +
				"-g Modo gráfico\n" +
				"-h Muestra esta ayuda\n" +
				"N Tamaño del tablero y número de reinas. fichero_salida Nombre del fichero de salida";

		System.out.println(help);
	}

	private static Input getInput(String[] args) {
		Builder builder = getBuilder(args);
		return builder.build();
	}

	private static Builder getBuilder(String[] args) {
		int argsLength = args.length;
		Builder builder = null;
		switch (argsLength) {
			case 0:
				throw new IllegalArgumentException("Need more arguments");
			case 1:
				if (hasHelp(args[0])) {
					builder = new Builder(0);
					builder.withHelp();
				} else {
					builder = new Builder(Integer.valueOf(args[0]));
				}

				break;
			case 2:
				builder = parseForArguments(args[0], args[1]);
				break;
			case 3:
				builder = parseForArguments(args[0], args[1], args[2]);
				break;
			case 4:
				builder = parseForArguments(args[0], args[1], args[2], args[3]);

		}
		return builder;
	}

	private static Builder parseForArguments(String... args) {
		Builder builder;
		if (args.length == 2) {
			if (hasHelp(args[0])) {
				builder = new Builder(0);
				builder.withHelp();
				return builder;
			}

			if (args[0].equals("-t")) {
				builder = new Builder(Integer.parseInt(args[1]));
				builder.withTraces();
				return builder;
			}

			if (args[0].equals("-g")) {
				builder = new Builder(Integer.parseInt(args[1]));
				builder.withGraphicMode();
				return builder;
			}

		} else if (args.length == 3) {

			if (hasHelp(args[0])) {
				builder = new Builder(0);
				builder.withHelp();
				return builder;
			}

			if (args[0].equals("-t")) {
				if (args[1].equals("-g")) {
					builder = new Builder(Integer.parseInt(args[2]));
					builder.withTraces();
					builder.withGraphicMode();
					return builder;
				}
			} else if ((args[0].equals("-g"))) {
				builder = new Builder(Integer.parseInt(args[1]));
				builder.withTraces();
				builder.withOutput(args[2]);
				return builder;
			}

			throw new IllegalArgumentException("Bad arguments");
		} else if (args.length == 4) {

			if (hasHelp(args[0])) {
				builder = new Builder(0);
				builder.withHelp();
				return builder;
			}

			builder = new Builder(Integer.parseInt(args[2]));
			builder.withTraces();
			builder.withGraphicMode();
			builder.withOutput(args[3]);
			return builder;
		} else {
			throw new IllegalArgumentException("Bad arguments");
		}
		throw new IllegalArgumentException("Bad arguments");
	}


	private static boolean hasHelp(String arg) {
		return arg.equals("-h");
	}
}
