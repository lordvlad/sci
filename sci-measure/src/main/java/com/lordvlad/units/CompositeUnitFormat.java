package com.lordvlad.units;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Iterator;
import java.util.Map.Entry;

class CompositeUnitFormat extends UnitFormat {

	private static final long serialVersionUID = -1972592388421822511L;
	private static final String E_FORMAT_NOT_A_UNIT = null;
	private static CompositeUnitFormat INSTANCE = null;
	private final NumberFormat num;
	private final SingleUnitFormat single;

	@SafeVarargs
	public static CompositeUnitFormat getInstance(Class<? extends SystemOfUnits>... systems) {
		return new CompositeUnitFormat(systems);
	}

	public static CompositeUnitFormat getInstance() {
		if (INSTANCE == null)
			INSTANCE = new CompositeUnitFormat(SI.class);
		return INSTANCE;
	}

	CompositeUnitFormat() {
		this(SI.class);
	}

	@SafeVarargs
	CompositeUnitFormat(Class<? extends SystemOfUnits>... system) {
		single = SingleUnitFormat.getInstance(system);
		num = NumberFormat.getInstance();
		num.setParseIntegerOnly(true);
	}

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
		return format(obj, toAppendTo, pos, false);
	}

	private StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos, boolean appendParens) {
		if (!(obj instanceof Unit)) {
			throw new IllegalArgumentException(E_FORMAT_NOT_A_UNIT);
		} else if (obj instanceof CompositeUnit) {
			Iterator<Entry<Unit<?>, Integer>> it = ((CompositeUnit<?>)obj).units.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Unit<?>, Integer> next = it.next();
				single.format(next.getKey(), toAppendTo, pos);
				if (next.getValue() != 1) append("^" + next.getValue(), toAppendTo, pos);
				if (it.hasNext()) append("*", toAppendTo, pos);
			}
		} else {
			toAppendTo = single.format(obj, toAppendTo, pos);
		}

		return toAppendTo;
	}

	private void append(String what, StringBuffer toAppendTo, FieldPosition pos) {
		pos.setBeginIndex(toAppendTo.length());
		toAppendTo.append(what);
		pos.setEndIndex(toAppendTo.length());
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		Unit<?> u = Unit.ONE;
		Character c = null;

		while (pos.getIndex() < source.length()) {
			c = source.charAt(pos.getIndex());

			if ('(' == c)
				return parse(source, inc(pos));
			else if (')' == c) {
				inc(pos);
				return u;
			} else if ('*' == c)
				u = u.times(parse(source, inc(pos)));
			else if ('/' == c)
				u = u.over(parse(source, inc(pos)));
			else if ('^' == c)
				u = u.pow(num.parse(source, inc(pos)).intValue());
			else if ('²' == c) {
				inc(pos);
				u = u.pow(2);
			} else if ('1' == c) {
				inc(pos);
				u = Unit.ONE;
			} else
				u = single.parse(source, pos);

			if (u == null)
				return null;
		}

		return u;
	}

}
