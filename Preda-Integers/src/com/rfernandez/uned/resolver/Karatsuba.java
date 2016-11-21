package com.rfernandez.uned.resolver;

import java.math.BigInteger;

class Karatsuba implements Resolver {

	public BigInteger resolve(BigInteger left, BigInteger right) {
		int digits = Math.max(left.bitLength(), left.bitLength());
		if (digits <= 2)
			return left.multiply(right);
		digits = digits / 2;
		BigInteger firstLeft = left.shiftRight(digits);
		BigInteger secondLeft = left.subtract(firstLeft.shiftLeft(digits));
		BigInteger firstRight = right.shiftRight(digits);
		BigInteger secondRight = right.subtract(firstRight.shiftLeft(digits));

		BigInteger z2 = resolve(firstLeft, firstRight);
		BigInteger z0 = resolve(secondLeft, secondRight);
		BigInteger z1 = resolve(firstLeft.add(secondLeft),
				firstRight.add(secondRight))
				.subtract(z2)
				.subtract(z0);

		return z2.shiftLeft(2 * digits).add(z1.shiftLeft(digits)).add(z0);
	}

}

