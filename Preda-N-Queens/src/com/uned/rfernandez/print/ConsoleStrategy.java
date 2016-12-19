package com.uned.rfernandez.print;

class ConsoleStrategy extends PrintFormatter implements Printer {

	@Override
	public void printResult(int[] result) {
		printConsole(getResult(result, false));
	}

	@Override
	public void accept(int row, int column) {
		printConsole(String.format("Accepted queen for row %d and column %d", row, column));
	}

	@Override
	public void denied(int row, int column) {
		printConsole(String.format("Denied queen for row %d and column %d", row, column));
	}

	private void printConsole(String trace) {
		System.out.println(trace);
	}
}
