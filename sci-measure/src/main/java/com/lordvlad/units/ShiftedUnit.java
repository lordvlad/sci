package com.lordvlad.units;

import java.util.function.Function;

import com.lordvlad.quantities.Quantity;

class ShiftedUnit<Q extends Quantity> extends Unit<Q> {

	private Unit<Q> baseUnit;
	private double shift;
	private Function<Double, Double> toBaseUnitLambda;
	private Function<Double, Double> fromBaseUnitLambda;

	public ShiftedUnit(Unit<Q> baseUnit, double shift) {
		super(baseUnit + (shift > 0 ? "+" : "") + shift);
		this.baseUnit = baseUnit;
		this.shift = shift;
	}

	@Override
	Unit<Q> getBaseUnit() {
		return baseUnit;
	}

	@Override
	Function<Double, Double> toBaseUnit() {
		if (toBaseUnitLambda == null)
			toBaseUnitLambda = new Function<Double, Double>() {

				public Double apply(Double a) {
					return a + shift;
				}

			};
		return toBaseUnitLambda;
	}

	@Override
	Function<Double, Double> fromBaseUnit() {
		if (fromBaseUnitLambda == null)
			fromBaseUnitLambda = new Function<Double, Double>() {

				public Double apply(Double a) {
					return a - shift;
				}

			};
		return fromBaseUnitLambda;
	}

	@Override
	protected Unit<Q> clone() throws CloneNotSupportedException {
		return new ShiftedUnit<Q>(baseUnit, shift);
	}

}
