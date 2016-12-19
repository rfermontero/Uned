package com.uned.rfernandez.print;

class PrintFormatter {

	private static final String ALPH = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private int counter;

	String getResult(int[] result, boolean graphic) {
		if (graphic) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("%d: ", counter++));
			for (int i = 0; i < result.length; i++) {
				sb.append(getLetter(i).concat(String.valueOf(result[i])).concat(" "));
			}

			return sb.toString();
		}
	}

	private String getLetter(int position) {
		return ALPH.substring(position, position + 1);
	}

}
