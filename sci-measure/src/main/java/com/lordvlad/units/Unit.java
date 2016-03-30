package com.lordvlad.units;

import com.lordvlad.quantities.Dimensionless;
import com.lordvlad.quantities.Quantity;
import com.lordvlad.structures.Lambda;

abstract public class Unit<Q extends Quantity> {

	private static final String E_INCOMPATIBLE = null;
	
	static final Lambda<Double, Double> noop = new Lambda<Double, Double>(){ public Double call(Double a) {return a; }};

	public static final Unit<Dimensionless> ONE = new Unit<Dimensionless>() {

		@Override
		public boolean equals(Object obj) {
			return obj == this;
		}
		
		@Override
		public int hashCode() {
			return 0;
		}

		@Override
		String getSymbol() {
			return "one";
		}
		
		@Override
		public Unit<Dimensionless> pow(int num) {
			return this;
		}
		
		@Override
		public <Q extends Quantity> Unit<Q> times(Unit<Q> other) {
			return other;
		}

		@Override
		Unit<Dimensionless> getBaseUnit() {
			return this;
		}

		@Override Lambda<Double, Double> toBaseUnit() {
			return noop;
		}

		@Override Lambda<Double, Double> fromBaseUnit() {
			return noop;
		}
	};

	protected String cachedname;
	
	abstract Unit<Q> getBaseUnit();
	
	@Override
	public String toString() {
		return cachedname == null ? (cachedname = getSymbol()) : cachedname;
	}

	abstract String getSymbol();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends Quantity> Unit<?> times(Unit<T> other) {
		if (other.equals(this))	return new PowerUnit(this, 2);
		return new ProductUnit(this, other);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Unit<?> pow(int num) {
		return new PowerUnit(this, num);
	}

	public <T extends Quantity> Unit<?> over(Unit<T> other) {
		return this.times(other.pow(-1));
	}

	public Unit<Q> scale(Scale s) {
		return new ScaledUnit<Q>(this, s);
	}
	
	public Unit<Q> scale(String symbol, double scale) {
		return new ScaledUnit<Q>(this, symbol, scale);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Quantity> Unit<T> as(Class<T> quantity) {
		return (Unit<T>) this;
	}
	
	public boolean isCompatible(Unit<?> other) {
		return other.getBaseUnit().equals(this.getBaseUnit());
	}
	
	abstract Lambda<Double, Double> toBaseUnit();
	
	abstract Lambda<Double, Double> fromBaseUnit();
	
	public Lambda<Double, Double> to(Unit<?> other) {
		if (!this.isCompatible(other)) throw new UnsupportedOperationException(String.format(E_INCOMPATIBLE, this, other));
		final Lambda<Double, Double> a = this.toBaseUnit();
		final Lambda<Double, Double> b = other.fromBaseUnit();
		return new Lambda<Double, Double>(){ public Double call(Double d) {
			return b.call(a.call(d)); 
		}};
	}
}
