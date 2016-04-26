package com.lordvlad.units;

import java.util.function.Function;

import com.lordvlad.quantities.Dimensionless;
import com.lordvlad.quantities.Quantity;

class UnitOne extends Unit<Dimensionless> {

	UnitOne() {
		super("");
	}

	@Override
	public boolean equals(Object obj) {
		return obj == this;
	}
	
	@Override
	public int hashCode() {
		return 0;
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

	@Override Function<Double, Double> toBaseUnit() {
		return noop;
	}

	@Override Function<Double, Double> fromBaseUnit() {
		return noop;
	}

	@Override
	protected Unit<Dimensionless> clone() throws CloneNotSupportedException {
		return new UnitOne();
	}
}