package com.rfernandez.uned.print;

import java.io.IOException;

public interface PrintStrategy {
	void printResult(String result) throws IOException;

	void trace(String trace) throws IOException;

	void close() throws IOException;
}
