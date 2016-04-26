package com.lordvlad.math.numbers;


public class Op {
	private static final String E_UNSUPPORTED_NUMBER = null;

	private Op() {
	};

	public static Number add(Number a, Number b) {
		if (a instanceof Double) {
			return add(a.doubleValue(), b.doubleValue());
		}

		if (a instanceof Integer) {
			return add(a.intValue(), b.intValue());
		}

		throw noop(a);
	}

	public static Fraction add(Fraction a, Fraction b) {
		return Fraction.of(add(a.num, b.num), add(a.denom, b.denom));
	}
	public static long add(long a, long b) {
		return a+ b;
	}
	public static long add(int a, int b) {
		return a + b;
	}

	public static double add(double a, double b) {
		return a + b;
	}

	public static boolean gt(double a, double b) {
		return a > b;
	}

	boolean gt(int a, int b) {
		return a > b;
	}

	public static int remainder(int a, int b) {
		return a % b;
	}

	public static Number mul(Integer a, Integer b) {
		return a * b;
	}

	public static UnsupportedOperationException noop(Object o) {
		throw new UnsupportedOperationException(String.format(E_UNSUPPORTED_NUMBER, o.getClass()));
	}

	public static ArithmeticException divisionByZero() {
		return new ArithmeticException("/ by zero");
	}

	public static long remainder(long a, long b) {
		return a % b;
	}

	public static long mul(long a, long b) {
		return a * b;
	}

	public static Number mul(Number a, Number b) {
		if (a instanceof Double)
			return mul(a.doubleValue(), b.doubleValue());

		throw noop(a);
	}

	public static int compare(Number a, Number b) {
		if (a instanceof Double)
			return Double.compare(a.doubleValue(), b.doubleValue());

		throw noop(a);
	}

	public static Fraction inv(Fraction b) {
		return Fraction.of(b.denom, b.num);
	}

	public static Fraction mul(Fraction a, Fraction b) {
		return Fraction.of(mul(a.num, b.num), mul(b.denom, b.denom));
	}

	public static Fraction div(Fraction a, Fraction b) {
		return mul(a, inv(b));
	}

	public static boolean equals(Number a, Number a2) {
		// TODO Auto-generated method stub
		return false;
	}
}
