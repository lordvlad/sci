package com.lordvlad.units;

import java.util.HashMap;
import java.util.Map;

import com.lordvlad.quantities.Quantity;

public abstract class SystemOfUnits {

	protected final Map<String, Unit<?>> names = new HashMap<String, Unit<?>>();
	protected final Map<String, Unit<?>> aliases = new HashMap<String, Unit<?>>();
	protected final Map<Unit<?>, String> units = new HashMap<Unit<?>, String>();
	protected final Map<Unit<?>, Object> quantities = new HashMap<Unit<?>, Object>();
	protected final Map<Object, Unit<?>> quantityUnits = new HashMap<Object, Unit<?>>();

	public Unit<?> getUnit(String key) {
		if (key.isEmpty())
			return Unit.ONE;
		if (names.containsKey(key))
			return names.get(key);
		if (aliases.containsKey(key))
			return aliases.get(key);
		return null;
	}
	protected <Q extends Quantity> Unit<Q> addUnit(Class<Q> quantity, Unit<Q> unit, String... aliases) {
		String key = unit.getSymbol();
		names.put(key, unit);
		units.put(unit, key);
		quantities.put(unit, quantity);
		quantityUnits.put(quantity, unit);
		return alias(unit, aliases);
	}

	protected <Q extends Quantity> Unit<Q> alias(Unit<Q> unit, String... aliases) {
		for (String alias : aliases) this.aliases.put(alias, unit);
		return unit;
	}




}
