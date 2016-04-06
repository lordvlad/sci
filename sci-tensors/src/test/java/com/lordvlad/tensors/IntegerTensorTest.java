package com.lordvlad.tensors;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class IntegerTensorTest {

	private static final int[] sizes = new int[] { 10, 100, 1000, 5000 };

	@Test
	public void test_construct() {
		IntegerTensor x = new IntegerTensor(new int[4], 2, 2);

		assertEquals(4, x.size());
		assertEquals(2, x.dimensions());
		assertArrayEquals(new int[] { 2, 2 }, x.shape());
		assertEquals(0, x.get(0, 0));
		assertEquals(0, x.get(0, 0));
		assertEquals(0, x.get(1, 0));
		assertEquals(0, x.get(0, 1));
		assertEquals(0, x.get(1, 1));
		assertEquals(1, x.set(1, 0, 0));
		assertEquals(2, x.set(2, 1, 0));
		assertEquals(3, x.set(3, 0, 1));
		assertEquals(4, x.set(4, 1, 1));
	}

	@Test
	public void test_hadamardProduct() {
		Random r = new Random();
		for (int s : sizes) {
			System.gc();
			IntegerTensor x = new IntegerTensor(new int[s * s], s, s);
			IntegerTensor y = new IntegerTensor(new int[s * s], s, s);

			for (int i = 0; i < s * s; i++) {
				x.data[i] = r.nextInt();
				y.data[i] = r.nextInt();
			}

			long startTime = System.currentTimeMillis();
			IntegerTensor z = x.hadamardProduct(y);
			long finishTime = System.currentTimeMillis();
			System.out.printf("Multiplication of an integer tensor (%dx%d) took %dms%n", s, s, finishTime - startTime);

			assertEquals(s * s, z.size());
			assertEquals(2, z.dimensions());
			assertArrayEquals(new int[] { s, s }, z.shape());

			for (int i = 0; i < s * s; i++) {
				assertEquals(x.data[i] * y.data[i], z.data[i]);
			}
		}
	}
	
	public static void assertEquals(int a, Integer b) {
		assertTrue(a == b);
	}

}
