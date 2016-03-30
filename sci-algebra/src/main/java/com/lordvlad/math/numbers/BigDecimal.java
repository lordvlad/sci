package com.lordvlad.math.numbers;

public class BigDecimal extends Number<BigDecimal> {
	private static final long serialVersionUID = -3486891444554602027L;
	private static final java.math.BigDecimal MINUS_ONE = java.math.BigDecimal.valueOf(-1);
	private final java.math.BigDecimal dec;

	BigDecimal(java.math.BigDecimal dec) {
		this.dec = dec;
	}
	
	public BigDecimal times(BigDecimal that) {
		return new BigDecimal(this.dec.multiply(that.dec));
	}

	public BigDecimal plus(BigDecimal that) {
		return new BigDecimal(this.dec.add(that.dec));
	}

	public BigDecimal opposite() {
		return new BigDecimal(this.dec.multiply(MINUS_ONE));
	}

	public int compareTo(BigDecimal o) {
		return dec.compareTo(o.dec);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof BigDecimal && dec.equals(((BigDecimal) obj).dec);
	}

	@Override
	public int intValue() {
		return dec.intValue();
	}

	@Override
	public long longValue() {
		return dec.longValue();
	}

	@Override
	public float floatValue() {
		return dec.floatValue();
	}

	@Override
	public double doubleValue() {
		return dec.doubleValue();
	}

	@Override
	public String toString() {
		return dec.toString();
	}
	
	public static BigDecimal of(long l) {
		return new BigDecimal(java.math.BigDecimal.valueOf(l));
	}
	
	public static BigDecimal of(double d) {
		return new BigDecimal(java.math.BigDecimal.valueOf(d));
	}
}
