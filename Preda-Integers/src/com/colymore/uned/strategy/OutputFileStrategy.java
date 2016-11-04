package com.colymore.uned.strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

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
	public void resolve(long result) {
		try {
			OutputStream outputStream = new FileOutputStream(file);
			outputStream.write(longToBytes(result));
		} catch (IOException e) {
			//empty
		}
	}

	private byte[] longToBytes(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(8 / Byte.SIZE);
		buffer.putLong(x);
		return buffer.array();
	}
}
