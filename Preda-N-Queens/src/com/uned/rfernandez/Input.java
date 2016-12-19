package com.uned.rfernandez;

public class Input {

	private final int number;
	private final boolean withTraces;
	private final boolean withHelp;
	private final boolean withGraphics;
	private final String outputFile;

	private Input(int number,
			boolean withTraces,
			boolean withHelp,
			boolean withGraphics,
			String outputFile) {
		this.number = number;
		this.withTraces = withTraces;
		this.withHelp = withHelp;
		this.withGraphics = withGraphics;
		this.outputFile = outputFile;
	}

	int getNumber() {
		return number;
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

	public boolean isWithGraphics() {
		return withGraphics;
	}

	static class Builder {

		private int number;
		private String outputFile;
		private boolean graphicMode;
		private boolean withHelp;
		private boolean withTraces;

		Builder(int number) {
			this.number = number;
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

		Builder withGraphicMode() {
			this.graphicMode = true;
			return this;
		}

		Input build() {
			return withHelp ? new Input(0, false, true, false, null) :
					new Input(number, withTraces, false,
							graphicMode, outputFile);
		}
	}
}
