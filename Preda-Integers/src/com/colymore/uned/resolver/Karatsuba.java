package com.colymore.uned.resolver;

import com.colymore.uned.BigNumber;

class Karatsuba implements Resolver {

	private final BigNumber baseComparator;

	Karatsuba() {
		baseComparator = new BigNumber("10");
	}

	public BigNumber resolve(BigNumber left, BigNumber right) {

		int size1 = left.getLength();
		int size2 = right.getLength();
		int number = Math.max(size1, size2);
		if (number < 10) {
			return left.multiply(right);
		}
		number = (number / 2) + (number % 2);
		long m = (long) Math.pow(10, number);

		long b = left.toLong() / m;
		long a = left.toLong() - (b * m);
		long d = right.toLong() / m;
		long c = right.toLong() - (d * number);

		BigNumber z0 = resolve(new BigNumber(a), new BigNumber(c));
		BigNumber z1 = resolve(new BigNumber(a + b), new BigNumber(c + d));
		BigNumber z2 = resolve(new BigNumber(b), new BigNumber(d));

		return new BigNumber(z0.toLong() + ((z1.toLong() - z0.toLong() - z2.toLong()) * m) +
				(z2.toLong() * (long) (Math.pow(10, 2 * number))));
	}
}

