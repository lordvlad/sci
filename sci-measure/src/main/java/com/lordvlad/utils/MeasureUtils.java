package com.lordvlad.utils;

import com.lordvlad.math.numbers.Number;

public class MeasureUtils extends Utils {

	public static <T extends Number<T>> boolean equals(Number<T> a, Number<T> b) {
		return a == null ? (b == null) : a.equals(b);
	}

}
