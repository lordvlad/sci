package com.lordvlad.matrices;

import com.lordvlad.math.numbers.Number;

public class GenericMatrix<N extends Number<N>> extends AbstractMatrix implements Matrix<N> {

	private Object[] data;

	public GenericMatrix(Object[] r, int i, int j) {
		this(r, new int[]{i, j});
	}
	
	public GenericMatrix(Object[] r, int[] is) {
		super(is);
		this.data = r;
	}

	public GenericMatrix(int r, int c) {
		this(new Object[r*c], r, c);
	}

	@SuppressWarnings("unchecked")
	public N get(int... pos) {
		return (N) data[index(pos)];
	}

	@SuppressWarnings("unchecked")
	public N set(N x, int... pos) {
		return (N) (data[index(pos)] = x);
	}

	@SuppressWarnings("unchecked")
	public Matrix<N> times(Matrix<N> other) {
		int r = rows();
		int l = cols();
		int c = other.cols();
		final GenericMatrix<N> a = new GenericMatrix<N>(r, c);
		
		if (other instanceof GenericMatrix) {
			GenericMatrix<N> o = (GenericMatrix<N>) other;
			
			for (int i = 0; i < r; i++) {			
				for (int j = 0; j < c; j++) {
					int s = a.index(i,j);
					for (int k = 0; k < l; k++) {
						a.data[s] = ((N)a.data[s]).plus(((N)data[index(i, k)]).times((N) o.data[o.index(k, j)])); 
					}
				}
			}
		} else {
			// TODO
		}
		return a;
	}

	@SuppressWarnings("unchecked")
	public Matrix<N> hadamardProduct(Matrix<N> other) {
		assertSameShape(this, other);
		final Object[] r = new Object[size];
		if (other instanceof GenericMatrix) {
			final GenericMatrix<N> o = (GenericMatrix<N>) other;
			for (int i=0; i< size; i++) r[i] = ((N)data[i]).times((N)o.data[i]);
		} else {
			// TODO
		}
		return new GenericMatrix<N>(r, shape[0], shape[1]);
	}
}
