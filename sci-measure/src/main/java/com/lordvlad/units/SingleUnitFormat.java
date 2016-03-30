package com.lordvlad.units;

import java.text.FieldPosition;
import java.text.ParsePosition;

import com.lordvlad.utils.Utils;

class SingleUnitFormat extends UnitFormat 
{
	private static final long serialVersionUID = 749597331701123304L;
	private static SingleUnitFormat INSTANCE = null;
	private SystemOfUnits system;
	
	private SingleUnitFormat(SystemOfUnits system) {
		this.system = system;
	}
	
	public static <S extends SystemOfUnits> SingleUnitFormat getInstance(S system) {
		return new SingleUnitFormat(system);
	}
	
	public static SingleUnitFormat getInstance() {
		if (INSTANCE == null) INSTANCE = new SingleUnitFormat(SI.getInstance());
		return INSTANCE;
	}

	@Override
	public final StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
		pos.setBeginIndex(toAppendTo.length());
		if (obj instanceof ScaledUnit) {
			ScaledUnit<?> u = (ScaledUnit<?>) obj;
			toAppendTo.append(MetricScale.scalings.get(u.scale).prefix);
			toAppendTo.append(this.system.units.get(u.baseUnit));
		} else {
			toAppendTo.append(this.system.units.get((Unit<?>) obj));
		}
		pos.setEndIndex(toAppendTo.length());
		return toAppendTo;
	}

	@Override
	public final Object parseObject(String source, ParsePosition pos) {
		Character c = null;
		StringBuilder b = new StringBuilder();
		while (pos.getIndex() < source.length()) {
			c = source.charAt(pos.getIndex());
			if (isNotAllowed(c)) break;
			b.append(c);
			inc(pos);
		}
		
		return Utils.firstNonNull(splitPrefixAndUnit(b, 2),
				splitPrefixAndUnit(b, 1),
				system.getUnit(b.toString()));
	}

	private Unit<?> splitPrefixAndUnit(final StringBuilder b, int prefixLength) {
		if (b.length() < prefixLength + 1) return null;
		final Scale s = MetricScale.prefixes.get(b.substring(0, prefixLength));
		if (s != null) {
			final Unit<?> u = system.getUnit(b.substring(prefixLength));
			if (u != null) return u.scale(s);
		}
		return null;
	}

	private static boolean isNotAllowed(Character c) {
		return c == null
				|| Character.isWhitespace(c)
				|| c == '/'
				|| c == '*'
				|| c == '^'
				|| c == '('
				|| c == ')';				
	}
}
