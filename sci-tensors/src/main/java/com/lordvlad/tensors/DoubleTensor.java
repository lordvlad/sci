package com.lordvlad.tensors;

import java.io.Serializable;

public class DoubleTensor extends AbstractTensor implements Tensor<Double>, Serializable{
	private static final long serialVersionUID = -3146778078315611181L;
	final protected double[] data;
	
	public DoubleTensor(final double[] data, final int ... shape) {
		super(shape);
		this.data = data;
	}
    
	public Double get(int... pos) {
		return data[index(pos)];
	}
	
	public Double set(Double x, int... pos) {
		return data[index(pos)] = x;
	}

	public DoubleTensor hadamardProduct(DoubleTensor other) {
		assertSameShape(this, other);
		double[] r = new double[size];
		for (int i=0; i< size; i++) r[i] = data[i] * other.data[i];
		return new DoubleTensor(r, shape);
	}

	public double set(int i, int ... pos) {
		return set((double) i, pos);
	}

}
