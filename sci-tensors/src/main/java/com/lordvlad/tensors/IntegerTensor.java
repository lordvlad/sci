package com.lordvlad.tensors;

import java.io.Serializable;


/**
 * 
 * @author wre
 *
 */
public class IntegerTensor extends AbstractTensor implements Tensor<Integer>, Serializable
{
	private static final long serialVersionUID = 1947876806789807762L;
	final int[] data;

	public IntegerTensor(final int[] data, final int ... shape) {
		super(shape);
		this.data = data;
	}
	
	public Integer get(int... pos) {
		return data[index(pos)];
	}
	
	public Integer set(Integer x, int... pos) {
		return data[index(pos)] = x;
	}
	
	public IntegerTensor hadamardProduct(IntegerTensor other) {
		int[] r = new int[size];
		for (int i=0; i< size; i++) r[i] = data[i] * other.data[i];
		return new IntegerTensor(r, shape);
	}
	
}
