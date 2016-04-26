package com.lordvlad.tensors;

import java.io.Serializable;

import com.lordvlad.math.numbers.NumBase;

public class GenericTensor<N extends NumBase<N>> extends AbstractTensor implements NumberTensor<N>, Serializable{
	private static final long serialVersionUID = -5539627822538773739L;
	final Object[] data;
	final Class<N> cls;

	public GenericTensor(Class<N> cls, final int ... shape) {
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
	public GenericTensor<N> hadamardProduct(GenericTensor<N> other) {
		assertSameShape(this, other);
		final GenericTensor<N> r = new GenericTensor<N>(cls, shape);
		for (int i=0; i< size; i++) {
			r.data[i] = ((N) data[i]).times((N) other.data[i]);
		}
		return r;
	}
}
