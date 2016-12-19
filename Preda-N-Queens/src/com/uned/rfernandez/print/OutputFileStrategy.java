package com.uned.rfernandez.print;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import static java.lang.System.lineSeparator;

class OutputFileStrategy extends PrintFormatter implements Printer {

	File file;

	OutputFileStrategy(String outputFile) {
		file = new File(outputFile);
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException ignored) {

		}
	}

	@Override
	public void printResult(int[] result) {
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file, true);
			outputStream.write(getResult(result, false).getBytes(Charset.defaultCharset()));
			outputStream.write(lineSeparator().getBytes(Charset.defaultCharset()));
			outputStream.flush();
		} catch (IOException e) {
			//empty
		} finally {
			close(outputStream);
		}
	}

	@Override
	public void accept(int row, int column) {
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file, true);
			outputStream.write(String.format("Accept queen for row %d and column %d", row, column).getBytes(Charset.defaultCharset()));
			outputStream.write(lineSeparator().getBytes(Charset.defaultCharset()));
			outputStream.flush();
		} catch (IOException e) {
			//empty
		} finally {
			close(outputStream);
		}
	}

	@Override
	public void denied(int row, int column) {
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file, true);
			outputStream.write(String.format("Denied queen for row %d and column %d", row, column).getBytes(Charset.defaultCharset()));
			outputStream.write(lineSeparator().getBytes(Charset.defaultCharset()));
			outputStream.flush();
		} catch (IOException e) {
			//empty
		} finally {
			close(outputStream);
		}
	}

	void close(Closeable c) {
		if (c == null) return;
		try {
			c.close();
		} catch (IOException ignored) {
		}
	}
}
