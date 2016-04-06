package com.lordvlad.units;

import static org.junit.Assert.*;
import static com.lordvlad.units.SI.*;
import static com.lordvlad.units.MetricScale.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

public class CompositeUnitFormatTest {


	private static final String[] UNITS = { "mL/h", "°C", "mOsm/kg", "mL", "nm", "nm/(g/L)", "h", "µm", "mL/h^2", "1/(gDW/L)", "mmol",
			"g/L", "mmol/L", "mmol/h", "1/h", "g/mL", "g",
			// "%",
			"mL/h²", "rpm", "gDW/L", "10⁵ cells/mL", "10^5 cells/mL", "L/h", "L*h", "s^2",
			// "mg/L",
			// "g/mL"
	};
	
	private static final Unit<?> METER_PER_SQ_SECOND = METRE.over(SECOND.pow(2));
	private static final Unit<?> SQ_METER = METRE.pow(2);
	UnitFormat format;

	@Before
	public void setup() {
		format = CompositeUnitFormat.getInstance(SI.getInstance());
	}

	@Test
	public void test_parser() throws ParseException {
		assertEquals(METRE, format.parseObject("m"));
		assertEquals(SQ_METER, format.parseObject("m^2"));
		assertEquals(METER_PER_SQ_SECOND, format.parseObject("m*s^-2"));
		assertEquals(METER_PER_SQ_SECOND, format.parseObject("m/(s^2)"));
		assertEquals(MILLI(METRE), format.parseObject("mm"));
		assertEquals(NANO(MOLE), format.parseObject("nmol"));
		assertEquals(AMPERE.over(MICRO(SECOND)), format.parseObject("A/µs"));

		
		for (String u:UNITS) {
			assertNotNull(format.parseObject(u));
		}
	}

	@Test
	public void test_formatter() throws ParseException {
		assertEquals("m", format.format(METRE));
		assertEquals("m^2", format.format(SQ_METER));
		assertEquals("m*s^-2", format.format(METER_PER_SQ_SECOND));
		assertEquals("kg", format.format(SI.KILOGRAM));
	}

}
