package com.lordvlad.tensors;

public final class DoubleTensor extends AbstractTensor {
	final double[] data;
	
	public DoubleTensor(final double[] data, final int ... shape) {
		super(shape);
		this.data = data;
	}
    
	public double get(int... pos) {
		return data[index(pos)];
	}
	
	public double set(double x, int... pos) {
		return data[index(pos)] = x;
	}

	public DoubleTensor times(DoubleTensor other) {
		double[] r = new double[size];
		for (int i=0; i< size; i++) r[i] = data[i] * other.data[i];
		return new DoubleTensor(r, shape);
	}

}
