package com.lordvlad.tensors;

abstract public class AbstractTensor {

	protected int dimensions;
	protected int size;
	protected int[] shape;
	protected int[] stride;

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

	protected int index(int[] pos) {
		int j = 0;
		for (int i = 0; i < pos.length; i++) {
			j += this.stride[i] * pos[i];
		}
		return j;			
	}

	protected int dimensions() {
		return dimensions;
	}

	protected int[] shape() {
		return shape;
	}

	protected int size() {
		return size;
	}
}