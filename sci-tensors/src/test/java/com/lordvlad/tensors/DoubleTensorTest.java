package com.lordvlad.tensors;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DoubleTensorTest {

	private static final double EPSILON = 1e-8;
	private static final int[] sizes = new int[] { 10, 100, 1000, 5000 };

	@Test
	public void test_construct() {
		DoubleTensor x = new DoubleTensor(new double[4], 2, 2);

		assertEquals(4, x.size());
		assertEquals(2, x.dimensions());
		assertArrayEquals(new int[] { 2, 2 }, x.shape());
		assertEquals(0, x.get(0, 0), EPSILON);
		assertEquals(0, x.get(1, 0), EPSILON);
		assertEquals(0, x.get(0, 1), EPSILON);
		assertEquals(0, x.get(1, 1), EPSILON);
		assertEquals(1, x.set(1, 0, 0), EPSILON);
		assertEquals(2, x.set(2, 1, 0), EPSILON);
		assertEquals(3, x.set(3, 0, 1), EPSILON);
		assertEquals(4, x.set(4, 1, 1), EPSILON);
	}

	@Test
	public void test_hadamardProduct() {
		Random r = new Random();
		for (int s : sizes) {
			System.gc();
			DoubleTensor x = new DoubleTensor(new double[s * s], s, s);
			DoubleTensor y = new DoubleTensor(new double[s * s], s, s);

			for (int i = 0; i < s * s; i++) {
				x.data[i] = r.nextDouble();
				y.data[i] = r.nextDouble();
			}

			long startTime = System.currentTimeMillis();
			DoubleTensor z = x.hadamardProduct(y);
			long finishTime = System.currentTimeMillis();
			System.out.printf("Multiplication of a double tensor (%dx%d) took %dms%n", s, s, finishTime - startTime);
			
			assertEquals(s * s, z.size());
			assertEquals(2, z.dimensions());
			assertArrayEquals(new int[] { s, s }, z.shape());

			for (int i = 0; i < s * s; i++) {
				assertEquals(x.data[i] * y.data[i], z.data[i], EPSILON);
			}

			x = null;
			y = null;
			z = null;
			
			System.gc();

			double[][] a = new double[s][s];
			double[][] b = new double[s][s];
			
			for (int i = 0; i < s; i++) {
				for (int j = 0; j< s; j++) {
					a[i][j] = r.nextDouble();
					b[i][j] = r.nextDouble();
				}
			}

			startTime = System.currentTimeMillis();
			double[][] c = new double[s][s];
			for (int i= 0; i<s; i++) {
				for (int j = 0; j < s; j++) {
					c[i][j] = a[i][j] * b[i][j];
				}
			}
			finishTime = System.currentTimeMillis();
			System.out.printf("Multiplication of an array of arrays of doubles (%dx%d) took %dms%n", s, s, finishTime - startTime);
		}
	}
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void test_hadamardProduct_not_matching_dimensions(){
		DoubleTensor x = new DoubleTensor(new double[2], 1, 2);
		DoubleTensor y = new DoubleTensor(new double[2], 2, 1);
	
		thrown.expect(AssertionError.class);
		x.hadamardProduct(y);
		
	}

}
