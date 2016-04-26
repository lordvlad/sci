package com.lordvlad.units;

import java.text.FieldPosition;
import java.text.ParsePosition;

import com.lordvlad.utils.Utils;

class SingleUnitFormat extends UnitFormat 
{
	private static final long serialVersionUID = 749597331701123304L;
	private static final int ALLOW_SPACE = 0b10;
	private static final int ALLOW_NUMBERS = 0b01;
	private static SingleUnitFormat INSTANCE = null;
	private Class<? extends SystemOfUnits>[] systems;
	
	@SafeVarargs
	private SingleUnitFormat(Class<? extends SystemOfUnits> ...systems) {
		this.systems = systems;
	}
	
	@SafeVarargs
	public static SingleUnitFormat getInstance(Class<? extends SystemOfUnits> ...systems) {
		return new SingleUnitFormat(systems);
	}
	
	public static SingleUnitFormat getInstance() {
		if (INSTANCE == null) INSTANCE = new SingleUnitFormat(SI.class);
		return INSTANCE;
	}

	@Override
	public final StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
		pos.setBeginIndex(toAppendTo.length());
		String name;
		if ((name=Units.getName((Unit<?>) obj, this.systems)) !=null){
			toAppendTo.append(name);
		}else if (obj instanceof ScaledUnit) {
			ScaledUnit<?> u = (ScaledUnit<?>) obj;
			toAppendTo.append(MetricScale.scalings.get(u.scale).prefix);
			toAppendTo.append(Units.getName(u.baseUnit, this.systems));
		} else {
			// FIXME exception?
		}
		pos.setEndIndex(toAppendTo.length());
		return toAppendTo;
	}

	@Override
	public final Object parseObject(String source, ParsePosition pos) {
		int i = 0;
		int options = 0;
		Character c = null;
		StringBuilder b = new StringBuilder();
		while (pos.getIndex() < source.length()) {
			c = source.charAt(pos.getIndex());
			if (i == 0) {
				if (Character.isDigit(c)) {
					options = options | ALLOW_SPACE;
					options = options | ALLOW_NUMBERS;
				}
			}
			if (isNotAllowed(c, options)) break;
			b.append(c);
			i++;
			inc(pos);
		}
		
		return Utils.firstNonNull(splitPrefixAndUnit(b, 2),
				splitPrefixAndUnit(b, 1),
				Units.getUnit(b.toString(), systems));
	}

	private Unit<?> splitPrefixAndUnit(final StringBuilder b, int prefixLength) {
		if (b.length() < prefixLength + 1) return null;
		final Scale s = MetricScale.prefixes.get(b.substring(0, prefixLength));
		if (s != null) {
			final Unit<?> u = Units.getUnit(b.substring(prefixLength), systems);
			if (u != null) return u.scale(s);
		}
		return null;
	}

	private static boolean isNotAllowed(Character c, int o) {
		return c == null
				|| (( o & ALLOW_SPACE) == ALLOW_SPACE ? false : c == ' ')
				|| (( o & ALLOW_NUMBERS) == ALLOW_NUMBERS ? false : Character.isDigit(c))
				|| c == '/'
				|| c == '*'
				|| c == '^'
				|| c == '('
				|| c == ')'
				|| c == '²';		
	}
}
