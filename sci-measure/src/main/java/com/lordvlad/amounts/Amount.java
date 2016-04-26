package com.lordvlad.amounts;

import com.lordvlad.math.numbers.Num;
import com.lordvlad.math.numbers.NumberWithError;
import com.lordvlad.math.numbers.Op;
import com.lordvlad.quantities.Quantity;
import com.lordvlad.structures.Pair;
import com.lordvlad.units.Unit;

public class Amount<Q extends Quantity> extends Pair<Number, Unit<Q>> implements Num<Amount<Q>>{

	private static final long serialVersionUID = 252947783626459366L;
	private static final String E_UNIT_NULL = "unit is null";
	private static final String E_NUM_NULL = "numeric value is nulll";

	public static <S extends Quantity> Amount<S> of(Number a, Unit<S> b) {
		if (a == null)
			throw new IllegalArgumentException(E_NUM_NULL);
		if (b == null)
			throw new IllegalArgumentException(E_UNIT_NULL);
		return new Amount<S>(a, b);
	}

	public static <S extends Quantity> Amount<S> of(double a1, double a2, Unit<S> b) {
		return of(NumberWithError.of(a1, a2), b);
	}

	public static <S extends Quantity> Amount<S> of(double a, Unit<S> b) {
		return of(Double.valueOf(a), b);
	}

	public static <S extends Quantity> Amount<S> of(Number a1, Number a2, Unit<S> b) {
		return of(NumberWithError.of(a1, a2), b);
	}

	private Amount(Number a, Unit<Q> b) {
		super(a, b);
	}

	public Number getAmount() {
		return a;
	}

	public Unit<Q> getUnit() {
		return b;
	}

	@Override
	public String toString() {
		String u = String.valueOf(b);
		if (u != null && !u.isEmpty())
			u = ' ' + u;
		return String.valueOf(a) + u;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		try {
			@SuppressWarnings("unchecked")
			Amount<Q> other = (Amount<Q>) obj;
			if (a == null) {
				if (other.a != null)
					return false;
			} else if (!Op.equals(a, other.a))
				return false;
			if (b == null) {
				if (other.b != null)
					return false;
			} else if (!b.equals(other.b))
				return false;
			return true;
		} catch (ClassCastException e) {
			return false;
		}
	}

	public Amount<Q> to(Unit<Q> u) {
		return (Amount<Q>) of(getUnit().to(u).apply(getAmount().doubleValue()), u);
	}

	@Override
	public Amount<Q> plus(Amount<Q> o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Amount<Q> inverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Amount<Q> times(Amount<Q> o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Amount<Q> opposite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Amount<Q> minus(Amount<Q> o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Amount<Q> over(Amount<Q> o) {
		// TODO Auto-generated method stub
		return null;
	}
}
