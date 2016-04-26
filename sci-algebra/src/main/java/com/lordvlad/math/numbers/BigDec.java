package com.lordvlad.math.numbers;

import java.math.BigDecimal;
import java.util.Formatter;

public final class BigDec extends NumBase<BigDec> {

	private static final long serialVersionUID = 7398154298567512512L;
	private final BigDecimal n;

	private BigDec(BigDecimal n) {
		this.n = n;
	}

	@Override
	public void formatTo(Formatter formatter, int flags, int width, int precision) {
		// TODO Auto-generated method stub

	}

	@Override
	public BigDec plus(BigDec o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDec inverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDec times(BigDec o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDec opposite() {
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

	public static BigDec valueOf(long n) {
		return valueOf(BigDecimal.valueOf(n));
	}

	public static BigDec valueOf(double n) {
		return valueOf(BigDecimal.valueOf(n));
	}

	private static BigDec valueOf(BigDecimal n) {
		return new BigDec(n);
	}

}
