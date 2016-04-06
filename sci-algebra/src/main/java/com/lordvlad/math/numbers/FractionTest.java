package com.lordvlad.math.numbers;

import static org.junit.Assert.*;

import org.junit.Test;

public class FractionTest {

	@Test
	public void test() {
		Fraction a = new Fraction(1, 2);
		Fraction b = new Fraction(1, 3);
		assertEquals(new Fraction(1, 6), a.times(b));
		assertEquals(new Fraction(5, 6), a.plus(b));
		assertEquals(new Fraction(3, 2), a.times(b.inverse()));
		assertEquals(new Fraction(3, 2), a.over(b));
		assertEquals(new Fraction(1, 3), Fraction.reduce(new Fraction(2, 6)));
		assertEquals(new Fraction(1, 3), Fraction.of("1/3"));
		assertEquals(Fraction.of(1), Fraction.of("1"));
		assertEquals("1/3", Fraction.reduce(Fraction.of(5,6).plus(Fraction.of("-1/2"))).toString());
	}

}
