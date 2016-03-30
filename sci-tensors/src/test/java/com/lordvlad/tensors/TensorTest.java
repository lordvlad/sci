package com.lordvlad.tensors;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import com.lordvlad.math.numbers.BigDecimal;


public class TensorTest {

	private static final int[] sizes = new int[] { 2, 10, 100, 1000 };
	private static final Random RND = new Random();

	@Test
	public void test_construct() {
		Tensor<BigDecimal> x = new Tensor<BigDecimal>(BigDecimal.class, 2, 2);

		assertEquals(4, x.size());
		assertEquals(2, x.dimensions());
		assertArrayEquals(new int[] { 2, 2 }, x.shape());
		assertEquals(null, x.get(0, 0));
		assertEquals(null, x.get(1, 0));
		assertEquals(null, x.get(0, 1));
		assertEquals(null, x.get(1, 1));
		assertEquals(BigDecimal.of(1), x.set(BigDecimal.of(1), 0, 0));
		assertEquals(BigDecimal.of(2), x.set(BigDecimal.of(2), 0, 1));
		assertEquals(BigDecimal.of(3), x.set(BigDecimal.of(3), 1, 0));
		assertEquals(BigDecimal.of(4), x.set(BigDecimal.of(4), 1, 1));
	}

	@Test
	public void test_times() {
		for (int s : sizes) {
			System.gc();
			Tensor<BigDecimal> x = new Tensor<BigDecimal>(BigDecimal.class, s, s);
			Tensor<BigDecimal> y = new Tensor<BigDecimal>(BigDecimal.class, s, s);

			for (int i = 0; i < s; i++) {
				for (int j = 0; j < s; j++) {
					x.set(randomBigDecimal(), i, j);
					y.set(randomBigDecimal(), i, j);
				}
			}

			long startTime = System.currentTimeMillis();
			Tensor<BigDecimal> z = x.times(y);
			long finishTime = System.currentTimeMillis();
			System.out.printf("Multiplication of an big decimal tensor (%dx%d) took %dms%n", s, s, finishTime - startTime);

			assertEquals(s * s, z.size());
			assertEquals(2, z.dimensions());
			assertArrayEquals(new int[] { s, s }, z.shape());

			for (int i = 0; i < s; i++) {
				for (int j = 0; j < s; j++) {
					assertTrue(x.get(i,j).times(y.get(i,j)).equals(z.get(i,j)));
				}
			}
		}
	}

	/**
	 * @param r
	 * @return
	 */
	private static BigDecimal randomBigDecimal() {
		return BigDecimal.of(RND.nextDouble());
	}
}
