package com.lordvlad.math.numbers;

public class Long extends Number<Long> {
	private static final long serialVersionUID = 5622862883942052121L;
	private final java.lang.Long d;
	
	Long (java.lang.Long d) {
		this.d = d;
	}
	
	public static Long of(java.lang.Long d) {
		return new Long(d);
	}

	public Long times(Long that) {
		return new Long(this.d * that.d);
	}

	public Long plus(Long that) {
		return new Long(this.d + that.d);
	}

	public Long opposite() {
		return new Long(-this.d);
	}

	public int compareTo(Long o) {
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
		if (!(obj instanceof Long)) return false;
		return d.equals(((Long)obj).d);
	}
	
	@Override
	public String toString() {
		return String.valueOf(d);
	}
}