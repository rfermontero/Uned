package com.rfernandez.uned;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Input {

	private final BigInteger firstInput;
	private final BigInteger secondInput;
	private final boolean withTraces;
	private final boolean withHelp;
	private final String outputFile;

	private Input(String firstInput,
			String secondInput,
			boolean withTraces,
			boolean withHelp,
			String outputFile) {
		this.firstInput = new BigInteger(firstInput != null ? firstInput : "0", 10);
		this.secondInput = new BigInteger(secondInput != null ? secondInput : "0", 10);
		this.withTraces = withTraces;
		this.withHelp = withHelp;
		this.outputFile = outputFile;
	}

	BigInteger getFirstInput() {
		return firstInput;
	}

	BigInteger getSecondInput() {
		return secondInput;
	}

	public boolean isWithTraces() {
		return withTraces;
	}

	boolean isWithHelp() {
		return withHelp;
	}

	public Boolean hasOutputFile() {
		return outputFile != null;
	}

	public String getOutputFile() {
		return outputFile;
	}

	static class Builder {

		private String inputFile;
		private String outputFile;
		private boolean withHelp;
		private boolean withTraces;

		Builder() {
		}

		Builder withInput(String file) {
			this.inputFile = file;
			return this;
		}

		Builder withOutput(String file) {
			this.outputFile = file;
			return this;
		}

		Builder withHelp() {
			this.withHelp = true;
			return this;
		}

		Builder withTraces() {
			this.withTraces = true;
			return this;
		}

		Input build() {
			try {
				if (withHelp) {
					return new Input(null, null, false, true, null);
				} else {
					if (inputFile == null) {
						throw new IllegalArgumentException("Input file should exists");
					} else {
						String firstNumber = parseFirstNumber(inputFile);
						String secondNumber = parseSecondNumber(inputFile);
						return new Input(firstNumber, secondNumber, withTraces, false, outputFile);
					}
				}
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException("Input file should exists");
			}
		}

		private String parseSecondNumber(String inputFile) throws FileNotFoundException {
			File file = getExistentFile(inputFile);
			Scanner data = new Scanner(file);
			return getValue(data, 1);
		}

		private String parseFirstNumber(String inputFile) throws FileNotFoundException {
			File file = getExistentFile(inputFile);
			Scanner data = new Scanner(file);
			return getValue(data, 0);
		}

		private String getValue(Scanner scanner, int position) {
			String text = scanner.useDelimiter("\\A").next();
			scanner.close();
			return text.split(" |\n")[position];
		}

		private File getExistentFile(String inputFile) {
			File file = new File(inputFile);
			if (!file.exists()) {
				throw new IllegalArgumentException("Input file should exists");
			}
			return file;
		}
	}
}
