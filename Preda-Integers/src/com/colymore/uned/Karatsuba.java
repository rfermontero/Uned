package com.colymore.uned;

class Karatsuba {

	long resolve(String leftS, String rightS) {
		Long left = 0L;
		Long right = 0L;

		if (fillsInLong(leftS) && fillsInLong(rightS)) {
			left = Long.valueOf(leftS);
			right = Long.valueOf(rightS);
		}

		int digits = Math.max(getLength(left), getLength(right));

		if (left < 10 || right < 10) {
			return left * right;
		}

		long leftFirstHalf = (long) (left / Math.pow(10, digits / 2));
		long leftSecondHalf = (long) (left % Math.pow(10, digits / 2));

		long rightFirstHalf = (long) (right / Math.pow(10, digits / 2));
		long rightSecondHalf = (long) (right % Math.pow(10, digits / 2));

		double z2 = resolve(String.valueOf(leftFirstHalf), String.valueOf(rightFirstHalf));
		double z0 = resolve(String.valueOf(leftSecondHalf), String.valueOf(rightSecondHalf));
		double z1 = resolve(String.valueOf(leftFirstHalf + leftSecondHalf), String.valueOf(rightFirstHalf + rightSecondHalf));

		return (long) ((z2 * Math.pow(10, Math.floor(digits / 2d) * 2)) + ((z1 - z0 - z2) * Math.pow(10, Math.floor(digits / 2))) + z0);
	}

	private int getLength(Long l) {
		int counter = 1;
		while (l >= 10) {
			l /= 10;
			counter++;
		}
		return counter;
	}

	private static boolean fillsInLong(String number) {
		String longMax = String.valueOf(Long.MAX_VALUE);
		if (number.length() > longMax.length()) return false;
		if (number.length() < longMax.length()) return true;
		long a, b;
		for (int i = 1; i < number.length(); i++) {
			a = Long.parseLong(number.substring(0, i));
			b = Long.parseLong(longMax.substring(0, i));
			if (a > b) return false;
		}
		return Integer.parseInt(number.substring(number.length() - 1, number.length())) <= Integer.parseInt(longMax.substring(number.length() - 1, number.length()));
	}

}
