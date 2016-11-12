package com.colymore.uned;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {

	private final BigNumber firstInput;
	private final BigNumber secondInput;
	private final boolean withTraces;
	private final boolean withHelp;
	private final String inputFile;
	private final String outputFile;

	private Input(BigNumber firstInput,
			BigNumber secondInput,
			boolean withTraces,
			boolean withHelp,
			String inputFile,
			String outputFile) {
		this.firstInput = firstInput;
		this.secondInput = secondInput;
		this.withTraces = withTraces;
		this.withHelp = withHelp;
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}

	BigNumber getFirstInput() {
		return firstInput;
	}

	BigNumber getSecondInput() {
		return secondInput;
	}

	public boolean isWithTraces() {
		return withTraces;
	}

	boolean hasHelp() {
		return withHelp;
	}

	public String getInputFile() {
		return inputFile;
	}

	public Boolean hasOutputFile() {
		return outputFile!=null;
	}

	public String getOutputFile() {
		return outputFile;
	}

	static class Builder {

		private String inputFile;
		private String outputFIle;
		private boolean withHelp;
		private boolean withTraces;

		Builder() {
		}

		Builder withInput(String file) {
			this.inputFile = file;
			return this;
		}

		Builder withOutput(String file) {
			this.outputFIle = file;
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
					return new Input(null, null, false, true, null, null);
				} else {
					if (inputFile == null) {
						throw new IllegalArgumentException("Input file should exists");
					} else {
						String firstNumber = parseFirstNumber(inputFile);
						String secondNumber = parseSecondNumber(inputFile);
						return new Input(new BigNumber(firstNumber),
								new BigNumber(secondNumber),
								withTraces,
								withHelp,
								inputFile, outputFIle);
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
