package com.lordvlad.matrices;

import java.io.Serializable;

public class DoubleMatrix extends AbstractMatrix implements Matrix<Double>, Serializable {

	private static final long serialVersionUID = -3289084451784697489L;
	
	final double[] data;

	public DoubleMatrix(double[] data, int cols, int rows) {
		super(new int[] { cols, rows });
		this.data = data;
	}

	public DoubleMatrix(int cols, int rows) {
		this(new double[rows * cols], cols, rows);
	}

	public Matrix<Double> times(final Matrix<Double> other) {
		if (other instanceof DoubleMatrix) {

			final DoubleMatrix matA = this;
			final DoubleMatrix matB = (DoubleMatrix) other;
			final int k0 = matA.cols(); // == other.rows()
			final int rows = matA.rows();
			final int cols = matB.cols();
			final DoubleMatrix matC = new DoubleMatrix(rows, cols);

			double[] a = matA.data;
			double[] b = matB.data;
			double[] c = matC.data;

			int indexA = 0;
			int indexB = 0;
			int indexC = 0;
			int k = 0;

			for (int rowA = 0; rowA < rows; rowA++) {
				for (int colA = 0; colA < k0; colA++) {
					k = 0;
					indexB = colA * cols;
					indexC = rowA * cols;
					while (k < cols) {
						c[indexC] += a[indexA] * b[indexB];
						indexC++;
						indexB++;
						k++;
					}
					indexA++;
				}
			}

			return matC;
		} else {
			// TODO
			return null;
		}
	}

	public Double get(final int... pos) {
		return this.data[index(pos)];
	}

	public Double set(final Double x, final int... pos) {
		return this.data[index(pos)] = x;
	}

	public Matrix<Double> hadamardProduct(final Matrix<Double> other) {
		assertSameShape(this, other);
		final double[] r = new double[size];
		if (other instanceof DoubleMatrix) {
			final DoubleMatrix o = (DoubleMatrix) other;
			for (int i = 0; i < size; i++)
				r[i] = data[i] * o.data[i];
		} else {
			// TODO
		}
		return new DoubleMatrix(r, shape[0], shape[1]);
	}

}
