package com.lordvlad.units;

import static org.junit.Assert.*;
import static com.lordvlad.units.SI.*;
import static com.lordvlad.units.ExtendedSI.*;
import static com.lordvlad.units.MetricScale.*;

import java.text.ParseException;

import org.junit.Test;

public class CompositeUnitFormatTest {

	UnitFormat format = CompositeUnitFormat.getInstance(SI.class, ExtendedSI.class, Bio.class);
	
	private static final String[] UNITS = { "mL/h", "°C", "mL", "nm", "nm/(g/L)", "h", "µm", "mL/h^2",
			"1/(gDW/L)", "mmol", "g/L", "mmol/L", "mmol/h", "1/h", "g/mL", "g", "%", "mL/h²", "rpm", "gDW/L",
			"10⁵ cells/mL", "10^5 cells/mL", "L/h", "L*h", "s^2", "mg/L", "g/mL", "mOsm/kg" };


	@Test
	public void test_parser() throws ParseException {
		assertEquals(METRE, format.parseObject("m"));
		assertEquals(SQUARE_METER, format.parseObject("m^2"));
		assertEquals(METER_PER_SQ_SECOND, format.parseObject("m*s^-2"));
		assertEquals(METER_PER_SQ_SECOND, format.parseObject("m/(s^2)"));
		assertEquals(MILLI(METRE), format.parseObject("mm"));
		assertEquals(NANO(MOLE), format.parseObject("nmol"));
		assertEquals(AMPERE.over(MICRO(SECOND)), format.parseObject("A/µs"));
		assertEquals(MILLI(LITRE).over(HOUR), format.parseObject("mL/h"));
		assertEquals(CELSIUS, format.parseObject("°C"));

		for (String u : UNITS) {
			try {
				assertNotNull("Could not parse " + u, format.parseObject(u));
			} catch (ParseException e) {
				fail("Could not parse " + u);
			}
		}
	}

	@Test
	public void test_formatter() throws ParseException {
		assertEquals("m", format.format(METRE));
		assertEquals("m^2", format.format(SQUARE_METER));
		assertEquals("m*s^-2", format.format(METER_PER_SQ_SECOND));
		assertEquals("kg", format.format(KILOGRAM));
		assertEquals("°C", format.format(CELSIUS));
	}

}
