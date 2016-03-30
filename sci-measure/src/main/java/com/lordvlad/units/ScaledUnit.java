package com.lordvlad.units;

import java.io.Serializable;

import com.lordvlad.quantities.Quantity;
import com.lordvlad.structures.Lambda;

class ScaledUnit<Q extends Quantity> extends Unit<Q> implements Serializable {
	private static final long serialVersionUID = -5488981475737797350L;
	final Unit<Q> baseUnit;
	final double scale;
	final String symbol;
	private Lambda<Double, Double> toBaseUnitLambda;
	private Lambda<Double, Double> fromBaseUnitLambda;

	ScaledUnit(ScaledUnit<Q> a, Scale s)
	{
		this(a.baseUnit, MetricScale.scalings.get(a.scale*s.scaling));
	}
	
	ScaledUnit(Unit<Q> a, Scale s) {
		this(a, s.prefix + a.toString(), s.scaling);
	}
	
	ScaledUnit(Unit<Q> a, String symbol, double scale) {
		if (a instanceof ScaledUnit) {
			ScaledUnit<Q> o = (ScaledUnit<Q>) a;
			this.baseUnit = o.baseUnit;
			this.scale = o.scale * scale;
		} else {
			this.baseUnit = a;
			this.scale = scale;
		}
		this.symbol = symbol;
	}
	
	ScaledUnit(ScaledUnit<Q> a, String symbol, double scale) {
		this(a.baseUnit, symbol, a.scale * scale);
	}

	@Override
	String getSymbol() {
		return this.symbol;
	}

	

	@Override
	Unit<Q> getBaseUnit() {
		return baseUnit;
	}

	@Override Lambda<Double, Double> toBaseUnit() {
		if (toBaseUnitLambda == null) toBaseUnitLambda = new Lambda<Double, Double>(){

			public Double call(Double a) {
				return a* scale;
			}
			
		};
		return toBaseUnitLambda;
	}

	@Override Lambda<Double, Double> fromBaseUnit() {
		if (fromBaseUnitLambda == null) fromBaseUnitLambda = new Lambda<Double, Double>(){

			public Double call(Double a) {
				return a/scale;
			}
			
		};
		return fromBaseUnitLambda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseUnit == null) ? 0 : baseUnit.hashCode());
		long temp;
		temp = Double.doubleToLongBits(scale);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScaledUnit<?> other = (ScaledUnit<?>) obj;
		if (baseUnit == null) {
			if (other.baseUnit != null)
				return false;
		} else if (!baseUnit.equals(other.baseUnit))
			return false;
		if (Double.doubleToLongBits(scale) != Double.doubleToLongBits(other.scale))
			return false;
		return true;
	}
	
	
}
