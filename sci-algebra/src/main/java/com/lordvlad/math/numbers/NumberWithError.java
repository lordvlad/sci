package com.lordvlad.math.numbers;

import java.io.IOException;
import java.util.Formattable;
import java.util.FormattableFlags;
import java.util.Formatter;

import com.lordvlad.utils.Symbols;

public class NumberWithError<N extends Number<N>> extends Number<NumberWithError<N>> implements Formattable {

	private static final long serialVersionUID = 226887904455377004L;

	private static final String E_ERR_LESS_THAN_ZERO = "Cannot create an Number/error pair with an error less than zero";
	private static final String E_VAL_NULL = "value is NULL";
	private static final String E_ERR_NULL = "error is NULL";

	final N val;
	final N err;

	public static NumberWithError<Double> of (double val, double err) {
		return of(Double.of(val), Double.of(err));
	}
	
	public static <M extends Number<M>> NumberWithError<M> of(M val, M err) {
		if (val == null)
			throw new IllegalArgumentException(E_VAL_NULL);
		if (err == null)
			throw new IllegalArgumentException(E_ERR_NULL);
		if (err.doubleValue() < 0)
			throw new IllegalArgumentException(E_ERR_LESS_THAN_ZERO);

		return new NumberWithError<M>(val, err);
	}

	private NumberWithError(N val, N err) {
		this.val = val;
		this.err = err;
	}

	@Override
	public int intValue() {
		return val.intValue();
	}

	@Override
	public long longValue() {
		return val.longValue();
	}

	@Override
	public float floatValue() {
		return val.floatValue();
	}

	@Override
	public double doubleValue() {
		return val.doubleValue();
	}

	public double maxValue() {
		return val.doubleValue() + err.doubleValue();
	}

	public double minValue() {
		return val.doubleValue() - err.doubleValue();
	}

	@Override
	public String toString() {
		return (val.toString() + ' ' + Symbols.PLUSMINUS + ' ' + err.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((err == null) ? 0 : err.hashCode());
		result = prime * result + ((val == null) ? 0 : val.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Number))
			return false;
		if (!(obj instanceof NumberWithError)) {
			Number<?> other = (Number<?>) obj;
			return minValue() <= other.doubleValue() && other.doubleValue() <= maxValue();
		}
		NumberWithError<?> other = (NumberWithError<?>) obj;
		return maxValue() >= other.minValue() && maxValue() <= other.maxValue()
				|| minValue() >= other.minValue() && minValue() <= other.maxValue();
	}

	public void formatTo(Formatter formatter, int flags, int width, int precision) {
		try {
			final Appendable out = formatter.out();
			final String formatted = NumberWithErrorFormat.getInstance().format(this);

			int pad = width == -1 ? 0 : width - formatted.length();
			if ((flags & FormattableFlags.LEFT_JUSTIFY) == 0){
				while (pad > 0) { out.append(' '); pad--;}
			}
			out.append(formatted);
			if ((flags & FormattableFlags.LEFT_JUSTIFY) != 0){
				while (pad > 0) { out.append(' '); pad--;}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public NumberWithError<N> times(NumberWithError<N> that) {
		return new NumberWithError<N>(val.times(that.val), err.times(that.err));
	}

	public NumberWithError<N> plus(NumberWithError<N> that) {
		return new NumberWithError<N>(val.plus(that.val), err.plus(that.err));
	}

	public NumberWithError<N> opposite() {
		return new NumberWithError<N>(val.opposite(), err.opposite());
	}

	public int compareTo(NumberWithError<N> o) {
		return val.compareTo(o.val);
	}


}
