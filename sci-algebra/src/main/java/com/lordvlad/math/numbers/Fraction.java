package com.lordvlad.math.numbers;

import java.text.NumberFormat;
import java.text.ParsePosition;

import com.lordvlad.math.structures.Multiplicative;

public class Fraction extends Number<Fraction> implements Multiplicative<Fraction>{
	private static final long serialVersionUID = 3040650164805113194L;
	
	private static int commonDenominator(Fraction one, Fraction other) {
		// FIXME use some nice LCM here
		return one.denom * other.denom;
	}
	
	/** @return the greatest common denominator */
	public static int gcm(int a, int b) {
		while (a != 0 && b != 0) {
			if (a > b) {a = a % b;} else {b = b % a;}
		}
	    return a;
	}
	
	public static Fraction reduce(Fraction some) {
		int gcm = gcm(some.num, some.denom);
		return new Fraction(some.num / gcm, some.denom / gcm);
	}
	
	public static Fraction of(String s) {
		ParsePosition pos = new ParsePosition(0);
		int a = NumberFormat.getIntegerInstance().parse(s, pos).intValue();
		if (pos.getIndex() < s.length() && s.charAt(pos.getIndex()) == '/') {
			pos.setIndex(pos.getIndex()+1);
			int b = NumberFormat.getIntegerInstance().parse(s, pos).intValue();
			return Fraction.of(a, b);
		}
		return Fraction.of(a);
	}
	
	public static Fraction of(int a) {
		return new Fraction(a, 1);
	}
	
	public static Fraction of(int a, int b) {
		return new Fraction(a, b);
	}
	
	private final int denom;
	private final int num;
	
	Fraction(int num, int denom) {
		this.num = num;
		this.denom = denom;
	}

	public Fraction times(Fraction that) {
		return new Fraction(this.num * that.num, this.denom * that.denom);
	}

	public Fraction plus(Fraction that) {
		int ndenom = commonDenominator(this, that);
		int nnum = (ndenom/denom) * num + (ndenom/that.denom) * that.num;
		
		return new Fraction(nnum, ndenom);
	}

	public Fraction opposite() {
		return new Fraction(-this.num, this.denom);
	}

	public int compareTo(Fraction o) {
		return java.lang.Double.compare(this.doubleValue(), o.doubleValue());
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Fraction
				&& this.num == ((Fraction)obj).num
				&& this.denom == ((Fraction)obj).denom;
	}

	@Override
	public int intValue() {
		return new Double(doubleValue()).intValue();
	}

	@Override
	public long longValue() {
		return (long) intValue();
	}

	@Override
	public float floatValue() {
		return (float) doubleValue();
	}

	@Override
	public double doubleValue() {
		return this.num / this.denom;
	}

	@Override
	public String toString() {
		return String.format("%d/%d", this.num, this.denom);
	}

	public Fraction inverse() {
		return new Fraction(this.denom, this.num);
	}
	
	public Fraction over(Fraction other) {
		return this.times(other.inverse());
	}
}
