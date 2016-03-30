package com.lordvlad.units;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lordvlad.utils.Constants;

import static com.lordvlad.units.SI.*;
import static com.lordvlad.units.MetricScale.*;

public class SITest {

	@Test
	public void test() {
		assertTrue(KILO(METRE).getBaseUnit().equals(METRE));
		assertTrue(KILO(METRE).isCompatible(MILLI(METRE)));
		assertEquals(1000.0, KILO(METRE).toBaseUnit().call(1.0), Constants.EPSILON);
		assertEquals(1234.567, KILO(METRE).toBaseUnit().call(1.234567), Constants.EPSILON);
		assertEquals(1100.0 * 1100.0, KILO(METRE).pow(2).toBaseUnit().call(1.21), Constants.EPSILON);
		assertEquals(1100.0 * 1100.0, KILO(METRE).pow(2).toBaseUnit().call(1.21), Constants.EPSILON);
		assertTrue(METRE.over(SECOND.pow(2)).isCompatible(KILO(METRE).over(HOUR.pow(2))));
		assertEquals(10.0 * (1000.0 / 3600), KILO(METRE).over(HOUR).toBaseUnit().call(10.0), Constants.EPSILON);
		assertEquals(10.0 * (1000.0 / (3600*3600)), KILO(METRE).over(HOUR.pow(2)).toBaseUnit().call(10.0), Constants.EPSILON);
		assertEquals(10.0 * (1000.0 / (3600*3600)), KILO(METRE).over(HOUR.pow(2)).to(METRE.over(SECOND.pow(2))).call(10.0), Constants.EPSILON);
		assertEquals(10.0 * ((3600*3600) / 1000.0), METRE.over(SECOND.pow(2)).to(KILO(METRE).over(HOUR.pow(2))).call(10.0), Constants.EPSILON);
		
	}

}
