package com.rfernandez.uned.print;

class ConsoleStrategy implements PrintStrategy {

	@Override
	public void printResult(String result) {
		printConsole(result);
	}

	@Override
	public void trace(String trace) {
		printConsole(trace);
	}

	@Override
	public void close() {
		//empty
	}

	private void printConsole(String trace) {
		System.out.println(trace);
	}
}
