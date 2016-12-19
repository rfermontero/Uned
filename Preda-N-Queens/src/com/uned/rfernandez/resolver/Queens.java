package com.uned.rfernandez.resolver;

import java.util.Optional;

import com.uned.rfernandez.print.Printer;

class Queens implements Resolver {

	private Optional<Printer> printer = Optional.empty();
	private int[] solution;

	Queens(Printer printer) {
		this.printer = Optional.ofNullable(printer);
	}

	Queens() {
	}

	@Override
	public void resolve(int n) {
		this.solution = new int[n];
		resetSolutions();
		resolveHelper(0);
	}

	private void resolveHelper(int column) {
		if (column == solution.length) {
			printer.ifPresent(printer -> printer.printResult(solution));
		} else {
			for (int row = 0; row < solution.length; row++) {
				int finalRow = row;
				if (isValid(column, row)) {
					solution[column] = row;
					printer.ifPresent(printer -> printer.accept(column, finalRow));
					resolveHelper(column + 1);
				} else {
					printer.ifPresent(printer -> printer.denied(column, finalRow));
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
