package com.lordvlad.math.numbers;

import static org.junit.Assert.*;

import java.text.Format;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.lordvlad.math.numbers.NumberWithError;
import com.lordvlad.math.numbers.NumberWithErrorFormat;

public class NumberWithErrorFormatTest {

	private Format nf;

	@Before
	public void setup() {
		nf = NumberWithErrorFormat.getInstance();
	}
	
	@Test
	public void test_format() {
		assertEquals("100 \u00b1 0.01", nf.format(NumberWithError.of(100.0, 0.01)));
		assertEquals("100.04 \u00b1 0.01", nf.format(NumberWithError.of(100.04, 0.01)));
	}

	@Test 
	public void test_parse() throws ParseException {
		assertEquals(NumberWithError.of(100.0, 0.01), nf.parseObject("100 \u00b1 0.01"));
		assertEquals(NumberWithError.of(100.04, 0.01), nf.parseObject("100.04 \u00b1 0.01"));
		assertEquals(NumberWithError.of(2e10, 0.1), nf.parseObject("2E10 \u00b1 0.1"));
		assertEquals(NumberWithError.of(1.0, 1e-10), nf.parseObject("1 \u00b1 1E-10"));
		assertEquals(NumberWithError.of(2e7, 1e-10), nf.parseObject("2E7 \u00b1 1E-10"));
		assertEquals(Number.of(20000000L), nf.parseObject("2E7"));
	}
	
	@Test
	public void test_formattable() {
		assertEquals("this: 100 \u00b1 0.01", String.format("this: %s", NumberWithError.of(100.0, 0.01)));
		assertEquals("this: 100.04 \u00b1 0.01", String.format("this: %s", NumberWithError.of(100.04, 0.01)));
		assertEquals("this:        100.04 \u00b1 0.01", String.format("this: %20s", NumberWithError.of(100.04, 0.01)));
	}
}
