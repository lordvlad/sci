package com.lordvlad.units;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;

import com.lordvlad.quantities.Quantity;

final class CompositeUnit<Q extends Quantity> extends Unit<Q> {

	final TreeMap<Unit<?>, Integer> units;

	private Function<Double, Double> toBaseUnitLambda;
	private Function<Double, Double> fromBaseUnitLambda;

	private CompositeUnit(TreeMap<Unit<?>, Integer> units, String... aliases) {
		super(nameFor(units), aliases);
		this.units = units;
	}

	@Override
	public Unit<?> pow(int num) {
		final TreeMap<Unit<?>, Integer> m = new TreeMap<>();
		for (Entry<Unit<?>, Integer> x : units.entrySet()) {
			m.put(x.getKey(), x.getValue() * num);
		}
		return of(m);
	}

	private static String nameFor(TreeMap<Unit<?>, Integer> m) {
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<Unit<?>, Integer>> it = m.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Unit<?>, Integer> next = it.next();
			sb.append(next.getKey().toString());
			if (next.getValue() != 1) {
				sb.append("^" + next.getValue());
			}
			if (it.hasNext())
				sb.append("*");
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	Unit<Q> getBaseUnit() {
		boolean returnSelf = true;
		final TreeMap<Unit<?>, Integer> m = new TreeMap<Unit<?>, Integer>();
		for (Entry<Unit<?>, Integer> u : units.entrySet()) {
			final Unit<?> b = u.getKey().getBaseUnit();
			if (b != u.getKey())
				returnSelf = false;
			m.put(b, u.getValue());
		}
		return (Unit<Q>) (returnSelf ? this : of(m));
	}

	@Override
	Function<Double, Double> toBaseUnit() {
		if (toBaseUnitLambda == null)
			toBaseUnitLambda = new Function<Double, Double>() {

				public Double apply(Double a) {
					for (Entry<Unit<?>, Integer> e : units.entrySet()) {
						a = a * Math.pow(e.getKey().toBaseUnit().apply(1.0), e.getValue());
					}
					return a;
				}

			};
		return toBaseUnitLambda;
	}

	@Override
	Function<Double, Double> fromBaseUnit() {
		if (fromBaseUnitLambda == null)
			fromBaseUnitLambda = new Function<Double, Double>() {

				public Double apply(Double a) {
					for (Entry<Unit<?>, Integer> e : units.entrySet()) {
						a = a * Math.pow(e.getKey().fromBaseUnit().apply(1.0), e.getValue());
					}
					return a;
				}

			};
		return fromBaseUnitLambda;

	}

	@SuppressWarnings("unchecked")
	@Override
	protected Unit<Q> clone() throws CloneNotSupportedException {
		return (Unit<Q>) of((TreeMap<Unit<?>, Integer>) units.clone());
	}

	static Unit<?> multiply(Unit<?>... units) {
		final TreeMap<Unit<?>, Integer> m = new TreeMap<>();
		for (Unit<?> u : units) {
			if (u instanceof CompositeUnit) {
				for (Entry<Unit<?>, Integer> e : ((CompositeUnit<?>) u).units.entrySet()) {
					if (m.containsKey(e.getKey())) {
						int n = e.getValue() + 1;
						if (n != 0) {
							m.put(e.getKey(), n);
						} else {
							m.remove(e.getKey());
						}
					} else {
						m.put(e.getKey(), e.getValue());
					}
				}
			} else {
				m.put(u, 1);
			}
		}
		return of(m);
	}

	@SuppressWarnings({})
	static Unit<?> pow(Unit<?> unit, int num) {
		final TreeMap<Unit<?>, Integer> m = new TreeMap<>();
		if (unit instanceof CompositeUnit) {
			for (Entry<Unit<?>, Integer> e : ((CompositeUnit<?>) unit).units.entrySet()) {
				m.put(e.getKey(), e.getValue() + num);
			}
		} else {
			m.put(unit, num);
		}
		return of(m);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public <T extends Quantity> Unit<?> times(Unit<T> other) {
		TreeMap<Unit<?>, Integer> m = (TreeMap<Unit<?>, Integer>) units.clone();
		if (m.containsKey(other)) {
			int n = m.get(other) + 1;
			if (n != 0) {
				m.put(other, n);
			} else {
				m.remove(other);
			}
		} else {
			m.put(other, 1);
		}
		return of(m);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Unit<?> of(TreeMap<Unit<?>, Integer> m) {
		return m.isEmpty() ? Unit.ONE : new CompositeUnit(m);
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof CompositeUnit) && units.equals(((CompositeUnit<?>) obj).units);
	}
}
