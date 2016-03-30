package com.lordvlad.amounts;

import com.lordvlad.math.numbers.Double;
import com.lordvlad.math.numbers.Long;
import com.lordvlad.math.numbers.Number;
import com.lordvlad.math.numbers.NumberWithError;
import com.lordvlad.quantities.Quantity;
import com.lordvlad.structures.MutablePair;
import com.lordvlad.units.Unit;
import com.lordvlad.utils.MeasureUtils;

public class Amount<T extends Number<T>, Q extends Quantity> extends MutablePair<Number<T>, Unit<Q>> {

	private static final long serialVersionUID = 252947783626459366L;
	private static final String E_UNIT_NULL = "unit is null";
	private static final String E_NUM_NULL = "numeric value is nulll";
	
	public static <N extends Number<N>, S extends Quantity> Amount<N, S> of (Number<N> a, Unit<S> b) {
		if (a == null) throw new IllegalArgumentException(E_NUM_NULL);
		if (b == null) throw new IllegalArgumentException(E_UNIT_NULL);
		return new Amount<N, S>(a, b);
	}
	
	public static <S extends Quantity> Amount<NumberWithError<Double>, S> of (double a1, double a2, Unit<S> b) {
		return of(NumberWithError.of(a1, a2), b);
	}
	
	public static <S extends Quantity> Amount<Long, S> of (long a, Unit<S> b)
	{
		return of(Long.of(a), b);
	}
	
	public static <S extends Quantity> Amount<Double, S> of (double a, Unit<S> b) {
		return of(Double.of(a), b);
	}
	
	public static <N extends Number<N>, S extends Quantity> Amount<NumberWithError<N>, S> of (N a1, N a2, Unit<S> b) {
		return of(NumberWithError.of(a1, a2), b);
	}
	
	private Amount(Number<T> a, Unit<Q> b) {
		super(a, b);
	}
	public Number<T> getAmount(){ return a; }
	public Unit<Q> getUnit(){ return b; }
	
	@Override
	public String toString() {
		return String.valueOf(a) + ' ' + String.valueOf(b);
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
			Amount<T, Q> other = (Amount<T, Q>) obj;
			if (a == null) {
				if (other.a != null)
					return false;
			} else if (!MeasureUtils.equals(a, other.a))
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
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	
}
