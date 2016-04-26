package com.lordvlad.math.numbers;

public interface Num<T extends Num<T>> {

	T plus(T o);

	T inverse();

	T times(T o);

	T opposite();

	T minus(T o);

	T over(T o);

}