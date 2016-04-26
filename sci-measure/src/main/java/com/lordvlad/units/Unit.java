package com.lordvlad.units;

import java.util.function.Function;

import com.lordvlad.quantities.Dimensionless;
import com.lordvlad.quantities.Quantity;
import com.lordvlad.utils.Arrays;

abstract public class Unit<Q extends Quantity> implements Comparable<Unit<Q>> {

	public static final Unit<Dimensionless> ONE = new UnitOne();
	private static final String E_INCOMPATIBLE = null;
	protected static final Function<Double, Double> noop = new Function<Double, Double>() {
		public Double apply(Double a) {
			return a;
		}
	};

	protected String symbol;
	protected String[] aliases;

	protected Unit(String symbol, String... aliases) {
		this.symbol = symbol;
		this.aliases = aliases.clone();
	}

	abstract Unit<Q> getBaseUnit();

	@Override
	public String toString() {
		return getSymbol();
	}

	public String getSymbol() {
		return symbol;
	}

	public <T extends Quantity> Unit<?> times(Unit<T> other) {
		return this.equals(other) ? CompositeUnit.pow(this, 2) : CompositeUnit.multiply(this, other);
	}

	public Unit<?> pow(int num) {
		return CompositeUnit.pow(this, num);
	}

	public <T extends Quantity> Unit<?> over(Unit<T> other) {
		return this.times(other.pow(-1));
	}

	public Unit<Q> scale(Scale s) {
		return ScaledUnit.of(this, s);
	}

	public Unit<Q> plus(double d) {
		return new ShiftedUnit<Q>(this, d);
	}

	public Unit<Q> scale(double i) {
		return ScaledUnit.of(this, i);
	}

	@SuppressWarnings("unchecked")
	public <T extends Quantity> Unit<T> as(Class<T> quantity) {
		return (Unit<T>) this;
	}

	public boolean isCompatible(Unit<?> other) {
		return other.getBaseUnit().equals(this.getBaseUnit());
	}

	abstract Function<Double, Double> toBaseUnit();

	abstract Function<Double, Double> fromBaseUnit();

	public Function<Double, Double> to(Unit<?> other) {
		if (!this.isCompatible(other))
			throw new UnsupportedOperationException(String.format(E_INCOMPATIBLE, this, other));
		return this.toBaseUnit().andThen(other.fromBaseUnit());
	}

	public String[] getAliases() {
		return aliases;
	}

	public Unit<Q> alias(String... aliases) {
		try {
			Unit<Q> u = clone();
			u.aliases = Arrays.push(u.aliases, aliases);
			return u;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	protected abstract Unit<Q> clone() throws CloneNotSupportedException;

	public Unit<Q> symbol(String symbol) {
		try {
			Unit<Q> u = this.clone();
			u.symbol = symbol;
			return u;
		} catch (CloneNotSupportedException e) {
			// shouldn't happen
			return null;
		}
	}

	@Override
	public int compareTo(Unit<Q> o) {
		return o == null ? -1 : this.toString().compareTo(o.toString());
	}

}
