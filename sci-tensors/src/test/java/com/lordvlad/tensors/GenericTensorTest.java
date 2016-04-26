package com.lordvlad.tensors;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import com.lordvlad.math.numbers.BigDec;



public class GenericTensorTest {

	private static final int[] sizes = new int[] { 2, 10, 100, 1000 };
	private static final Random RND = new Random();

	private static BigDec randomBigDec() {
		return BigDec.valueOf(RND.nextDouble());
	}
	
	@Test
	public void test_construct() {
		GenericTensor<BigDec> x = new GenericTensor<BigDec>(BigDec.class, 2, 2);

		assertEquals(4, x.size());
		assertEquals(2, x.dimensions());
		assertArrayEquals(new int[] { 2, 2 }, x.shape());
		assertEquals(null, x.get(0, 0));
		assertEquals(null, x.get(1, 0));
		assertEquals(null, x.get(0, 1));
		assertEquals(null, x.get(1, 1));
		assertEquals(BigDec.valueOf(1), x.set(BigDec.valueOf(1), 0, 0));
		assertEquals(BigDec.valueOf(2), x.set(BigDec.valueOf(2), 0, 1));
		assertEquals(BigDec.valueOf(3), x.set(BigDec.valueOf(3), 1, 0));
		assertEquals(BigDec.valueOf(4), x.set(BigDec.valueOf(4), 1, 1));
	}

	@Test
	public void test_hadamardProduct_BigDec() {
		for (int s : sizes) {
			System.gc();
			
			GenericTensor<BigDec> x = new GenericTensor<BigDec>(BigDec.class, s, s);
			GenericTensor<BigDec> y = new GenericTensor<BigDec>(BigDec.class, s, s);

			for (int i = 0; i < s; i++) {
				for (int j = 0; j < s; j++) {
					x.set(randomBigDec(), i, j);
					y.set(randomBigDec(), i, j);
				}
			}

			long startTime = System.currentTimeMillis();
			GenericTensor<BigDec> z = x.hadamardProduct(y);
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
			
			x = null;
			y = null;
			z = null;
			
			System.gc();
			
			BigDec[][] a = new BigDec[s][s];
			BigDec[][] b = new BigDec[s][s];
			
			for (int i = 0; i < s; i++) {
				for (int j = 0; j < s; j++) {
					a[i][j] = randomBigDec();
					b[i][j] = randomBigDec();
				}
			}
			
			startTime = System.currentTimeMillis();
			BigDec[][] c = new BigDec[s][s];
			for (int i= 0; i<s; i++) {
				for (int j = 0; j < s; j++) {
					c[i][j] = a[i][j].times(b[i][j]);
				}
			}
			finishTime = System.currentTimeMillis();
			System.out.printf("Multiplication of an array of arrays of big decimals (%dx%d) took %dms%n", s, s, finishTime - startTime);
		}
	}
}
