package com.lordvlad.matrices;

import static org.junit.Assert.*;

import java.util.Random;

import org.ejml.ops.RandomMatrices;
import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

import com.lordvlad.matrices.DoubleMatrix;
import com.lordvlad.matrices.Matrix;

public class DoubleMatrixTest {

	static int[] sizes = new int[] { /*2, 3, 5, 10, 50, 80,*/ 100, 150 };
	static int runs = 1000;
	static Random RND = new Random();

	static double randomDouble() {
		return RND.nextDouble();
	}

	@Test
	public void testTimes() {
		double x = 0;
		double y = 0;

		for (int k = 0; k < sizes.length - 1; k++) {
			for (int r = 0; r < runs; r++) {
				System.gc();

				int l = sizes[k];
				int m = sizes[k + 1];

				DoubleMatrix a = new DoubleMatrix(l, m);
				DoubleMatrix b = new DoubleMatrix(m, l);

				for (int i = 0; i < l; i++) {
					for (int j = 0; j < m; j++) {
						a.set(randomDouble(), i, j);
						b.set(randomDouble(), j, i);
					}
				}

				long startTime = System.currentTimeMillis();
				Matrix<Double> c = a.times(b);
				long finishTime = System.currentTimeMillis();
				x += (finishTime - startTime);

				assertNotNull(c);

				a = null;
				b = null;
				c = null;

				System.gc();

				SimpleMatrix d = new SimpleMatrix(
						RandomMatrices.createRandom(l, m, Double.MIN_VALUE, Double.MAX_VALUE, RND));
				SimpleMatrix e = new SimpleMatrix(
						RandomMatrices.createRandom(m, l, Double.MIN_VALUE, Double.MAX_VALUE, RND));

				startTime = System.currentTimeMillis();
				SimpleMatrix f = d.mult(e);
				finishTime = System.currentTimeMillis();
				y += (finishTime - startTime);

				assertNotNull(f);

			}
		}
		System.out.printf("Multiplication of an double matrix took %fms%n", x / (runs * (sizes.length - 1)));
		System.out.printf("Multiplication of an ejml matrix took %fms%n", y / (runs * (sizes.length - 1)));
	}

}
