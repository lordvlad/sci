package com.lordvlad.math.numbers;

import java.io.IOException;
import java.util.Formattable;
import java.util.FormattableFlags;
import java.util.Formatter;

import com.lordvlad.utils.Symbols;

public class NumberWithError extends Number implements Formattable {

	private static final long serialVersionUID = 226887904455377004L;

	private static final String E_ERR_LESS_THAN_ZERO = "Cannot create an Number/error pair with an error less than zero";
	private static final String E_VAL_NULL = "value is NULL";
	private static final String E_ERR_NULL = "error is NULL";

	final Number val;
	final Number err;

	
	public static NumberWithError of(Number val, Number err) {
		if (val == null)
			throw new IllegalArgumentException(E_VAL_NULL);
		if (err == null)
			throw new IllegalArgumentException(E_ERR_NULL);
		if (err.doubleValue() < 0)
			throw new IllegalArgumentException(E_ERR_LESS_THAN_ZERO);

		return new NumberWithError(val, err);
	}

	private NumberWithError(Number val, Number err) {
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
			Number other = (Number) obj;
			return minValue() <= other.doubleValue() && other.doubleValue() <= maxValue();
		}
		NumberWithError other = (NumberWithError) obj;
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


	public int compareTo(NumberWithError o) {
		return Op.compare(val, o.val);
	}

	

}
