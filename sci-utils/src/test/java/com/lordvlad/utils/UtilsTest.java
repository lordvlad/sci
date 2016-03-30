package com.lordvlad.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lordvlad.utils.Utils;

public class UtilsTest {


	@Test
	public void test_firstNonNull() {
		assertEquals("yo", Utils.firstNonNull(null, null, "yo"));
		assertEquals(null, Utils.firstNonNull(null, null, null));
	}
	
	@Test
	public void test_equals() {
		assertTrue(Utils.equals(1.0, 1.0));
		assertTrue(Utils.equals(1.0000000001, 1.0000000002));
		assertTrue(Utils.equals(100L, 100L));
		assertTrue(Utils.equals(100, 100));
	}

}
