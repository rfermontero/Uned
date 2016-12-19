package com.uned.rfernandez.print;

import java.io.FileOutputStream;
import java.io.IOException;

class GraphicOutputFileStrategy extends OutputFileStrategy {

	GraphicOutputFileStrategy(String outputFile) {
		super(outputFile);
	}

	@Override
	public void printResult(int[] result) {

		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file, true);
			for (int row : result) {
				outputStream.write(System.getProperty("line.separator").getBytes());
				for (int ignored : result) {
					outputStream.write("----".getBytes());
				}
				outputStream.write(System.getProperty("line.separator").getBytes());

				for (int i = 0, resultLength = result.length; i < resultLength; i++) {
					String value = i == row ? "R" : i % 2 == 0 ? "*" : " ";
					outputStream.write(("| " + value + " ").getBytes());
				}
				outputStream.write(("|").getBytes());
			}
			outputStream.write(System.getProperty("line.separator").getBytes());
			for (int ignored : result) {
				outputStream.write("----".getBytes());
			}
			outputStream.flush();
		} catch (IOException e) {
			//empty
		} finally {
			close(outputStream);
		}
	}

	@Override
	public void accept(int row, int column) {
		//super.accept(row, column);
	}

	@Override
	public void denied(int row, int column) {
		//super.denied(row, column);
	}
}
