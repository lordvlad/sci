package com.lordvlad.tensors;

public interface Tensor<N extends Number> {
	N get(int ... pos);
	N set(N x, int ... pos);
	int[] shape();
	int dimensions();
	int size();
}
