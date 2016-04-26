package com.lordvlad.amounts;

import static org.junit.Assert.*;

import org.junit.Test;


import com.lordvlad.math.numbers.Fraction;
import com.lordvlad.units.SI;
import com.lordvlad.units.Unit;

public class AmountTest {

	@Test
	public void testPlus()  {
		assertEquals(Amount.of(3, SI.METRE), Amount.of(1, SI.METRE).plus(Amount.of(2, SI.METRE)));
		assertEquals(Amount.of(Fraction.of(2, 2), SI.KELVIN),
				Amount.of(Fraction.of(1, 2), SI.KELVIN).plus(Amount.of(Fraction.of(1, 2), SI.KELVIN)));
		
		assertEquals(Amount.of(6, SI.METRE.pow(2)), Amount.of(3, SI.METRE).times(Amount.of(2,  SI.METRE)));
		assertEquals(Amount.of(2, Unit.ONE), Amount.of(6, SI.METRE).over(Amount.of(3, SI.METRE)));
	}

	@Test
	public void testOpposite() {
		assertEquals(Amount.of(-1, SI.SECOND), Amount.of(1, SI.SECOND).opposite());
	}

}
