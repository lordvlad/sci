package com.lordvlad.units;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

public class SingleUnitFormatTest {

	SingleUnitFormat format;

	@Before 
	public void setup() {
		format = SingleUnitFormat.getInstance();
	}
	
	@Test
	public void test_parser() throws ParseException {
		assertEquals(SI.METRE, format.parseObject("m"));
		assertEquals(SI.SECOND, format.parseObject("s"));
		assertEquals(SI.SECOND, format.parseObject("\""));
	}
	
	@Test
	public void test_formatter() throws ParseException {
		assertEquals("m", format.format(SI.METRE));
		assertEquals("s", format.format(SI.SECOND));
		assertNotEquals("\"", format.format(SI.SECOND));
	}

}
