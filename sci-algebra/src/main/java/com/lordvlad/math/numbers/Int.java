package com.lordvlad.math.numbers;

import java.util.Formatter;

public final class Int extends NumBase<Int> {

	private static final long serialVersionUID = 5553763627505864083L;
	long l;

	private Int(long l) {
		this.l = l;
	}

	public static Int of(long l) {
		return new Int(l);
	}

	@Override
	public void formatTo(Formatter formatter, int flags, int width, int precision) {
		// TODO Auto-generated method stub
	}

	@Override
	public Int plus(Int o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Int inverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Int times(Int o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Int opposite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double doubleValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
