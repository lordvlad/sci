package com.lordvlad.matrices;

import com.lordvlad.tensors.Tensor;

public interface Matrix<N extends Number> extends Tensor<N>{
	
	public Matrix<N> times(Matrix<N> other);
	public Matrix<N> hadamardProduct (Matrix<N> other);
	
	public int cols();
	public int rows();
}
