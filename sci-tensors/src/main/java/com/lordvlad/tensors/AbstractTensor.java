package com.lordvlad.tensors;

import static com.google.common.truth.Truth.*;

import java.util.Arrays;

public abstract class AbstractTensor  {

	protected int dimensions;
	protected int size;
	protected int[] shape;
	protected int[] stride;
	
	protected AbstractTensor() {}

	protected AbstractTensor(int ...shape) {
		this.shape = shape;
		this.dimensions = shape.length;
		this.stride = new int[this.dimensions];
		this.stride[0] = 1;
		int size = shape[0];
		for (int i=1; i < this.dimensions; i++) {
			this.stride[i] = this.stride[i-1] * shape[i-1];
			size *= shape[i];
		}
		this.size = size;
	}

	protected int index(int ... pos) {
		int j = 0;
		for (int i = 0; i < pos.length; i++) {
			j += this.stride[i] * pos[i];
		}
		if (j >= this.size) {
			throw new RuntimeException("Position out of bounds: " + Arrays.toString(pos) + " - " + j + " " + Arrays.toString(this.shape) + "(" + Arrays.toString(this.stride)+")");
		}
		return j;
	}

	public int dimensions() {
		return dimensions;
	}

	public int[] shape() {
		return shape;
	}

	public int size() {
		return size;
	}

	protected static void assertSameShape(Tensor<?> one, Tensor<?> other) throws AssertionError {
		assertThat(one.shape()).isEqualTo(other.shape());		
	}
	
}