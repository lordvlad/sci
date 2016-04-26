package com.lordvlad.amounts;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.lordvlad.amounts.Amount;
import com.lordvlad.amounts.AmountFormat;
import com.lordvlad.units.SI;

public class AmountFormatTest {

	private AmountFormat af;

	@Before
	public void setup() {
		af = AmountFormat.getInstance();
	}
	
	@Test
	public void test_parse() throws ParseException {
		assertEquals(Amount.of(100.0, 0.1, SI.MOLE), af.parse("100 \u00b1 0.1 mol"));
		assertEquals(Amount.of(100, SI.MOLE), af.parse("100 mol"));
		assertEquals(Amount.of(4.3412, SI.METRE.over(SI.SECOND.pow(2))), af.parse("4.3412 m/s^2"));
		assertEquals(Amount.of(4.3412, 0.24, SI.METRE.over(SI.SECOND.pow(2))), af.parse("4.3412 \u00b1 0.24 m/s^2"));
	}

}
