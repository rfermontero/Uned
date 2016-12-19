package com.uned.rfernandez.print;

public interface Printer {
	void printResult(int[] result);

	void accept(int row, int column);

	void denied(int row, int column);
}
