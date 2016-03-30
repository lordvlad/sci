package com.lordvlad.tensors;

import com.lordvlad.math.numbers.Number;

public class Tensor<N extends Number<N>> extends AbstractTensor {
	final Object[] data;
	final Class<N> cls;

	public Tensor(Class<N> cls, final int ... shape) {
		super(shape);
		this.cls = cls;
		this.data = new Object[size];
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
	public Tensor<N> times(Tensor<N> other) {
		Tensor<N> r = new Tensor<N>(cls, shape);
		for (int i=0; i< size; i++) {
			r.data[i] = ((N) data[i]).times((N) other.data[i]);
		}
		return r;
	}
}
