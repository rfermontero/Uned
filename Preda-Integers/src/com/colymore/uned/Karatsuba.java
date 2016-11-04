package com.colymore.uned;

import java.math.BigInteger;

class Karatsuba {

	BigInteger resolve(String leftS, String rightS) {
		BigInteger left = new BigInteger(leftS);
		BigInteger right = new BigInteger(rightS);

		int digits = Math.max(leftS.length(), rightS.length());

		if (left.compareTo(new BigInteger("10", 10)) == -1 || right.compareTo(new BigInteger("10", 10)) == -1) {
			return left.multiply(right);
		}

		BigInteger leftFirstHalf = left.divide(BigInteger.valueOf((long) Math.pow(10, digits / 2)));
		BigInteger leftSecondHalf = left.mod(BigInteger.valueOf((long) Math.pow(10, digits / 2)));

		BigInteger rightFirstHalf = right.divide(BigInteger.valueOf((long) Math.pow(10, digits / 2)));
		BigInteger rightSecondHalf = right.mod(BigInteger.valueOf((long) Math.pow(10, digits / 2)));

		BigInteger z2 = resolve(String.valueOf(leftFirstHalf), String.valueOf(rightFirstHalf));
		BigInteger z0 = resolve(String.valueOf(leftSecondHalf), String.valueOf(rightSecondHalf));
		BigInteger z1 = resolve(String.valueOf(leftFirstHalf.add(leftSecondHalf)), String.valueOf(rightFirstHalf.add(rightSecondHalf)));

		return (z2.multiply(BigInteger.valueOf((long) Math.pow(10, Math.floor(digits / 2d) * 2))))
				.add((z1.subtract(z0).subtract(z2)).multiply(BigInteger.valueOf((long) Math.pow(10, Math.floor(digits / 2))).add(z0)));
	}
}
