package com.lordvlad.tensors;

/**
 * Hello world!
 *
 */
public final class IntegerTensor extends AbstractTensor
{
	final int[] data;

	public IntegerTensor(final int[] data, final int ... shape) {
		super(shape);
		this.data = data;
	}
	
	public int get(int... pos) {
		return data[index(pos)];
	}
	
	public int set(int x, int... pos) {
		return data[index(pos)] = x;
	}
	
	public IntegerTensor times(IntegerTensor other) {
		int[] r = new int[size];
		for (int i=0; i< size; i++) r[i] = data[i] * other.data[i];
		return new IntegerTensor(r, shape);
	}
	
}
