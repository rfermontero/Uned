package com.uned.rfernandez.print;

class GraphicConsoleStrategy extends ConsoleStrategy {

	@Override
	public void printResult(int[] result) {
		for (int row : result) {
			System.out.println("");
			for (int ignored : result) {
				System.out.print("----");
			}
			System.out.println("");

			for (int i = 0, resultLength = result.length; i < resultLength; i++) {
				String value = i == row ? "R" : i % 2 == 0 ? "*" : " ";
				System.out.print("| " + value + " ");
			}
			System.out.print("|");
		}
		System.out.println("");
		for (int ignored : result) {
			System.out.print("----");
		}
	}
}
