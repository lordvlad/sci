package com.lordvlad.math.numbers;

import com.lordvlad.math.structures.Ring;

public abstract class Number<T extends Number<T>> extends java.lang.Number implements Ring<T>, Comparable<T> {

	private static final long serialVersionUID = 1896780567358111417L;

	@SuppressWarnings("rawtypes")
	public static Number<?> of(Object o) {
		if (o == null) return null;
		if (o instanceof Long) return (Long) o;
		if (o instanceof Double) return (Double) o;
		if (o instanceof Integer) return (Integer) o;
		if (o instanceof NumberWithError) return (NumberWithError) o;
		if (o instanceof BigDecimal) return (BigDecimal) o;
		if (o instanceof java.lang.Double) return new Double((java.lang.Double) o);
		if (o instanceof java.lang.Integer) return new Integer((java.lang.Integer) o);
		if (o instanceof java.lang.Long) return new Long((java.lang.Long) o);
		if (o instanceof java.math.BigDecimal) return new BigDecimal((java.math.BigDecimal) o);
		throw new RuntimeException(String.format("Unhandled type %s", o.getClass()));
	}
	
	@Override
	public abstract boolean equals(Object obj);

}
