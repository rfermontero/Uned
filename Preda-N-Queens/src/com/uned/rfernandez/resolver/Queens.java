package com.uned.rfernandez.resolver;

import com.uned.rfernandez.Input;
import com.uned.rfernandez.print.Printer;

class Queens implements Resolver {

	private final Printer printer;
	private final Input input;
	private int[] solution;

	Queens(Printer printer, Input input) {
		this.printer = printer;
		this.input = input;
	}

	@Override
	public void resolve(int n) {
		this.solution = new int[n];
		resetSolutions();
		resolveHelper(0);
	}

	private void resolveHelper(int column) {
		if (column == solution.length) {
			printer.printResult(solution);
		} else {
			for (int row = 0; row < solution.length; row++) {
				if (isValid(column, row)) {
					solution[column] = row;
					if (input.isWithTraces()) {
						printer.accept(column, row);
					}
					resolveHelper(column + 1);
				} else {
					if (input.isWithTraces()) {
						printer.denied(column, row);
					}
				}
			}
		}
	}

	private boolean isValid(int column, int row) {
		for (int i = 0; i < column; i++) {
			if (solution[i] == row || solution[i] == row - (column - i) || solution[i] == row + (column - i)) {
				return false;
			}
		}
		return true;
	}

	private void resetSolutions() {
		for (int i = 0; i < solution.length; i++) {
			solution[i] = -1;
		}
	}
}
