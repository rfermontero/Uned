package com.colymore.uned;

class BigNumber implements Comparable<BigNumber> {

	private final static long MASK = 0xffffffffL;
	private int[] digits;

	private BigNumber(String number) {
		int length = number.length();
		digits = new int[length];
		for (int i = 0; i < length; i++) {
			digits[i] = Character.getNumericValue(number.charAt(i));
		}
	}

	private BigNumber(int[] digits) {
		this.digits = digits;
	}

	public BigNumber(long number) {
		this(String.valueOf(number));
	}

	public BigNumber plus(BigNumber other) {
		int[] resultPointer = new int[Math.max(digits.length, other.digits.length) + 1];
		int carry = 0;
		for (int i = (Math.max(digits.length, other.digits.length)) - 1; i >= 0; i--) {
			int left = digits.length > i ? digits[i] : 0;
			int right = other.digits.length > i ? other.digits[i] : 0;

			int result = left + right + carry;
			carry = result >= 10 ? 1 : 0;
			resultPointer[i + 1] = result <= 9 ? result : result - 10;
		}
		resultPointer[0] = carry;

		return new BigNumber(resultPointer);
	}

	public BigNumber multiply(BigNumber other) {
		int start = getLength() - 1;
		int yStart = other.getLength() - 1;

		int[] result = new int[digits.length + other.digits.length];

		long carry = 0;
		for (int j = yStart, k = yStart + 1 + start; j >= 0; j--, k--) {
			long product = (other.digits[j] & MASK) * (digits[start] & MASK) + carry;
			result[k] = (int) product;
			carry = product >>> 0x20;
		}

		result[start] = (int) carry;

		for (int i = start - 1; i >= 0; i--) {
			carry = 0;
			for (int j = yStart, k = yStart + 1 + i; j >= 0; j--, k--) {
				long product = (other.digits[j] & MASK) * (digits[i] & MASK) + (result[k] & MASK) + carry;
				result[k] = (int) product;
				carry = product >>> 0x20;
			}
			result[i] = (int) carry;
		}
		return new BigNumber(result);
	}

	private int getLength() {
		return digits.length;
	}

	@Override
	public String toString() {
		StringBuilder number = new StringBuilder();
		for (int digit : digits) {
			number.append(digit);
		}
		return number.toString();
	}

	@Override
	public int compareTo(BigNumber o) {
		int result;
		for (int i = 0; i < digits.length; i++) {
			result = digits[i] > o.digits[i] ? 1 : digits[i] == o.digits[i] ? 0 : -1;
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}

	public long toLong() {
		int sum = 0;
		int exponent = 0;
		for (int i = digits.length - 1; i >= 0; i--) {
			sum += Math.pow(10, exponent++) * digits[i];
		}
		return sum;
	}
}
