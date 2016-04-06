package com.lordvlad.matrices;

import com.lordvlad.tensors.AbstractTensor;

abstract class AbstractMatrix extends AbstractTensor {


	public Object[] data;
	public AbstractMatrix(int[] shape) {
		super(shape);
	}

	public int dimensions () {
		return 2;
	}
	
	@Override
	protected int index(int... pos) {
		return super.index(pos);
	}

	public int rows() { return this.shape[0]; }
	public int cols() { return this.shape[1]; }
}
