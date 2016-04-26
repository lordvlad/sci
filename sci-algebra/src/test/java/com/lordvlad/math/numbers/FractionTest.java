package com.lordvlad.math.numbers;

import static org.junit.Assert.*;

import org.junit.Test;

public class FractionTest {

	@Test
	public void test() {
		Fraction a = Fraction.of(1, 2);
		Fraction b = Fraction.of(1, 3);
		assertEquals(Fraction.of(1, 6), Op.mul(a, b));
		assertEquals(Fraction.of(5, 6), Op.add(a, b));
		assertEquals(Fraction.of(3, 2), Op.add(a, Op.inv(b)));
		assertEquals(Fraction.of(3, 2), Op.div(a, b));
		assertEquals(Fraction.of(1, 3), new Fraction(2, 6));
		assertEquals(Fraction.of(1, 3), Fraction.of("1/3"));
		assertEquals(Fraction.of(1), Fraction.of("1"));
		assertEquals("1/3", Op.add(Fraction.of(5,6), Fraction.of("-1/2")).toString());
	}

}
