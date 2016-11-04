package com.colymore.uned.strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

class OutputFileStrategy implements PrintStrategy {

	private final File file;

	OutputFileStrategy(String outputFile) {
		file = new File(outputFile);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				//Empty
			}
		}
	}

	@Override
	public void resolve(String result) {
		try {
			OutputStream outputStream = new FileOutputStream(file);
			outputStream.write(result.getBytes(Charset.defaultCharset()));
		} catch (IOException e) {
			//empty
		}
	}
}
