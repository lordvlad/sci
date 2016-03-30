package com.lordvlad.math.numbers;

public class Integer extends Number<Integer> {
	private static final long serialVersionUID = 5622862883942052121L;
	private final java.lang.Integer d;
	
	Integer (java.lang.Integer d) {
		this.d = d;
	}

	public Integer times(Integer that) {
		return new Integer(this.d * that.d);
	}

	public Integer plus(Integer that) {
		return new Integer(this.d + that.d);
	}

	public Integer opposite() {
		return new Integer(-this.d);
	}

	public int compareTo(Integer o) {
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
		if (!(obj instanceof Integer)) return false;
		return d.equals(((Integer)obj).d);
	}
	
	@Override
	public String toString() {
		return String.valueOf(d);
	}
}
