package com.rfernandez.uned.print;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import static java.lang.System.lineSeparator;

class OutputFileStrategy implements PrintStrategy {

	private OutputStream outputStream;

	OutputFileStrategy(String outputFile) {
		File file = new File(outputFile);
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
			outputStream = new FileOutputStream(file);
		} catch (IOException e) {
		}
	}

	@Override
	public void printResult(String result) throws IOException {
		try {
			if (outputStream != null) {
				outputStream.write(result.getBytes(Charset.defaultCharset()));
				outputStream.write(lineSeparator().getBytes(Charset.defaultCharset()));
				outputStream.flush();
			}
		} catch (IOException e) {
			//empty
		} finally {
			if (outputStream != null) {
				outputStream.close();
				outputStream = null;
			}
		}

	}

	@Override
	public void trace(String trace) throws IOException {
		if (outputStream != null) {
			outputStream.write(trace.getBytes(Charset.defaultCharset()));
		}
	}

	@Override
	public void close() throws IOException {
		if (outputStream != null) {
			outputStream.close();
		}
	}
}
