package com.rfernandez.uned.resolver;

import java.math.BigInteger;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KaratsubaTest {

	private static final String FIRST_NUMBER =
			"472847457767461616183930349575645352426273737373627282736738272636478458574638364743";
	private static final String SECOND_NUMBER =
			"13142635242626253673748586970695656453524123131425364758696968574634535423535475869457362618191726363727252424253738";

	@Test
	public void karatsubaShouldMultiply() {
		Karatsuba karatsuba = new Karatsuba();

		long time = System.currentTimeMillis();
		BigInteger result = karatsuba.resolve(new BigInteger(FIRST_NUMBER), new BigInteger(SECOND_NUMBER));
		System.out.println(System.currentTimeMillis() - time);
		time = System.currentTimeMillis();
		BigInteger bigIntegerResult = new BigInteger(FIRST_NUMBER).multiply(new BigInteger(SECOND_NUMBER));
		System.out.println(System.currentTimeMillis() - time);
		assertEquals(result.toString(), bigIntegerResult.toString());
	}

}