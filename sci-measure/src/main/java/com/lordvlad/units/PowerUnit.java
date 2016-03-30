package com.lordvlad.units;

import java.io.Serializable;

import com.lordvlad.quantities.Quantity;
import com.lordvlad.structures.Lambda;

final class PowerUnit<Q extends Quantity> extends CompositeUnit<Q> implements Serializable {
	private static final long serialVersionUID = -2557741318197702804L;
	final Unit<?> unit;
	final int power;
	private Lambda<Double, Double> toBaseUnitLambda;
	private Lambda<Double, Double> fromBaseUnitLambda;

	PowerUnit(Unit<?> a, int b) {
		this.unit = a;
		this.power = b;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Unit<?> pow(int num) {
		return new PowerUnit(this.unit, this.power * num);
	}

	@Override
	String getSymbol() {
		return (unit instanceof CompositeUnit) ? "(" + unit + ")" : unit + "^" + power;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + power;
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
		PowerUnit<?> other = (PowerUnit<?>) obj;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (power != other.power)
			return false;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	Unit<Q> getBaseUnit() {
		if (this.unit instanceof BaseUnit) return this;
		return (Unit<Q>) this.unit.getBaseUnit().pow(power);
	}

	@Override Lambda<Double, Double> toBaseUnit() {
		if (this.unit instanceof BaseUnit) return noop;
		if (toBaseUnitLambda == null) toBaseUnitLambda = new Lambda<Double, Double>(){
			public Double call(Double a) {
				return a*Math.pow(unit.toBaseUnit().call(1.0), power);
			}			
		};
		return toBaseUnitLambda;
	}

	@Override Lambda<Double, Double> fromBaseUnit() {
		if (this.unit instanceof BaseUnit) return noop;
		if (fromBaseUnitLambda == null) fromBaseUnitLambda = new Lambda<Double, Double>(){
			public Double call(Double a) {
				return a*Math.pow(unit.fromBaseUnit().call(1.0), power);
			}
		};
		return fromBaseUnitLambda;
	}

}