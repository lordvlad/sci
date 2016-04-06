package com.lordvlad.math.numbers;

public class Double extends Number<Double> {

	private static final long serialVersionUID = 5622862883942052121L;
	private final java.lang.Double d;
	
	Double (java.lang.Double d) {
		this.d = d;
	}
	
	public static Double of(java.lang.Double d) {
		return new Double(d);
	}
	
	public static Double of(double d) {
		return of((java.lang.Double) d);
	}

	public Double times(Double that) {
		return new Double(this.d * that.d);
	}

	public Double plus(Double that) {
		return new Double(this.d + that.d);
	}

	public Double opposite() {
		return new Double(-this.d);
	}

	public int compareTo(Double o) {
		return d.compareTo(o.d);
	}

	@Override
	public int intValue() {
		return d.intValue();
	}

	@Override
	public long longValue() {
		return d.longValue();
	}

	@Override
	public float floatValue() {
		return d.floatValue();
	}

	@Override
	public double doubleValue() {
		return d.doubleValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Double)) return false;
		return d.equals(((Double)obj).d);
	}
	
	@Override
	public String toString() {
		return String.valueOf(d);
	}

	public Double times(double e) {
		return new Double(d * e);
	}
}
