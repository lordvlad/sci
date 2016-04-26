package com.lordvlad.math.numbers;

import java.text.NumberFormat;
import java.text.ParsePosition;

final public class Fraction extends Number {
	long num;
	long denom;


	private static final long serialVersionUID = 3040650164805113194L;
	public static final Fraction ONE = new Fraction(1, 1);
	public static final Fraction ZERO = new Fraction(0, 1);

	protected Fraction(long n, long d) {
		this.num = n;
		this.denom = d;
	}

	/**
	 * @return the greatest common denominator
	 */
	private static long gcd(long a, long b) {
		while (a != 0 && b != 0) {
			if (Op.gt(a, b)) {
				a = Op.remainder(a, b);
			} else {
				b = Op.remainder(b, a);
			}
		}
		return a;
	}

	static Number commonDenominator(Fraction one, Fraction other) {
		// FIXME use some nice LCM here
		if (one.denom == other.denom)
			return one.denom;
		return Op.mul(one.denom, other.denom);
	}

	public static Fraction of(long a, long b)  {
		if (a == 0)
			return ZERO;
		if (a == 1 && b == 1) return ONE;
		if (b == 0)
			throw Op.divisionByZero();
		final long gcd = gcd(a, b);
		final long n = a/gcd;
		final long d = b/gcd;
		return new Fraction(n, d);
	}


	public static Number of(String s) {
		ParsePosition pos = new ParsePosition(0);
		long a = NumberFormat.getIntegerInstance().parse(s, pos).longValue();
		if (pos.getIndex() < s.length() && s.charAt(pos.getIndex()) == '/') {
			pos.setIndex(pos.getIndex() + 1);
			long b = NumberFormat.getIntegerInstance().parse(s, pos).longValue();
			return of(a, b);
		}
		return of(a, 1);
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

	public static Fraction of(int i) {
		return of(i, 1);
	}

}
